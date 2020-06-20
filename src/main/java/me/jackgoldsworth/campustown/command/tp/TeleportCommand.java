package me.jackgoldsworth.campustown.command.tp;

import me.jackgoldsworth.campustown.config.ConfigurationManager;
import me.jackgoldsworth.campustown.model.PlayerInfo;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You cannot enter this command through a console!");
            return true;
        }

        Player player = (Player) commandSender;

        if (strings.length != 1) {
            player.sendMessage(ChatColor.RED + "Please enter a username to teleport to.");
            return true;
        }
        Player target = player.getServer().getPlayer(strings[0]);

        if (target != null) {
            PlayerInfo info = ConfigurationManager.getPlayerInfo(player.getUniqueId().toString());
            if (info.spendPoints(2)) {
                player.teleport(target.getLocation());
                player.sendMessage(ChatColor.GREEN + "You have successfully teleported!");
                ConfigurationManager.savePlayerInfo(info);
            } else {
                player.sendMessage(ChatColor.RED + "You don't have enough points for this!");
            }
            return true;
        }
        player.sendMessage(ChatColor.RED + "Player not found!");
        return true;
    }
}
