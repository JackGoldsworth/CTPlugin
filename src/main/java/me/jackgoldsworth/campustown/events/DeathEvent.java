package me.jackgoldsworth.campustown.events;

import me.jackgoldsworth.campustown.CampusTown;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class DeathEvent implements Listener {

    private final Set<UUID> coolDown = new HashSet<>();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        p.getServer().getOnlinePlayers().forEach(player -> {
            player.getWorld().strikeLightning(player.getLocation());
            if (player.getUniqueId() != p.getUniqueId() && !coolDown.contains(player.getUniqueId())) {
                player.setHealth(0.0D);
                coolDown.add(player.getUniqueId());
                getServer().getScheduler().scheduleSyncDelayedTask(CampusTown.getInstance(), () -> coolDown.remove(player.getUniqueId()), 40L);
            }
        });
        p.getServer().broadcastMessage(ChatColor.WHITE + p.getName() + ChatColor.GRAY + " has died resulting in everyone's death.");
    }
}
