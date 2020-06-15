package me.jackgoldsworth.campustown.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.Color;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        p.getWorld().strikeLightning(p.getLocation());
        p.getServer().getOnlinePlayers().forEach(player -> player.setHealth(0.0D));
        p.getServer().broadcastMessage(Color.WHITE + p.getName() + Color.GRAY + " has died resulting in everyone's death.");
    }
}
