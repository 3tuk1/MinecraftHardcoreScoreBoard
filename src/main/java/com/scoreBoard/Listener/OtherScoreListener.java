package com.scoreBoard.Listener;

import com.scoreBoard.ScoreCalc.ScoreCalculation;
import org.bukkit.Material;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class OtherScoreListener implements Listener {

    private final Map<Player, Integer> blockBreakCount = new HashMap<>();
    private final Map<Player, Integer> craftCount = new HashMap<>();

    // 農作業によるポイントの上昇
    @EventHandler
    public void onHarvest(PlayerHarvestBlockEvent event) {
        Player player = event.getPlayer();
        if (Math.random() < 0.7)
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
        if (event.getAdvancement().getKey().getKey().startsWith("recipes/")) {
            // レシピの追加はポイントの上昇
            Player player = event.getPlayer();
            ScoreCalculation.addScore(player, 2, "Other");
        }else {
            Player player = event.getPlayer();
            ScoreCalculation.addScore(player, 30, "Other");
        }
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

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        // 作業台での作業によるポイントの上昇
        if (event.getView().getPlayer() instanceof Player) {
            Player player = (Player) event.getView().getPlayer();
            craftCount.put(player, craftCount.getOrDefault(player, 0) + 1);
            if (craftCount.get(player) >= 10) {
                ScoreCalculation.addScore(player, 1, "Other");
                craftCount.put(player, 0); // カウントをリセット
            }
        }
        // ジュークボックスの作成によるポイントの減少
        ItemStack result = event.getInventory().getResult();
        if (result != null && result.getType() == Material.JUKEBOX) {
            if(Math.random() < 0.5)
                ScoreCalculation.addScore((Player) event.getView().getPlayer(), -300, "Other");
        }
    }


}