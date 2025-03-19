package com.scoreBoard.Listener;

import com.scoreBoard.ScoreCalc.ScoreCalculation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.Material;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(!event.getBlock().hasMetadata("placedByPlayer")) {
            if (event.getBlock().getType() == Material.DIAMOND_ORE || event.getBlock().getType() == Material.DEEPSLATE_DIAMOND_ORE) {
                // スコア計算ロジックをここに追加
                ScoreCalculation.addScore(event.getPlayer(), 50, "Ore");
            }
            if (event.getBlock().getType() == Material.EMERALD_ORE || event.getBlock().getType() == Material.DEEPSLATE_EMERALD_ORE) {
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
            if (event.getBlock().getType() == Material.LAPIS_ORE || event.getBlock().getType() == Material.DEEPSLATE_LAPIS_ORE) {
                // スコア計算ロジックをここに追加
                ScoreCalculation.addScore(event.getPlayer(), 3, "Ore");
            }
            if (event.getBlock().getType() == Material.REDSTONE_ORE || event.getBlock().getType() == Material.DEEPSLATE_REDSTONE_ORE) {
                // スコア計算ロジックをここに追加
                ScoreCalculation.addScore(event.getPlayer(), 1, "Ore");
            }
            if (event.getBlock().getType() == Material.COPPER_ORE || event.getBlock().getType() == Material.DEEPSLATE_COPPER_ORE) {
                // スコア計算ロジックをここに追加
                ScoreCalculation.addScore(event.getPlayer(), 1, "Ore");
            }
            if (event.getBlock().getType() == Material.ANCIENT_DEBRIS) {
                // スコア計算ロジックをここに追加
                ScoreCalculation.addScore(event.getPlayer(), 300, "Ore");
            }
            // 原木の場合のスコア加算
            if (event.getBlock().getType() == Material.OAK_LOG || event.getBlock().getType() == Material.SPRUCE_LOG ||
                    event.getBlock().getType() == Material.BIRCH_LOG || event.getBlock().getType() == Material.JUNGLE_LOG ||
                    event.getBlock().getType() == Material.ACACIA_LOG || event.getBlock().getType() == Material.DARK_OAK_LOG) {
                // スコア計算ロジックをここに追加
                ScoreCalculation.addScore(event.getPlayer(), 2, "Other");
            }
        }
    }
}