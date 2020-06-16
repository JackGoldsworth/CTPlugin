package me.jackgoldsworth.campustown.config;

import me.jackgoldsworth.campustown.CampusTown;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationManager {

    private final static CampusTown campusTown = CampusTown.getInstance();
    private static FileConfiguration config;

    public static void loadConfig() {
        config = campusTown.getConfig();
        config.options().copyDefaults(true);
    }

    public static void setValue(String path, Object value) {
        config.set(path, value);
    }

    public static void saveAndReload() {
        campusTown.saveConfig();
        campusTown.reloadConfig();
    }

    public static String getValue(String path) {
        return config.getString(path);
    }
}
