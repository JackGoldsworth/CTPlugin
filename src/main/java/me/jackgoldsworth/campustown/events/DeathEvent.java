package me.jackgoldsworth.campustown.events;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashSet;
import java.util.Set;

public class DeathEvent implements Listener {

    private final Set<String> deadPlayers = new HashSet<>();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (!deadPlayers.contains(p.getUniqueId().toString())) {
            p.getServer().getOnlinePlayers().forEach(player -> {
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1f, 1f);
                String uuid = player.getUniqueId().toString();
                if (player.getUniqueId() != p.getUniqueId() && !deadPlayers.contains(uuid)) {
                    deadPlayers.add(uuid);
                    player.setHealth(0.0D);
                }
            });
            p.getServer().broadcastMessage(ChatColor.WHITE + p.getName() + ChatColor.GRAY + " has died resulting in everyone's death.");
            deadPlayers.clear();
        }
    }
}
