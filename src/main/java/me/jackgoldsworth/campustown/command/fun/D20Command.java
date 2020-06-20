package me.jackgoldsworth.campustown.command.fun;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class D20Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        int number = ThreadLocalRandom.current().nextInt(20);
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            p.getServer().broadcastMessage(getResult(p.getName(), number));
            return true;
        }
        commandSender.getServer().broadcastMessage(getResult("Console", number));
        return true;
    }

    private String getResult(String user, int number) {
        return ChatColor.RED + user + ChatColor.GRAY +
                " has rolled a " + (number > 10 ? ChatColor.GREEN : ChatColor.RED) + number;
    }
}
