package me.jackgoldsworth.campustown;

import me.jackgoldsworth.campustown.command.LeaderboardCommand;
import me.jackgoldsworth.campustown.command.PrefixCommand;
import me.jackgoldsworth.campustown.command.fun.D20Command;
import me.jackgoldsworth.campustown.command.fun.FakeJobCommand;
import me.jackgoldsworth.campustown.command.points.ShopCommand;
import me.jackgoldsworth.campustown.command.points.StatsCommand;
import me.jackgoldsworth.campustown.command.tp.HomeCommand;
import me.jackgoldsworth.campustown.command.tp.SetHomeCommand;
import me.jackgoldsworth.campustown.command.tp.TeleportCommand;
import me.jackgoldsworth.campustown.command.tp.WorldCommand;
import me.jackgoldsworth.campustown.config.ConfigurationManager;
import me.jackgoldsworth.campustown.events.ChatEvent;
import me.jackgoldsworth.campustown.events.DeathEvent;
import me.jackgoldsworth.campustown.events.KillEvent;
import me.jackgoldsworth.campustown.model.PlayerInfo;
import me.jackgoldsworth.campustown.ui.LeaderboardUI;
import me.jackgoldsworth.campustown.ui.PointShopUI;
import me.jackgoldsworth.campustown.ui.WorldUI;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Objects;

public class CampusTown extends JavaPlugin {

    private static CampusTown instance;
    private static World oldWorld;

    @Override
    public void onEnable() {
        instance = this;
        ConfigurationManager.loadConfig();
        this.reloadConfig();

        schedulePoints();
        loadWorlds();

        PointShopUI pointShopUI = new PointShopUI();
        pointShopUI.initInventory();

        this.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        this.getServer().getPluginManager().registerEvents(new KillEvent(), this);
        this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        this.getServer().getPluginManager().registerEvents(new LeaderboardUI(), this);
        this.getServer().getPluginManager().registerEvents(new WorldUI(), this);
        this.getServer().getPluginManager().registerEvents(pointShopUI, this);

        Objects.requireNonNull(this.getServer().getPluginCommand("fakejob")).setExecutor(new FakeJobCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("tp")).setExecutor(new TeleportCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("d20")).setExecutor(new D20Command());
        Objects.requireNonNull(this.getServer().getPluginCommand("setprefix")).setExecutor(new PrefixCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("sethome")).setExecutor(new SetHomeCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("home")).setExecutor(new HomeCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("shop")).setExecutor(new ShopCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("stats")).setExecutor(new StatsCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("leaderboard")).setExecutor(new LeaderboardCommand());
        Objects.requireNonNull(this.getServer().getPluginCommand("world")).setExecutor(new WorldCommand());
    }

    @Override
    public void onDisable() {
        this.saveConfig();
        instance = null;
    }

    public World getOldWorld() {
        return oldWorld;
    }

    private void schedulePoints() {
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, () -> getServer()
                .getOnlinePlayers()
                .forEach(p -> {
                    p.sendMessage(ChatColor.GREEN + "You've earned a point!");
                    PlayerInfo info = ConfigurationManager.getPlayerInfo(p.getUniqueId().toString());
                    info.addPoints(1);
                    ConfigurationManager.savePlayerInfo(info);
                }), 0L, 20 * 60 * 20); // Ticks, seconds, minutes
    }

    private void loadWorlds() {
        oldWorld = new WorldCreator("old_world").environment(World.Environment.NORMAL).createWorld();
    }

    public static CampusTown getInstance() {
        return instance;
    }
}
