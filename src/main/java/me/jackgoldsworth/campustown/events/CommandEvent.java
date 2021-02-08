package me.jackgoldsworth.campustown.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().equals("/plugins")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§fPlugins (1): §aJack's Custom Server");
        } else if(e.getMessage().equals("/help")) {
            e.setCancelled(true);
            Player p = e.getPlayer();
            p.sendMessage(ChatColor.GREEN + "---------- " + ChatColor.AQUA + "Commands" + ChatColor.GREEN + " ----------");
            p.sendMessage(getCommand("help", "Sends the player a list of available commands."));
            p.sendMessage(getCommand("d20", "Rolls a dice between 1 - 20."));
            p.sendMessage(getCommand("stats", "List your current stats, including points and levels."));
            p.sendMessage(getCommand("shop", "You can spend your points on items here."));
            p.sendMessage(getCommand("fakejob", "Tell Joe that he has a fake job."));
            p.sendMessage(getCommand("world", "If you're level 10+, you can travel to our previous world (and back)."));
            p.sendMessage(getCommand("leaderboard", "Opens up a menu that lists players based on level and experience."));
            p.sendMessage(getCommand("setprefix", "Set a one word prefix that can go before your name in chat."));
            p.sendMessage(getCommand("tp", "Teleport to another player for 2 points."));
            p.sendMessage(getCommand("sethome", "Set a home at your current location."));
            p.sendMessage(getCommand("home", "Go to your home."));
        }
    }

    private String getCommand(String command, String message) {
        return ChatColor.GREEN + "/" + command + ChatColor.WHITE + " - " + ChatColor.AQUA + message;
    }
}
