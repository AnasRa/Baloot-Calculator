package com.example.android;

public class Score {

    private int teamOneScore;
    private int teamTwoScore;
    private int prevTeamOneScore;
    private int PrevTeamTwoScore;

    public int getPrevTeamOneScore() {
        return prevTeamOneScore;
    }

    public int getPrevTeamTwoScore() {
        return PrevTeamTwoScore;
    }

    public int getTeamOneScore() {
        return teamOneScore;
    }

    public int getTeamTwoScore() {
        return teamTwoScore;
    }

    public Score(int teamOneScore, int teamTwoScore, int prevTeamOneScore, int prevTeamTwoScore) {
        this.teamOneScore = teamOneScore;
        this.teamTwoScore = teamTwoScore;
        this.prevTeamOneScore = prevTeamOneScore;
        PrevTeamTwoScore = prevTeamTwoScore;
    }

    public int getTotal(){
        return teamOneScore+teamTwoScore;
    }
}
