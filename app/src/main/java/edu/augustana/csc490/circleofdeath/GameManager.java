package edu.augustana.csc490.circleofdeath;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.augustana.csc490.circleofdeath.enums.Number;

public final class GameManager {

    static ArrayList<Player> players;
    static int currentPlayer;

    static HashMap<Number, String> defaultRules;
    static HashMap<Number, String> rules;
    static int turn = 0;
    static boolean tabIsPopped = false;
    static Deck deck;

    // Deck mode spinner flags
    public static final int DECK_SINGLE_MODE = 0;
    public static final int DECK_ENDLESS_MODE = 1;

    // Pop tab mode spinner flags
    public static final int POP_TAB_RANDOM = 0;
    public static final int POP_TAB_AFTER_TURN_20 = 1;

    // Set default game modes
    private static int gameMode = DECK_SINGLE_MODE;
    private static int popTabMode = POP_TAB_RANDOM;

    public static void addPlayer(String name) {
        players.add(new Player(name));
    }

    public static int getNumberOfPlayers() {
        return players.size();
    }

    public static boolean containsPlayer(String name) {
        // loop through players and check names
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void incrementCurrentPlayer() {
        if (currentPlayer == players.size() - 1) {
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    }

    public static String getCurrentPlayerName() {
        return players.get(currentPlayer).getName();
    }

    public static void setCurrentPlayerAsQuestionMaster() {
        for (Player p : players) {
            p.setQuestionMaster(false);
        }
        players.set(currentPlayer, players.get(currentPlayer)).setQuestionMaster(true);
    }

    public static void setCurrentPlayerAsRuleMaster() {
        for (Player p : players) {
            p.setRuleMaster(false);
        }
        players.set(currentPlayer, players.get(currentPlayer)).setRuleMaster(true);
    }

    public static void setCurrentPlayerAsThumbMaster() {
        for (Player p : players) {
            p.setThumbMaster(false);
        }
        players.set(currentPlayer, players.get(currentPlayer)).setThumbMaster(true);
    }

    public static boolean isCurrentPlayerQuestionMaster() {
        return players.get(currentPlayer).getQuestionMaster();
    }

    public static boolean isCurrentPlayerRuleMaster() {
        return players.get(currentPlayer).getRuleMaster();
    }

    public static boolean isCurrentPlayerThumbMaster() {
        return players.get(currentPlayer).getThumbMaster();
    }

    public static String getRule(Card card) {
        return rules.get(card.getNumber());
    }

    // Method loads up default rules from strings.xml into the default rules Map
    public static void loadDefaultRules(Context context) {

        defaultRules.put(Number.ACE, context.getResources().getString(R.string.default_ace));
        defaultRules.put(Number.KING, context.getResources().getString(R.string.default_king));
        defaultRules.put(Number.QUEEN, context.getResources().getString(R.string.default_queen));
        defaultRules.put(Number.JACK, context.getResources().getString(R.string.default_jack));
        defaultRules.put(Number.TEN, context.getResources().getString(R.string.default_ten));
        defaultRules.put(Number.NINE, context.getResources().getString(R.string.default_nine));
        defaultRules.put(Number.EIGHT, context.getResources().getString(R.string.default_eight));
        defaultRules.put(Number.SEVEN, context.getResources().getString(R.string.default_seven));
        defaultRules.put(Number.SIX, context.getResources().getString(R.string.default_six));
        defaultRules.put(Number.FIVE, context.getResources().getString(R.string.default_five));
        defaultRules.put(Number.FOUR, context.getResources().getString(R.string.default_four));
        defaultRules.put(Number.THREE, context.getResources().getString(R.string.default_three));
        defaultRules.put(Number.TWO, context.getResources().getString(R.string.default_two));
    }

    // Method sets the current player to whichever card master if the card is drawn
    public static void setMasters(Card card) {
        if (GameManager.getNumberOfPlayers() != 0) {
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

    public static int getPopTabMode() {
        return popTabMode;
    }

    public static void setPopTabMode(int mode) {
        popTabMode = mode;
    }

    public static void increaseTurn() {
        turn++;
    }

    public static void checkPoppedTab(Context context){
        if(turn >= 20 && !tabIsPopped && popTabMode == 0){
            int percent = turn - 15;
            Random rand = new Random();
            int tempNum = rand.nextInt(100);
            if(tempNum < percent){
                tabIsPopped = true;
                AlertDialog.Builder poppedTabDialog = new AlertDialog.Builder(context);
                poppedTabDialog.setTitle("Popped the tab!");
                poppedTabDialog.setMessage("You popped the tab!");
                poppedTabDialog.setCancelable(false);
                poppedTabDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                poppedTabDialog.show();
            }
        }
    }

    public static void resetTurns(){
        turn = 0;
        tabIsPopped = false;
    }

}
