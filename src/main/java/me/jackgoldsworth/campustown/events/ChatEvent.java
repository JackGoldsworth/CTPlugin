package me.jackgoldsworth.campustown.events;

import me.jackgoldsworth.campustown.config.ConfigurationManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String prefix = ConfigurationManager.getValue(p.getName() + ".prefix");
        if (prefix != null) {
            e.setCancelled(true);
            p.getServer().broadcastMessage(ChatColor.GREEN + "[" + prefix + "]" + " " + ChatColor.GRAY + p.getName() + ChatColor.WHITE + ": " + e.getMessage());
        }
    }
}
