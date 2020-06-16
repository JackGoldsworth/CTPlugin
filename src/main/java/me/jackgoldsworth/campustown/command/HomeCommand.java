package me.jackgoldsworth.campustown.command;

import me.jackgoldsworth.campustown.CampusTown;
import me.jackgoldsworth.campustown.config.ConfigurationManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You cannot enter this command through a console!");
            return false;
        }
        Player p = (Player) commandSender;
        String x = ConfigurationManager.getValue(p.getName() + ".x");
        if (x != null) {
            String y = ConfigurationManager.getValue(p.getName() + ".y");
            String z = ConfigurationManager.getValue(p.getName() + ".z");
            String yaw = ConfigurationManager.getValue(p.getName() + ".yaw");
            String pitch = ConfigurationManager.getValue(p.getName() + ".pitch");
            String worldName = ConfigurationManager.getValue(p.getName() + ".world");
            Location location = new Location(CampusTown.getInstance().getServer().getWorld(worldName),
                    Double.parseDouble(x),
                    Double.parseDouble(y),
                    Double.parseDouble(z),
                    Float.parseFloat(yaw),
                    Float.parseFloat(pitch)
            );
            p.teleport(location);
            p.sendMessage(ChatColor.GREEN + "You teleported home.");
            return true;
        }
        return false;
    }
}
