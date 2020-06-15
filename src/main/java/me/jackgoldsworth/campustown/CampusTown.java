package me.jackgoldsworth.campustown;

import me.jackgoldsworth.campustown.events.DeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CampusTown extends JavaPlugin {

    private static CampusTown instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static CampusTown getInstance() {
        return instance;
    }
}
