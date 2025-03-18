package com.scoreBoard.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.entity.Entity;
import java.util.HashSet;
import java.util.Set;

public class SpawnerSpawnListener implements Listener {
    private static final Set<Entity> spawnerSpawns = new HashSet<>();

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {
            spawnerSpawns.add(event.getEntity());
        }
    }

    public static boolean isSpawnerSpawn(Entity entity) {
        return spawnerSpawns.contains(entity);
    }
}