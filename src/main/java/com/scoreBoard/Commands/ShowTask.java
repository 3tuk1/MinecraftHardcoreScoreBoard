package com.scoreBoard.Commands;

import com.scoreBoard.Data.ScoreData;
import com.scoreBoard.Data.ScoreDataManage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;

public class ShowTask {
    private static ShowTask instance;
    private final Map<Player, ViewTask> guitask = new HashMap<>();
    private class ViewTask {
        public boolean isTop;
        public boolean isToponly;
    }

    private ShowTask() {
    }

    public synchronized void showTaskAdd(Player player, boolean istop, boolean istoponly) {
        ViewTask viewTask = new ViewTask();
        viewTask.isTop = istop;
        viewTask.isToponly = istoponly;
        this.guitask.put(player, viewTask);
    }

    public synchronized void showTaskRemove(Player player) {
        this.guitask.remove(player);
        showEmpty(player);
    }

    public static ShowTask getInstance() {
        if (instance == null) {
            instance = new ShowTask();
        }
        return instance;
    }

    public synchronized boolean isGuiTask(Player player) {
        return guitask.containsKey(player);
    }

    private void showEmpty(Player player) {
        // スコアボードを削除
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public synchronized void showScoreboard() {
        // Mapの中身が空の場合は処理を終了
        if (guitask.isEmpty()) {
            return;
        }
        for (Map.Entry<Player, ViewTask> entry : guitask.entrySet()) {
            Player player = entry.getKey();
            ViewTask viewTask = entry.getValue();
            boolean isTop = viewTask.isTop;
            boolean isToponly = viewTask.isToponly;

            // スコアボードマネージャを取得
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = manager.getNewScoreboard();

            // スコアボードのタイトルと表示スロットを設定
            Objective objective = scoreboard.registerNewObjective("scoreboard", "dummy", ChatColor.GOLD + "スコアボード");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            // スコアデータを取得
            ScoreData scoreData = ScoreDataManage.getInstance().getScoreData(player.getName());
            if (scoreData == null) {
                player.sendMessage("スコアデータが見つかりません。");
                return;
            }
            if(!isToponly) {
                Score mobScore = objective.getScore(ChatColor.GREEN + "モブスコア: " + ChatColor.WHITE + scoreData.getMobscore());
                mobScore.setScore(8);

                Score oreScore = objective.getScore(ChatColor.GREEN + "鉱石スコア: " + ChatColor.WHITE + scoreData.getOreScore());
                oreScore.setScore(7);

                Score moveScore = objective.getScore(ChatColor.GREEN + "移動スコア: " + ChatColor.WHITE + (int) scoreData.getMoveScore());
                moveScore.setScore(6);

                Score deathScore = objective.getScore(ChatColor.GREEN + "デススコア: " + ChatColor.WHITE + scoreData.getDeathScore());
                deathScore.setScore(5);

                Score totalScore = objective.getScore(ChatColor.GREEN + "合計スコア: " + ChatColor.WHITE + (int) scoreData.getTotalscore());
                totalScore.setScore(4);
            }else {
                // 自分の順位とスコアを表示
                Score myScore = objective.getScore(ChatColor.GREEN +"" + ScoreDataManage.getInstance().getRank(player) + "位: " + ChatColor.WHITE + (int) scoreData.getTotalscore());
                myScore.setScore(4);
            }
            if (isTop) {
                int rank = 1;
                for (ScoreData topScoreData : ScoreDataManage.getInstance().getTop3()) {
                    ChatColor color;
                    switch (rank) {
                        case 1:
                            color = ChatColor.GOLD; // 金
                            break;
                        case 2:
                            color = ChatColor.GRAY; // 銀
                            break;
                        case 3:
                            color = ChatColor.DARK_RED; // 銅
                            break;
                        default:
                            color = ChatColor.WHITE;
                    }
                    Score topScore = objective.getScore(color + "Top" + rank + ": " + topScoreData.getPlayerName() + " - " + (int) topScoreData.getTotalscore());
                    topScore.setScore(rank); // スコアを設定
                    rank++;
                }
            }

            // プレイヤーにスコアボードを表示
            player.setScoreboard(scoreboard);
        }
    }
}