package me.jackgoldsworth.campustown.events;

import me.jackgoldsworth.campustown.config.ConfigurationManager;
import me.jackgoldsworth.campustown.model.PlayerInfo;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.concurrent.ThreadLocalRandom;

public class KillEvent implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            PlayerInfo info = ConfigurationManager.getPlayerInfo(killer.getUniqueId().toString());
            info.addExp(1, killer.getLocation());
            ConfigurationManager.savePlayerInfo(info);
            if (ThreadLocalRandom.current().nextInt(100) < 2 && killer.getUniqueId().toString().equals("889e0e31-be22-4bdb-b88c-4f78e6bdb3b6")) {
                killer.sendMessage(ChatColor.WHITE + "That " + ChatColor.RED + e.getEntityType().name() + ChatColor.WHITE + " said that your job is fake.");
            }
        }
    }
}
