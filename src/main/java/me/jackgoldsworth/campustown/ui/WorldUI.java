package me.jackgoldsworth.campustown.ui;

import me.jackgoldsworth.campustown.CampusTown;
import me.jackgoldsworth.campustown.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class WorldUI implements Listener {

    private static final String UI_NAME = "World Portal";

    public static Inventory getInventory(Player player, int playerLevel) {
        Inventory inventory = Bukkit.createInventory(null, 9, UI_NAME);

        if (!player.getWorld().equals(CampusTown.getInstance().getOldWorld())) {
            if (playerLevel >= 10) {
                ItemStack unlocked = ItemUtils.createItem(Material.GRASS_BLOCK,
                        1,
                        ChatColor.GRAY + "Old World",
                        Collections.singletonList(ChatColor.GREEN + "Click here to teleport to the " + ChatColor.AQUA + "Old World"));
                inventory.setItem(4, unlocked);
            } else {
                ItemStack locked = ItemUtils.createItem(Material.BARRIER,
                        1,
                        ChatColor.RED + "LOCKED",
                        Collections.singletonList(ChatColor.GRAY + "You have not unlocked access to the " + ChatColor.GREEN + "Old World"));
                inventory.setItem(4, locked);
            }
        } else {
            ItemStack newWorld = ItemUtils.createItem(Material.ENDER_PEARL,
                    1,
                    ChatColor.GRAY + "New World",
                    Collections.singletonList(ChatColor.GREEN + "Click here to teleport to the " + ChatColor.AQUA + "New World"));
            inventory.setItem(4, newWorld);
        }

        return inventory;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        HumanEntity player = e.getView().getPlayer();
        if (e.getView().getTitle().equals(UI_NAME)) {
            if (item != null && item.getType() != Material.AIR) {
                if (item.getType() == Material.GRASS_BLOCK) {
                    Location location = new Location(CampusTown.getInstance().getOldWorld(), 1640, 63, -466, 90f, 0.2f);
                    player.teleport(location);
                } else if (item.getType() == Material.ENDER_PEARL) {
                    World world = Bukkit.getWorlds().get(0);
                    Location spawn = world.getSpawnLocation();
                    Location location = new Location(world, spawn.getX(), spawn.getY(), spawn.getZ());
                    player.teleport(location);
                }
                e.setCancelled(true);
            }
        }
    }
}