package edu.augustana.csc490.circleofdeath;

import java.util.ArrayList;

public final class GameManager {

    static ArrayList<Player> players;
    // static List<Rule> rules; --Future
    static Deck deck;
    static int currentPlayer;

    public static void addPlayer(String name){ players.add(new Player(name)); }

    public static int getPlayersSize(){ return players.size(); }

    public static void incrementCurrentPlayer(){
        if (currentPlayer == players.size() - 1){
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    }

    public static String getCurrentPlayerName(){ return players.get(currentPlayer).getName(); }

    public static void setCurrentPlayerAsQuestionMaster(){
        for (Player p : players){
            p.setQuestionMaster(false);
        }
        players.set(currentPlayer, players.get(currentPlayer)).setQuestionMaster(true);
    }

    public static boolean isCurrentPlayerQuestionMaster(){
        return players.get(currentPlayer).getQuestionMaster();
    }

}
