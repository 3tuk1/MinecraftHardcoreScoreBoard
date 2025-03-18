package com.scoreBoard;

import com.scoreBoard.Commands.ShowScore;
import com.scoreBoard.Data.ScoreData;
import com.scoreBoard.Data.ScoreDataManage;
import com.scoreBoard.Listener.MobKillListener;
import com.scoreBoard.Listener.MoveListener;
import com.scoreBoard.Listener.SpawnerSpawnListener;
import com.scoreBoard.ScoreCalc.OreBreakListener;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public final class ScoreBoard extends JavaPlugin implements Listener {
    private File scoreFile;
    private FileConfiguration scoreConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new SpawnerSpawnListener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(this), this);
        getServer().getPluginManager().registerEvents(new OreBreakListener(), this);
        getServer().getPluginManager().registerEvents(new MobKillListener(), this);
        getServer().getPluginManager().registerEvents(this, this);

        // プラグインフォルダにファイルがなければ作成
        File scoreFile = new File(getDataFolder(), "ScoreData.yml");
        if (!scoreFile.exists()) {
            try {
                scoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // コマンドの登録
        this.getCommand("showscore").setExecutor(new ShowScore());
        // ログ　プラグインが有効になったことを通知
        getLogger().info("ScoreBoard Plugin Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ScoreData scoreData = ScoreDataManage.getInstance().getScoreData(event.getPlayer().getName());
        if (scoreData == null) {
            String playerName = event.getPlayer().getName();
            loadScoreConfig();
            int mobScore = scoreConfig.getInt(playerName + ".mobScore");
            int oreScore = scoreConfig.getInt(playerName + ".oreScore");
            double moveScore = scoreConfig.getDouble(playerName + ".moveScore");
            int deathscore = loadDeathScoreConfig(playerName);
            scoreData = new ScoreData(playerName, mobScore, oreScore, moveScore, deathscore);
            // それぞれのスコアを表示
            getLogger().info("PlayerName: " + playerName + " MobScore: " + mobScore + " OreScore: " + oreScore + " MoveScore: " + moveScore + " DeathScore: " + deathscore);
            ScoreDataManage.getInstance().addScoreData(scoreData);
        }
    }

    private void loadScoreConfig() {
        // プラグインフォルダにファイルがなければ作成
        scoreFile = new File(getDataFolder(), "ScoreData.yml");
        if (!scoreFile.exists()) {
            saveResource("ScoreData.yml", false);
        }
        scoreConfig = YamlConfiguration.loadConfiguration(scoreFile);
        // デバッグメッセージ
        //getLogger().info("ScoreData.yml content: " + scoreConfig.saveToString());
    }

    private int loadDeathScoreConfig( String playerName) {
        // プラグインフォルダにファイルがなければ作成
        File DscoreFile = new File(getDataFolder().getParentFile(), "WorldResetPlugin/death_records.yml");
        if (!DscoreFile.exists()) {
            try {
                DscoreFile.createNewFile();
            } catch (IOException e) {
                getLogger().info("DeathRecords.ymlファイルが見つかりませんでした。");
                e.printStackTrace();
            }

        }
        FileConfiguration deathScoreConfig = YamlConfiguration.loadConfiguration(DscoreFile);
        return deathScoreConfig.getInt(playerName + ".deaths");

    }
}
