package edu.augustana.csc490.circleofdeath;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import edu.augustana.csc490.circleofdeath.enums.Number;

public final class GameManager {

    static ArrayList<Player> players;
    static int currentPlayer;

    static HashMap<Number, String> defaultRules;
    static HashMap<Number, String> rules;

    static Deck deck;

    // Deck mode spinner flags
    public static final int DECK_SINGLE_MODE = 0;
    public static final int DECK_ENDLESS_MODE = 1;

    // Break circle mode spinner flags
    public static final int BREAK_CIRCLE_RANDOM = 0;
    public static final int BREAK_CIRCLE_AFTER_TURN_20 = 1;

    // Pop tab mode spinner flags
    public static final int POP_TAB_RANDOM = 0;
    public static final int POP_TAB_AFTER_TURN_20 = 1;

    // Set default game modes
    private static int gameMode = DECK_SINGLE_MODE;
    private static int breakCircleMode = BREAK_CIRCLE_RANDOM;
    private static int popTabMode = POP_TAB_RANDOM;

    public static void addPlayer(String name){ players.add(new Player(name)); }

    public static int getNumberOfPlayers(){ return players.size(); }

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
    public static void setCurrentPlayerAsRuleMaster(){
        for (Player p : players){
            p.setRuleMaster(false);
        }
        players.set(currentPlayer, players.get(currentPlayer)).setRuleMaster(true);
    }
    public static void setCurrentPlayerAsThumbMaster(){
        for (Player p : players){
            p.setThumbMaster(false);
        }
        players.set(currentPlayer, players.get(currentPlayer)).setThumbMaster(true);
    }

    public static boolean isCurrentPlayerQuestionMaster(){ return players.get(currentPlayer).getQuestionMaster(); }

    public static boolean isCurrentPlayerRuleMaster(){ return players.get(currentPlayer).getRuleMaster(); }

    public static boolean isCurrentPlayerThumbMaster(){ return players.get(currentPlayer).getThumbMaster(); }

    public static String getRule(Card card){ return rules.get(card.getNumber()); }

    // Method loads up default rules from strings.xml into the default rules Map
    public static void loadDefaultRules(Context context){

        defaultRules.put(Number.ACE, context.getResources().getString(R.string.default_ace));
        defaultRules.put(Number.KING, context.getResources().getString(R.string.default_king));
        defaultRules.put(Number.QUEEN,context.getResources().getString(R.string.default_queen));
        defaultRules.put(Number.JACK,context.getResources().getString(R.string.default_jack));
        defaultRules.put(Number.TEN,context.getResources().getString(R.string.default_ten));
        defaultRules.put(Number.NINE,context.getResources().getString(R.string.default_nine));
        defaultRules.put(Number.EIGHT,context.getResources().getString(R.string.default_eight));
        defaultRules.put(Number.SEVEN,context.getResources().getString(R.string.default_seven));
        defaultRules.put(Number.SIX,context.getResources().getString(R.string.default_six));
        defaultRules.put(Number.FIVE,context.getResources().getString(R.string.default_five));
        defaultRules.put(Number.FOUR,context.getResources().getString(R.string.default_four));
        defaultRules.put(Number.THREE,context.getResources().getString(R.string.default_three));
        defaultRules.put(Number.TWO,context.getResources().getString(R.string.default_two));
    }

    // Method sets the current player to whichever card master if the card is drawn
    public static void setMasters(Card card){
        if (GameManager.getNumberOfPlayers() !=0 ){
            if (card.getNumber().equals(Number.QUEEN)) {
                GameManager.setCurrentPlayerAsQuestionMaster();
            }
            if (card.getNumber().equals(Number.KING)) {
                GameManager.setCurrentPlayerAsRuleMaster();
            }
            if (card.getNumber().equals(Number.JACK)) {
                GameManager.setCurrentPlayerAsThumbMaster();
            }
        }
    }

    public static int getGameMode() {
        return gameMode;
    }

    public static void setGameMode(int mode) {
        gameMode = mode;
    }

    public static int getBreakCircleMode() {
        return breakCircleMode;
    }

    public static void setBreakCircleMode(int mode) {
        breakCircleMode = mode;
    }

    public static int getPopTabMode() {
        return popTabMode;
    }

    public static void setPopTabMode(int mode) {
        popTabMode = mode;
    }

}
