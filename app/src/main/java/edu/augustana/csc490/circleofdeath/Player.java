package edu.augustana.csc490.circleofdeath;

public class Player {

    private String playerName;
    private boolean isQuestionMaster;

    public Player(String player) {
        playerName = player;
    }

    public void setQuestionMaster(boolean isIt) {
        isQuestionMaster = isIt;
    }

    public String toString() {
        return playerName;
    }

}