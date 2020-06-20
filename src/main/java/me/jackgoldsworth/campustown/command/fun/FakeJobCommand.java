package me.jackgoldsworth.campustown.command.fun;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.concurrent.ThreadLocalRandom;

public class FakeJobCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        ChatColor[] values = ChatColor.values();
        ChatColor color = values[ThreadLocalRandom.current().nextInt(values.length)];
        commandSender.getServer().broadcastMessage(color + "Joe has a fake job.");
        return true;
    }
}
