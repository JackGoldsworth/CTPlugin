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
        ConfigurationManager.setValue(p.getName() + ".world", p.getLocation().getWorld().getName());
        ConfigurationManager.setValue(p.getName() + ".x", p.getLocation().getX());
        ConfigurationManager.setValue(p.getName() + ".y", p.getLocation().getY());
        ConfigurationManager.setValue(p.getName() + ".z", p.getLocation().getZ());
        ConfigurationManager.setValue(p.getName() + ".yaw", p.getLocation().getYaw());
        ConfigurationManager.setValue(p.getName() + ".pitch", p.getLocation().getPitch());
        ConfigurationManager.saveAndReload();
        p.sendMessage(ChatColor.GREEN + "Your home has been set!");
        return true;
    }
}
