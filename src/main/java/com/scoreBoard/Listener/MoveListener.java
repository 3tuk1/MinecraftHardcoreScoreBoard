package com.scoreBoard.Listener;

import com.scoreBoard.ScoreCalc.ScoreCalculation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class MoveListener implements Listener {
    private final Map<Player, Location> lastLocations = new HashMap<>();
    private final Map<Player, Integer> pendingScores = new HashMap<>();

    public MoveListener(JavaPlugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Location lastLocation = lastLocations.get(player);
                    Location currentLocation = player.getLocation();

                    if (lastLocation != null && !lastLocation.equals(currentLocation)) {
                        double distance = lastLocation.distance(currentLocation);
                        if (distance > 0.1) { // 小さな移動を無視
                            int score = calculateScore(currentLocation, (int) distance);
                            pendingScores.put(player, pendingScores.getOrDefault(player, 0) + score);
                        }
                    }

                    lastLocations.put(player, currentLocation);
                }
            }
        }.runTaskTimer(plugin, 0L, 20L); // 200L = 10秒 (20 ticks * 10秒)

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Map.Entry<Player, Integer> entry : pendingScores.entrySet()) {
                    Player player = entry.getKey();
                    int score = entry.getValue();
                    if (score > 0) {
                        ScoreCalculation.addScore(player, score, "Move");
                    }
                }
                pendingScores.clear();
            }
        }.runTaskTimerAsynchronously(plugin, 0L, 200L); // 非同期でスコアを加算
    }

    private int calculateScore(Location location, int distance) {
        World world = location.getWorld();
        if (world != null) {
            if (world.getName().equals("world_nether")) {
                int score = (int) (distance * 1.3);
                return score;
            } else if (world.getName().equals("world_the_end")) {
                int score = (int) (distance * 1.5);
                return score;
            }
        }
        return distance; // 通常のワールドではそのまま
    }


}