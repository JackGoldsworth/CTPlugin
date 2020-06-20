package me.jackgoldsworth.campustown.command;

import me.jackgoldsworth.campustown.config.ConfigurationManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You cannot enter this command through a console!");
            return false;
        }
        Player p = (Player) commandSender;
        String uuid = p.getUniqueId().toString();
        ConfigurationManager.setValue(uuid + ".world", p.getLocation().getWorld().getName());
        ConfigurationManager.setValue(uuid + ".x", p.getLocation().getX());
        ConfigurationManager.setValue(uuid + ".y", p.getLocation().getY());
        ConfigurationManager.setValue(uuid + ".z", p.getLocation().getZ());
        ConfigurationManager.setValue(uuid + ".yaw", p.getLocation().getYaw());
        ConfigurationManager.setValue(uuid + ".pitch", p.getLocation().getPitch());
        p.sendMessage(ChatColor.GREEN + "Your home has been set!");
        return true;
    }
}
