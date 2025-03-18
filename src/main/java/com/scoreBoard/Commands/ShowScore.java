package com.scoreBoard.Commands;

import com.scoreBoard.Data.ScoreData;
import com.scoreBoard.Data.ScoreDataManage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.HashMap;
import java.util.Map;

public class ShowScore implements CommandExecutor {
    private final JavaPlugin plugin;
    private Map<String, Boolean> isGui = new HashMap<>();
    private BukkitTask Tasks;
    public ShowScore(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("プレイヤー名を指定してください。");
            return false;
        }

        if (args[0].equalsIgnoreCase("all")) {
            showAllScores(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("me")) {
            showIndividualScore((Player) sender);
            return true;
        }

        // ランキング1位から３位まで表示
        if (args[0].equalsIgnoreCase("top")) {
            sender.sendMessage("ランキング1位から3位までのスコア:");
            ScoreDataManage.getInstance().getTop3().forEach(scoreData -> {
                sender.sendMessage(scoreData.getPlayerName() + " : " + scoreData.getTotalscore());
            });
            return true;
        }

        if(args[0].equals("gui")) {
            // GUI表示処理をここに追加
            if (isGui.get(sender.getName()) != null && isGui.get(sender.getName())) {
                sender.sendMessage("既にスコアボードを表示しています。");
                return true;
            }
            Tasks = new BukkitRunnable() {
                // スコアボードを表示する処理をここに追加
                @Override
                public void run() {
                    showScoreboard((Player) sender);
                }
            }.runTaskTimer(plugin,0L, 20L);

            isGui.put(sender.getName(), true);

            return true;
        }

        if(args[0].equals("nogui")) {
            // GUIを閉じる処理をここに追加
            if (isGui.get(sender.getName()) == null || !isGui.get(sender.getName())) {
                sender.sendMessage("スコアボードが表示されていません。");
                return true;
            }
            Tasks.cancel();
            Player player = (Player) sender;
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            isGui.put(sender.getName(), false);
            return true;
        }

        if(args[0].equals("help")) {
            sender.sendMessage("スコア表示コマンドの使い方:");
            sender.sendMessage("/showscore <プレイヤー名> : 指定したプレイヤーのスコアを表示します。");
            sender.sendMessage("/showscore all : 全員のスコアを表示します。");
            sender.sendMessage("/showscore me : 自分のスコアを表示します。");
            sender.sendMessage("/showscore top : ランキング1位から3位までのスコアを表示します。");
            sender.sendMessage("/showscore gui : スコアボードを表示します。");
            return true;
        }

        String playerName = args[0];
        ScoreData scoreData = ScoreDataManage.getInstance().getScoreData(playerName);

        if (scoreData == null) {
            sender.sendMessage("指定されたプレイヤーのスコアデータが見つかりません。");
            return true;
        }
        showIndividualScore((Player) sender);

        return true;
    }

    // 個人スコア表示処理をここに追加
    private void showIndividualScore(Player player) {
        ScoreData scoreData = ScoreDataManage.getInstance().getScoreData(player.getName());
        if (scoreData != null) {
            player.sendMessage(player.getName() + "のスコア:");
            player.sendMessage("Mobスコア: " + scoreData.getMobscore());
            player.sendMessage("Oreスコア: " + scoreData.getOreScore());
            player.sendMessage("Moveスコア: " + scoreData.getMoveScore());
            player.sendMessage("デススコア: " + scoreData.getDeathScore());
            player.sendMessage("合計スコア: " + scoreData.getTotalscore());
        } else {
            player.sendMessage("スコアデータが見つかりません。");
        }
    }

    // 全員のスコア表示処理をここに追加
    private void showAllScores(CommandSender sender) {
        for (ScoreData scoreData : ScoreDataManage.getInstance().getScoreDataList()) {
            sender.sendMessage("====================================");
            sender.sendMessage(scoreData.getPlayerName() + "のスコア:");
            sender.sendMessage("Mobスコア: " + scoreData.getMobscore());
            sender.sendMessage("Oreスコア: " + scoreData.getOreScore());
            sender.sendMessage("Moveスコア: " + scoreData.getMoveScore());
            sender.sendMessage("デススコア: " + scoreData.getDeathScore());
            sender.sendMessage("合計スコア: " + scoreData.getTotalscore());
        }
    }

    private void showScoreboard(Player player) {


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
            Score mobScore = objective.getScore(ChatColor.GREEN + "モブスコア: " + ChatColor.WHITE + scoreData.getMobscore());
            mobScore.setScore(5);

            Score oreScore = objective.getScore(ChatColor.GREEN + "鉱石スコア: " + ChatColor.WHITE + scoreData.getOreScore());
            oreScore.setScore(4);

            Score moveScore = objective.getScore(ChatColor.GREEN + "移動スコア: " + ChatColor.WHITE + (int) scoreData.getMoveScore());
            moveScore.setScore(3);

            Score deathScore = objective.getScore(ChatColor.GREEN + "デススコア: " + ChatColor.WHITE  + scoreData.getDeathScore());
            deathScore.setScore(2);

            Score totalScore = objective.getScore(ChatColor.GREEN + "合計スコア: " + ChatColor.WHITE + (int) scoreData.getTotalscore());
            totalScore.setScore(1);

            // プレイヤーにスコアボードを表示
            player.setScoreboard(scoreboard);
    }

}