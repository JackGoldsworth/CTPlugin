package me.jackgoldsworth.campustown.ui;

import me.jackgoldsworth.campustown.config.ConfigurationManager;
import me.jackgoldsworth.campustown.model.PlayerInfo;
import me.jackgoldsworth.campustown.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class PointShopUI implements Listener {

    private static Inventory inventory;

    public static Inventory getInventory() {
        return inventory;
    }

    public void initInventory() {
        inventory = Bukkit.createInventory(null, 9, "Points Shop");

        ItemStack diamond = ItemUtils.createItem(Material.DIAMOND,
                1,
                ChatColor.GRAY + "Diamond",
                getItemDescription(2));

        ItemStack emerald = ItemUtils.createItem(Material.EMERALD,
                1,
                ChatColor.GRAY + "Emerald",
                getItemDescription(2));

        ItemStack netherite = ItemUtils.createItem(Material.NETHERITE_INGOT,
                1,
                ChatColor.GRAY + "Netherite",
                getItemDescription(5));

        ItemStack firework = ItemUtils.createItem(Material.FIREWORK_ROCKET,
                64,
                ChatColor.GRAY + "Fireworks",
                getItemDescription(10));

        ItemStack enderEgg = ItemUtils.createItem(Material.ENDERMAN_SPAWN_EGG,
                1,
                ChatColor.GRAY + "Enderman Egg (Used for Spawner Change)",
                getItemDescription(15));

        ItemStack cowEgg = ItemUtils.createItem(Material.COW_SPAWN_EGG,
                1,
                ChatColor.GRAY + "Cow Egg (Used for Spawner Change)",
                getItemDescription(10));

        ItemStack spawner = ItemUtils.createItem(Material.SPAWNER,
                1,
                ChatColor.GRAY + "Pig Spawner",
                getItemDescription(20));

        inventory.setItem(0, diamond);
        inventory.setItem(1, emerald);
        inventory.setItem(2, netherite);
        inventory.setItem(4, firework);
        inventory.setItem(6, enderEgg);
        inventory.setItem(7, cowEgg);
        inventory.setItem(8, spawner);
    }

    private List<String> getItemDescription(int price) {
        String points = "points";
        if (price == 1)
            points = "point";
        return Collections.singletonList(ChatColor.GREEN + "Purchase this for " + ChatColor.AQUA + price + ChatColor.GREEN + " " + points);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        HumanEntity player = e.getView().getPlayer();
        PlayerInfo info = ConfigurationManager.getPlayerInfo(player.getUniqueId().toString());
        if (e.getView().getTitle().equals("Points Shop")) {
            if (item != null && item.getType() != Material.AIR) {
                if (item.getType() == Material.DIAMOND) {
                    boolean result = info.spendPoints(2);
                    if (result) {
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                        ConfigurationManager.savePlayerInfo(info);
                    }
                } else if (item.getType() == Material.EMERALD) {
                    boolean result = info.spendPoints(2);
                    if (result) {
                        player.getInventory().addItem(new ItemStack(Material.EMERALD, 1));
                        ConfigurationManager.savePlayerInfo(info);
                    }
                } else if (item.getType() == Material.NETHERITE_INGOT) {
                    boolean result = info.spendPoints(5);
                    if (result) {
                        player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
                        ConfigurationManager.savePlayerInfo(info);
                    }
                } else if (item.getType() == Material.FIREWORK_ROCKET) {
                    boolean result = info.spendPoints(10);
                    if (result) {
                        player.getInventory().addItem(new ItemStack(Material.FIREWORK_ROCKET, 64));
                        ConfigurationManager.savePlayerInfo(info);
                    }
                } else if (item.getType() == Material.SPAWNER) {
                    boolean result = info.spendPoints(20);
                    if (result) {
                        player.getInventory().addItem(new ItemStack(Material.SPAWNER, 1));
                        ConfigurationManager.savePlayerInfo(info);
                    }
                } else if (item.getType() == Material.COW_SPAWN_EGG) {
                    boolean result = info.spendPoints(10);
                    if (result) {
                        player.getInventory().addItem(new ItemStack(Material.COW_SPAWN_EGG, 1));
                        ConfigurationManager.savePlayerInfo(info);
                    }
                } else if (item.getType() == Material.ENDERMAN_SPAWN_EGG) {
                    boolean result = info.spendPoints(15);
                    if (result) {
                        player.getInventory().addItem(new ItemStack(Material.ENDERMAN_SPAWN_EGG, 1));
                        ConfigurationManager.savePlayerInfo(info);
                    }
                }
                e.setCancelled(true);
            }
        }
    }
}
