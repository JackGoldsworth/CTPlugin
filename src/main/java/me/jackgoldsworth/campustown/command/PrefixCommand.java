package me.jackgoldsworth.campustown.command;

import me.jackgoldsworth.campustown.Permissions;
import me.jackgoldsworth.campustown.config.ConfigurationManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrefixCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(ChatColor.RED + "Please enter in a prefix!");
            return false;
        } else if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You cannot enter this command through a console!");
            return false;
        }
        if (strings.length == 2) {
            Player p = commandSender.getServer().getPlayer(strings[1]);
            if (p != null && p.hasPermission(Permissions.PREFIX.getPermission())) {
                ConfigurationManager.setValue(p.getName() + ".prefix", strings[0]);
            }
            return true;
        }
        ConfigurationManager.setValue(commandSender.getName() + ".prefix", strings[0]);
        return true;
    }
}
