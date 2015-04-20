package edu.augustana.csc490.circleofdeath;

import java.util.ArrayList;

public final class GameManager {

    static ArrayList<String> players;  // TODO: make it an ArrayList<Player>, but need to change Array Adapter in MenuActivity
    // static List<Rule> rules; --Future
    static Deck deck;
    static int currentPlayer;

    public static void addPlayer(String name){ players.add(name); }

    public static int getPlayersSize(){ return players.size(); }

    public static void incrementCurrentPlayer(){
        if (currentPlayer == players.size() - 1){
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    }

    public static String getCurrentPlayerName(){ return players.get(currentPlayer).toString(); }

}
