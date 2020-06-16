package me.jackgoldsworth.campustown;

import me.jackgoldsworth.campustown.command.*;
import me.jackgoldsworth.campustown.config.ConfigurationManager;
import me.jackgoldsworth.campustown.events.ChatEvent;
import me.jackgoldsworth.campustown.events.DeathEvent;
import me.jackgoldsworth.campustown.events.KillEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class CampusTown extends JavaPlugin {

    private static CampusTown instance;

    @Override
    public void onEnable() {
        instance = this;
        ConfigurationManager.loadConfig();
        this.reloadConfig();
        this.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        this.getServer().getPluginManager().registerEvents(new KillEvent(), this);
        this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        //this.getServer().getPluginManager().registerEvents(new PlayerDisconnectEvent(), this);
        Objects.requireNonNull(this.getServer().getPluginCommand("fakejob")).setExecutor(new FakeJobCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("d20")).setExecutor(new D20Command());
        Objects.requireNonNull(this.getServer().getPluginCommand("setprefix")).setExecutor(new PrefixCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("sethome")).setExecutor(new SetHomeCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("home")).setExecutor(new HomeCommand());
    }

    @Override
    public void onDisable() {
        this.saveConfig();
        instance = null;
    }

    public static CampusTown getInstance() {
        return instance;
    }
}
