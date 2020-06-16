package me.jackgoldsworth.campustown.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerDisconnectEvent implements Listener {

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e) {
        List<Player> players = e.getPlayer()
                .getServer()
                .getOnlinePlayers()
                .stream()
                .filter(p -> p.getHealth() <= 5)
                .collect(Collectors.toList());
        if (!players.isEmpty()) {
            e.setQuitMessage("You've quit before someone was about to die, there for you have also died.");
            e.getPlayer().setHealth(0);
        }
    }
}
