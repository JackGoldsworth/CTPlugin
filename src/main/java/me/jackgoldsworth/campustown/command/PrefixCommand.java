package me.jackgoldsworth.campustown.command;

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
        }
        if (!(commandSender instanceof Player)) {
            if (strings.length == 2) {
                Player p = commandSender.getServer().getPlayer(strings[0]);
                if (p != null) {
                    ConfigurationManager.setValue(p.getUniqueId().toString() + ".prefix", strings[1]);
                }
                return true;
            }
            commandSender.sendMessage(ChatColor.RED + "You cannot enter this command through a console!");
            return false;
        }
        ConfigurationManager.setValue(((Player) commandSender).getUniqueId().toString() + ".prefix", strings[0]);
        return true;
    }
}
