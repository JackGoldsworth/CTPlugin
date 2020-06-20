package me.jackgoldsworth.campustown.command.points;

import me.jackgoldsworth.campustown.ui.PointShopUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You cannot enter this command through a console!");
            return false;
        }
        ((Player) commandSender).openInventory(PointShopUI.getInventory());
        return true;
    }
}
