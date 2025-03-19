package com.scoreBoard.Data;

import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
                saveScoreData(scoreData.getPlayerName());
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



    public void saveScoreData(String playerName) {
        File file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("ScoreBoard")).getDataFolder(), "ScoreData.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        for (ScoreData scoreData : scoreDataList) {
            if (scoreData.getPlayerName().equals(playerName)) {
                yamlConfiguration.set(playerName + ".mobScore", scoreData.getMobscore());
                yamlConfiguration.set(playerName + ".oreScore", scoreData.getOreScore());
                yamlConfiguration.set(playerName + ".moveScore", scoreData.getMoveScore());
                yamlConfiguration.set(playerName + ".deathScore", scoreData.getDeathScore());
                yamlConfiguration.set(playerName + ".otherScore", scoreData.getOtherScore());
                yamlConfiguration.set(playerName + ".totalScore", scoreData.getTotalscore());
                break;
            }
        }

        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ScoreData> getTop3() {
        return scoreDataList.stream()
                .sorted((a, b) -> Double.compare(b.getTotalscore(), a.getTotalscore()))
                .limit(3)
                .collect(Collectors.toList());
    }

    // プレイヤーの順位取得する
    public int getRank(Player player) {
        List<ScoreData> sortedList = scoreDataList.stream()
                .sorted((a, b) -> Double.compare(b.getTotalscore(), a.getTotalscore()))
                .toList();
        for (int i = 0; i < sortedList.size(); i++) {
            if (sortedList.get(i).getPlayerName().equals(player.getName())) {
                return i + 1;
            }
        }
        return -1;
    }
}
