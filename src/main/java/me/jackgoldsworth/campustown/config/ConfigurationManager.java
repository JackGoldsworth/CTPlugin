package me.jackgoldsworth.campustown.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import me.jackgoldsworth.campustown.CampusTown;
import me.jackgoldsworth.campustown.model.PlayerInfo;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.concurrent.TimeUnit;

public class ConfigurationManager {

    private final static CampusTown campusTown = CampusTown.getInstance();
    private final static Cache<String, PlayerInfo> cache = CacheBuilder.newBuilder()
            .maximumSize(5)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public static void loadConfig() {
        campusTown.getConfig().options().copyDefaults(true);
    }

    public static void setValue(String path, String value) {
        campusTown.getConfig().set(path, value);
        campusTown.saveConfig();
    }

    public static void setValue(String path, double value) {
        campusTown.getConfig().set(path, value);
        campusTown.saveConfig();
    }

    public static void setValue(String path, float value) {
        campusTown.getConfig().set(path, value);
        campusTown.saveConfig();
    }

    public static void savePlayerInfo(PlayerInfo info) {
        cache.put(info.getUuid(), info);
        setValue(info.getUuid() + ".exp", info.getExp());
        setValue(info.getUuid() + ".level", info.getLevel());
        setValue(info.getUuid() + ".points", info.getPoints());
        campusTown.saveConfig();
    }

    /**
     * Gets player info from the cache. (Must save after edits!)
     *
     * @param uuid player uuid
     * @return player info
     */
    public static PlayerInfo getPlayerInfo(String uuid) {
        if (!cache.asMap().containsKey(uuid)) {
            int level = getIntValue(uuid + ".level");
            PlayerInfo info = new PlayerInfo(
                    uuid,
                    getIntValue(uuid + ".exp"),
                    getIntValue(uuid + ".points"),
                    level == 0 ? 1 : level
            );
            addCacheValue(info);
            return info;
        }
        return cache.getIfPresent(uuid);
    }

    private static void addCacheValue(PlayerInfo info) {
        if (cache.size() >= 3) {
        }
        cache.put(info.getUuid(), info);
    }

    public static String getValue(String path) {
        return campusTown.getConfig().getString(path);
    }

    public static int getIntValue(String path) {
        return campusTown.getConfig().getInt(path);
    }

    public static double getValueDouble(String path) {
        return campusTown.getConfig().getDouble(path);
    }

    public static FileConfiguration getConfig() {
        return campusTown.getConfig();
    }
}
