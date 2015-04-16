package edu.augustana.csc490.circleofdeath;

import java.util.ArrayList;

/**
 * Created by cschroeder on 4/16/15.
 */
public class Players {

    private ArrayList<String> players;
    private boolean isQuestionMaster;

    private int currentPlayer;

    public Players(){
        players = new ArrayList<>();
        isQuestionMaster = false;
    }

    public void addPlayer(String name){

        if (!players.contains(name) && !name.equals("") && name != null){
            players.add(name);
        }
    }

    public ArrayList<String> getPlayerArray(){
        return players;
    }

    public void setQuestionMaster(boolean isIt){
        isQuestionMaster = isIt;
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }

    public void incrementCurrentPlayer(){
        if (currentPlayer == players.size()){
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    }

    public String getPlayerName(int id){
        return players.get(id);
    }

}