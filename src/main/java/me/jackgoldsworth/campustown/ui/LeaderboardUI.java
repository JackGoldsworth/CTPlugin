package me.jackgoldsworth.campustown.ui;

import me.jackgoldsworth.campustown.config.ConfigurationManager;
import me.jackgoldsworth.campustown.model.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class LeaderboardUI implements Listener {

    public static Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 9, "Leaderboard");

        List<PlayerInfo> playerInfo = ConfigurationManager.getAllPlayerInfo();
        Collections.sort(playerInfo);

        for (int i = 0; i < Math.min(playerInfo.size(), 9); i++) {
            PlayerInfo info = playerInfo.get(i);
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(info.getUuid()));
            skullMeta.setOwningPlayer(player);
            skullMeta.setDisplayName(ChatColor.AQUA + player.getName());

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "Experience: " + ChatColor.AQUA + info.getExp());
            lore.add(ChatColor.GREEN + "Experience Needed for Next Level: " + ChatColor.AQUA + info.getExperienceNextLevel());
            lore.add(ChatColor.GREEN + "Level: " + ChatColor.AQUA + info.getLevel());
            lore.add(ChatColor.GREEN + "Points: " + ChatColor.AQUA + info.getPoints());

            skullMeta.setLore(lore);
            skull.setItemMeta(skullMeta);
            inventory.setItem(i, skull);
        }
        return inventory;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("Leaderboard")) {
            e.setCancelled(true);
        }
    }
}