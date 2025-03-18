package com.scoreBoard.ScoreCalc;

import com.scoreBoard.Data.ScoreData;
import com.scoreBoard.Data.ScoreDataManage;
import org.bukkit.entity.Player;

public class ScoreCalculation {
    private static final int MoveScoremagnification = 30;
    private ScoreCalculation() {
    }
    public static void addScore(Player player, int score, String type) {
        // スコアを加算する処理をここに追加
        ScoreDataManage SDM = ScoreDataManage.getInstance();
        ScoreData scoredata = SDM.getScoreData(player.getName());
        switch (type) {
            case "Mob" -> scoredata.setMobscore(scoredata.getMobscore() + score);
            case "Ore" -> scoredata.setOreScore(scoredata.getOreScore() + score);
            case "Move" -> scoredata.setMoveScore(scoredata.getMoveScore() + ((double) score /MoveScoremagnification));
        }
        SDM.setScoreData(scoredata);
    }

    public static void DeathScore(Player player) {
        // スコアを加算する処理をここに追加
        ScoreDataManage SDM = ScoreDataManage.getInstance();

    }


}
