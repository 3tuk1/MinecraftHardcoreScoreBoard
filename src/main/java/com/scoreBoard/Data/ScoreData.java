package com.scoreBoard.Data;

public class ScoreData {
    private String playerName;
    private double totalscore;
    private int Mobscore;
    private int OreScore;
    private double MoveScore;
    private int deathCount;
    private double otherScore;

    public ScoreData(String playerName, int Mobscore, int OreScore , double MoveScore, int deathCount,double otherScore) {
        this.playerName = playerName;
        this.Mobscore = Mobscore;
        this.OreScore = OreScore;
        this.MoveScore = MoveScore;
        this.deathCount = deathCount;
        this.otherScore = otherScore;
    }

    public String getPlayerName() {
        return playerName;
    }


    public double getTotalscore() {
        double BasicScore = (Mobscore + OreScore -(deathCount * 1000));
        totalscore = (( BasicScore * ( 1 + MoveScore / 100)) + otherScore) / 4;
        return totalscore;
    }

    public int getMobscore() {
        return Mobscore;
    }

    public void setMobscore(int Mobscore) {
        this.Mobscore = Mobscore;
    }

    public int getOreScore() {
        return OreScore;
    }

    public void setOreScore(int OreScore) {
        this.OreScore = OreScore;
    }

    public double getMoveScore() {
        return MoveScore;
    }

    public int getDeathScore() {
        return -deathCount*500;
    }

    public void setMoveScore(double MoveScore) {
        this.MoveScore = MoveScore;
    }

    public void setOtherScore(double otherScore) {
        this.otherScore = otherScore;
    }

    public double getOtherScore() {
        return otherScore;
    }

}
