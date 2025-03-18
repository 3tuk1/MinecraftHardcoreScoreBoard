package com.scoreBoard.Listener;

import com.scoreBoard.ScoreCalc.ScoreCalculation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.HashMap;
import java.util.Map;

public class OtherScoreListener implements Listener {

    private final Map<Player, Integer> blockBreakCount = new HashMap<>();

    // 農作業によるポイントの上昇
    @EventHandler
    public void onHarvest(PlayerHarvestBlockEvent event) {
        Player player = event.getPlayer();
        ScoreCalculation.addScore(player, 1, "Other");
    }

    // 釣りによるポイントの上昇
    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            Player player = event.getPlayer();
            ScoreCalculation.addScore(player, 15, "Other");
        }
    }

    // 実績解除によるポイントの上昇
    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        ScoreCalculation.addScore(player, 20, "Other");
    }

    // どのブロックでも100ブロック破壊するとスコアの上昇
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        blockBreakCount.put(player, blockBreakCount.getOrDefault(player, 0) + 1);
        if (blockBreakCount.get(player) >= 100) {
            ScoreCalculation.addScore(player, 20, "Other");
            blockBreakCount.put(player, 0); // カウントをリセット
        }
    }
}