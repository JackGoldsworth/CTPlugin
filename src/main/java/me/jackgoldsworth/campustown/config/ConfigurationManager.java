package me.jackgoldsworth.campustown.config;

import me.jackgoldsworth.campustown.CampusTown;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationManager {

    private final static CampusTown campusTown = CampusTown.getInstance();

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

    public static String getValue(String path) {
        return campusTown.getConfig().getString(path);
    }

    public static double getValueDouble(String path) {
        return campusTown.getConfig().getDouble(path);
    }

    public static FileConfiguration getConfig() {
        return campusTown.getConfig();
    }
}
