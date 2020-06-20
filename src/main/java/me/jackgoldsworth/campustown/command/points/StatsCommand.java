package me.jackgoldsworth.campustown.command.points;

import me.jackgoldsworth.campustown.config.ConfigurationManager;
import me.jackgoldsworth.campustown.model.PlayerInfo;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You cannot enter this command through a console!");
            return false;
        }
        Player p = (Player) commandSender;
        PlayerInfo info = ConfigurationManager.getPlayerInfo(p.getUniqueId().toString());
        p.sendMessage(ChatColor.GREEN + "---------- " + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " ----------");
        p.sendMessage(ChatColor.GREEN + "Experience: " + ChatColor.AQUA + info.getExp());
        p.sendMessage(ChatColor.GREEN + "Experience Needed for Next Level: " + ChatColor.AQUA + info.getExperienceNextLevel());
        p.sendMessage(ChatColor.GREEN + "Level: " + ChatColor.AQUA + info.getLevel());
        p.sendMessage(ChatColor.GREEN + "Points: " + ChatColor.AQUA + info.getPoints());
        return true;
    }
}
