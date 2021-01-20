package me.jackgoldsworth.campustown.command.tp;

import me.jackgoldsworth.campustown.config.ConfigurationManager;
import me.jackgoldsworth.campustown.model.PlayerInfo;
import me.jackgoldsworth.campustown.ui.WorldUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You cannot enter this command through a console!");
            return false;
        }
        Player player = (Player) commandSender;
        PlayerInfo info = ConfigurationManager.getPlayerInfo(player.getUniqueId().toString());
        player.openInventory(WorldUI.getInventory(player, info.getLevel()));
        return true;
    }
}
