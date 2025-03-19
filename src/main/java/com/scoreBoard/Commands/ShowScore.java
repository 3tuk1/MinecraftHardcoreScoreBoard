package com.scoreBoard.Commands;

import com.scoreBoard.Data.ScoreData;
import com.scoreBoard.Data.ScoreDataManage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ShowScore implements CommandExecutor {
    private static final ShowTask showTask = ShowTask.getInstance();

    public ShowScore(JavaPlugin plugin) {
        // スコアボード表示タスクを一秒ごとに実行
        new BukkitRunnable() {
            @Override
            public void run() {
                showTask.showScoreboard();
            }
        }.runTaskTimer(plugin, 0L, 20L);
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

        if (args[0].equals("gui")) {
            // GUI表示処理をここに追加
            if(showTask.isGuiTask((Player) sender)) {
                sender.sendMessage("スコアボードを非表示にします。");
                showTask.showTaskRemove((Player) sender);
                return true;
            }

            if (args.length == 2 && args[1].equals("top")) {
                showTask.showTaskAdd((Player) sender, true);
            } else {
                showTask.showTaskAdd((Player) sender, false);
            }
            return true;
        }

        if (args[0].equals("nogui")) {

            return true;
        }

        if (args[0].equals("help")) {
            sender.sendMessage("スコア表示コマンドの使い方:");
            sender.sendMessage("/showscore <プレイヤー名> : 指定したプレイヤーのスコアを表示します。");
            sender.sendMessage("/showscore all : 全員のスコアを表示します。");
            sender.sendMessage("/showscore me : 自分のスコアを表示します。");
            sender.sendMessage("/showscore top : ランキング1位から3位までのスコアを表示します。");
            sender.sendMessage("/showscore gui : スコアボードを表示します。");
            sender.sendMessage("/showscore nogui : スコアボードを非表示にします。");
            sender.sendMessage("/showscore gui top : スコアボードにランキングを表示します。");
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
            player.sendMessage("Moveスコア: " +(int) scoreData.getMoveScore());
            player.sendMessage("デススコア: " + scoreData.getDeathScore());
            player.sendMessage("合計スコア: " +(int) scoreData.getTotalscore());
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
            sender.sendMessage("Oreスコア: " +  scoreData.getOreScore());
            sender.sendMessage("Moveスコア: " + (int) scoreData.getMoveScore());
            sender.sendMessage("デススコア: " + scoreData.getDeathScore());
            sender.sendMessage("合計スコア: " + (int) scoreData.getTotalscore());
        }
    }





}