package com.scoreBoard.Listener;

import com.scoreBoard.ScoreCalc.ScoreCalculation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.Material;

public class OreBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.DIAMOND_ORE || event.getBlock().getType() == Material.DEEPSLATE_DIAMOND_ORE) {
            // スコア計算ロジックをここに追加
            ScoreCalculation.addScore(event.getPlayer(), 50, "Ore");
        }
        if (event.getBlock().getType() == Material.EMERALD_ORE ||event.getBlock().getType() == Material.DEEPSLATE_EMERALD_ORE) {
            // スコア計算ロジックをここに追加
            ScoreCalculation.addScore(event.getPlayer(), 100, "Ore");
        }
        if (event.getBlock().getType() == Material.GOLD_ORE || event.getBlock().getType() == Material.DEEPSLATE_GOLD_ORE) {
            // スコア計算ロジックをここに追加
            ScoreCalculation.addScore(event.getPlayer(), 5, "Ore");
        }
        if (event.getBlock().getType() == Material.IRON_ORE || event.getBlock().getType() == Material.DEEPSLATE_IRON_ORE) {
            // スコア計算ロジックをここに追加
            ScoreCalculation.addScore(event.getPlayer(), 7, "Ore");
        }
        if (event.getBlock().getType() == Material.COAL_ORE || event.getBlock().getType() == Material.DEEPSLATE_COAL_ORE) {
            // スコア計算ロジックをここに追加
            ScoreCalculation.addScore(event.getPlayer(), 2, "Ore");
        }
    }
}