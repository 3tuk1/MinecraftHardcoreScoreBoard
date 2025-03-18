package com.scoreBoard.Data;

import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.Objects;

public class ScoreDataManage {
    private static ScoreDataManage instance;
    private ArrayList<ScoreData> scoreDataList;
    public static ScoreDataManage getInstance() {
        if (instance == null) {
            instance = new ScoreDataManage();
        }
        return instance;
    }

    private ScoreDataManage() {
        scoreDataList = new ArrayList<ScoreData>();
    }

    // スコアデータを追加する
    public void addScoreData(ScoreData scoreData) {
        scoreDataList.add(scoreData);
    }

    // スコアデータを変更する
    public void setScoreData(ScoreData scoreData) {
        for (ScoreData data : scoreDataList) {
            if (data.getPlayerName().equals(scoreData.getPlayerName())) {
                data.setMobscore(scoreData.getMobscore());
                data.setOreScore(scoreData.getOreScore());
                data.setMoveScore(scoreData.getMoveScore());
                saveScoreData();
                return;
            }
        }
    }

    // スコアデータを取得する
    public ScoreData getScoreData(String playerName) {
        for (ScoreData scoreData : scoreDataList) {
            if (scoreData.getPlayerName().equals(playerName)) {
                return scoreData;
            }
        }
        return null;
    }

    // スコアデータリストを取得する
    public ArrayList<ScoreData> getScoreDataList() {
        return scoreDataList;
    }



    public void saveScoreData() {
        File file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("ScoreBoard")).getDataFolder(), "ScoreData.yml");
        YamlConfiguration yamlConfiguration = new YamlConfiguration();

        for (ScoreData scoreData : scoreDataList) {
            String playerName = scoreData.getPlayerName();
            yamlConfiguration.set(playerName + ".mobScore", scoreData.getMobscore());
            yamlConfiguration.set(playerName + ".oreScore", scoreData.getOreScore());
            yamlConfiguration.set(playerName + ".moveScore", scoreData.getMoveScore());
            yamlConfiguration.set(playerName + ".deathScore", scoreData.getDeathScore());
            yamlConfiguration.set(playerName + ".totalScore", scoreData.getTotalscore());
        }

        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
