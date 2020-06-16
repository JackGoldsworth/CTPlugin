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
        double x = ConfigurationManager.getValueDouble(p.getName() + ".x");
        double y = ConfigurationManager.getValueDouble(p.getName() + ".y");
        double z = ConfigurationManager.getValueDouble(p.getName() + ".z");
        double yaw = ConfigurationManager.getValueDouble(p.getName() + ".yaw");
        double pitch = ConfigurationManager.getValueDouble(p.getName() + ".pitch");
        String worldName = ConfigurationManager.getValue(p.getName() + ".world");
        Location location = new Location(CampusTown.getInstance().getServer().getWorld(worldName),
                x,
                y,
                z,
                (float) yaw,
                (float) pitch
        );
        p.teleport(location);
        p.sendMessage(ChatColor.GREEN + "You teleported home.");
        return true;
    }
}
