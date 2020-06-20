package me.jackgoldsworth.campustown.events;

import me.jackgoldsworth.campustown.config.ConfigurationManager;
import me.jackgoldsworth.campustown.model.PlayerInfo;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();

        String prefix = ConfigurationManager.getValue(uuid + ".prefix");
        PlayerInfo info = ConfigurationManager.getPlayerInfo(uuid);

        final String levelStr = ChatColor.BLUE + "[" + info.getLevel() + "] ";
        final String prefixStr = ChatColor.GREEN + "[" + prefix + "]";

        if (prefix != null) {
            e.setCancelled(true);
            p.getServer().broadcastMessage(levelStr + prefixStr + " " + ChatColor.GRAY + p.getName() + ChatColor.WHITE + ": " + e.getMessage());
            return;
        }
        p.getServer().broadcastMessage(levelStr + ChatColor.GRAY + p.getName() + ChatColor.WHITE + ": " + e.getMessage());
        e.setCancelled(true);
    }
}
