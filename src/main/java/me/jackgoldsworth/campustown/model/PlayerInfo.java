package me.jackgoldsworth.campustown.model;

import org.bukkit.Location;
import org.bukkit.Sound;

public class PlayerInfo {

    private final String uuid;
    private int exp;
    private int points;
    private int level;

    public PlayerInfo(String uuid, int exp, int points, int level) {
        this.exp = exp;
        this.points = points;
        this.level = level;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public int getPoints() {
        return points;
    }

    public void addExp(int exp, Location location) {
        this.exp += exp;
        int nextLevel = getExperienceNextLevel();
        if (nextLevel <= this.exp) {
            this.level = level + 1;
            this.exp = 0;
            location.getWorld().playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1f, 1f);
        }
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public boolean spendPoints(int points) {
        int result = this.points - points;
        if (result >= 0) {
            this.points = result;
            return true;
        }
        return false;
    }

    public int getExperienceNextLevel() {
        return (int) Math.round((4 * (Math.pow(level, 3))) / 5);
    }
}
