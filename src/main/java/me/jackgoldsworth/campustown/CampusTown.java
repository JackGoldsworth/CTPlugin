package me.jackgoldsworth.campustown;

import me.jackgoldsworth.campustown.command.D20Command;
import me.jackgoldsworth.campustown.command.FakeJobCommand;
import me.jackgoldsworth.campustown.events.DeathEvent;
import me.jackgoldsworth.campustown.events.KillEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class CampusTown extends JavaPlugin {

    private static CampusTown instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        this.getServer().getPluginManager().registerEvents(new KillEvent(), this);
        Objects.requireNonNull(this.getServer().getPluginCommand("fakejob")).setExecutor(new FakeJobCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("d20")).setExecutor(new D20Command());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static CampusTown getInstance() {
        return instance;
    }
}
