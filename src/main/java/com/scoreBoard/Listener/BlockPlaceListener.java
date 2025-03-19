package com.scoreBoard.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.metadata.FixedMetadataValue;
import com.scoreBoard.ScoreBoard;

public class BlockPlaceListener implements Listener {
    private final ScoreBoard plugin;

    public BlockPlaceListener(ScoreBoard plugin) {
        this.plugin = plugin;
    }

    // プレイヤーが原木や鉱石の原石を設置したときにメタデータを設定
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.OAK_LOG || block.getType() == Material.SPRUCE_LOG ||
                block.getType() == Material.BIRCH_LOG || block.getType() == Material.JUNGLE_LOG ||
                block.getType() == Material.ACACIA_LOG || block.getType() == Material.DARK_OAK_LOG ||
                block.getType() == Material.COAL_ORE || block.getType() == Material.IRON_ORE ||
                block.getType() == Material.GOLD_ORE || block.getType() == Material.LAPIS_ORE ||
                block.getType() == Material.DIAMOND_ORE || block.getType() == Material.EMERALD_ORE ||
                block.getType() == Material.ANCIENT_DEBRIS || block.getType() == Material.REDSTONE_ORE ||
                block.getType() == Material.COPPER_ORE || block.getType() == Material.DEEPSLATE_COAL_ORE ||
                block.getType() == Material.DEEPSLATE_IRON_ORE || block.getType() == Material.DEEPSLATE_GOLD_ORE ||
                block.getType() == Material.DEEPSLATE_LAPIS_ORE || block.getType() == Material.DEEPSLATE_DIAMOND_ORE ||
                block.getType() == Material.DEEPSLATE_EMERALD_ORE || block.getType() == Material.DEEPSLATE_REDSTONE_ORE ||
                block.getType() == Material.DEEPSLATE_COPPER_ORE)
                {
            block.setMetadata("placedByPlayer", new FixedMetadataValue(plugin, true));
        }
    }


}