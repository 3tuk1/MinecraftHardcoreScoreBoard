package com.scoreBoard.ScoreCalc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.Material;

public class OreBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.DIAMOND_ORE) {
            // スコア計算ロジックをここに追加
            ScoreCalculation.addScore(event.getPlayer(), 50, "Ore");
        }
        if (event.getBlock().getType() == Material.EMERALD_ORE) {
            // スコア計算ロジックをここに追加
            ScoreCalculation.addScore(event.getPlayer(), 100, "Ore");
        }
        if (event.getBlock().getType() == Material.GOLD_ORE) {
            // スコア計算ロジックをここに追加
            ScoreCalculation.addScore(event.getPlayer(), 5, "Ore");
        }
        if (event.getBlock().getType() == Material.IRON_ORE) {
            // スコア計算ロジックをここに追加
            ScoreCalculation.addScore(event.getPlayer(), 10, "Ore");
        }
        if (event.getBlock().getType() == Material.COAL_ORE) {
            // スコア計算ロジックをここに追加
            ScoreCalculation.addScore(event.getPlayer(), 2, "Ore");
        }
    }
}