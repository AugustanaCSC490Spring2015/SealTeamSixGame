package edu.augustana.csc490.circleofdeath;

public class Player {

    private String playerName;
    private boolean isRuleMaster;
    private boolean isQuestionMaster;
    private boolean isThumbMaster;


    public Player(String player) {
        playerName = player;
    }

    public String getName() {
        return playerName;
    }

    public boolean getQuestionMaster() { return isQuestionMaster; }

    public void setQuestionMaster(boolean isIt) {
        isQuestionMaster = isIt;
    }

    public boolean getRuleMaster() { return isRuleMaster; }

    public void setRuleMaster(boolean isIt) { isRuleMaster = isIt; }

    public boolean getThumbMaster() { return isThumbMaster; }

    public void setThumbMaster(boolean isIt) { isThumbMaster = isIt; }

}