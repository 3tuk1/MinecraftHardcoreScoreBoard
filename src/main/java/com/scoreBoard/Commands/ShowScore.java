package com.scoreBoard.Commands;

import com.scoreBoard.Data.ScoreData;
import com.scoreBoard.Data.ScoreDataManage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShowScore implements CommandExecutor {
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

}