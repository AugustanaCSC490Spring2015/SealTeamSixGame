package edu.augustana.csc490.circleofdeath;

import java.io.IOException;
import java.io.InputStream;
import android.app.Fragment;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.augustana.csc490.circleofdeath.enums.*;
import edu.augustana.csc490.circleofdeath.enums.Number;

/**
 * GameFragment class controls the main game play of the app
 */
public class GameFragment extends Fragment{
     private static final String TAG = "CardGame Activity";

    private ImageView cardImageView;
    private Button nextCardButton;
    private TextView infoTextView;

    private Deck deck;
    private AssetManager assets;

    /**
     * Called when the fragment is created. It initializes variables and starts a new game
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_game, container, false);

        // Get assets
        assets = getActivity().getAssets();

        // get references to the view
        cardImageView = (ImageView) view.findViewById(R.id.cardView);
        nextCardButton = (Button) view.findViewById(R.id.newCardButton);
        infoTextView = (TextView) view.findViewById(R.id.infoTextView);
        infoTextView.setMovementMethod(new ScrollingMovementMethod()); // make the view scroll

        // Create a new deck
        newDeck();

        // Set the listener for the nextCardButton
        nextCardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadNextCard();
            }
        });

        // Start the game
        startGame();

        // return the view
        return view;
    }

    /**
     * newDeck() creates a new Deck by creating a Card object from each image in the assets/cards
     * folder
     */
    public void newDeck(){
        // create a new empty deck
        deck = new Deck();

        try{
            // get a String[] array containing the names of every image in the assets/cards folder
            String[] paths = assets.list("cards");

            // for each image, create a Card object and add it to the Deck
            for(String path: paths){
                deck.addCard(newCard(path));
            }
        }
        catch (IOException exception){
            Log.e(TAG, "Error loading image file names", exception);
        }

        deck.shuffle(); // shuffle the deck
    }

    /**
     * This method takes in the file name of a card image in the form of a string, parses out the
     * suit and number information, then builds and returns a Card object containing the information
     * @param cardImageName A String in the form number_suit.png
     * @return A Card object
     */
    private Card newCard(String cardImageName) {
        // create another string without the .png extension
        String cardExtensionRemoved = cardImageName.replace(".png", "");

        // find the index that separates the number and suit and create separate strings for each
        int spaceIndex = cardExtensionRemoved.indexOf('_');
        String suitString = cardExtensionRemoved.substring(spaceIndex+1);
        String numberString = cardExtensionRemoved.substring(0,spaceIndex);

        // Convert strings to enums
        Suit suit = getSuitFromString(suitString);
        Number number = getNumberFromString(numberString);

        // create and return the Card
        return new Card(suit,number,cardImageName);
    }

    /**
     * This method takes in a string representation of an enumerated suit and returns the
     * enumerated value
     * @param stringSuit the string of the suit. ex: heart
     * @return the enum value matching the suit of the string
     */
    private Suit getSuitFromString(String stringSuit) {
        stringSuit = stringSuit.toUpperCase(); // change to upper case so it will match enum

        // find which enum the string is equal to and return the enum
        if (stringSuit.equals(Suit.CLUB.toString())) {
            return Suit.CLUB;
        } else if (stringSuit.equals(Suit.DIAMOND.toString())) {
            return Suit.DIAMOND;
        } else if (stringSuit.equals(Suit.HEART.toString())) {
            return Suit.HEART;
        } else {
            return Suit.SPADE;
        }
    }

    /**
     * This method takes in the string representation of Number enumerated object, matches it to
     * the correct enum value and returns the enum value
     * @param stringNumber The string representation of the number, ex: four
     * @return the Number enum type matching the string given
     */
    private Number getNumberFromString(String stringNumber) {
        stringNumber = stringNumber.toUpperCase();// change to upper case so it will match enum

        // find which enum the string is equal to and return the enum
        if(stringNumber.equals(Number.ACE.toString())) {
            return Number.ACE;
        } else if(stringNumber.equals(Number.KING.toString())) {
            return Number.KING;
        } else if(stringNumber.equals(Number.QUEEN.toString())) {
            return Number.QUEEN;
        } else if(stringNumber.equals(Number.JACK.toString())) {
            return Number.JACK;
        } else if(stringNumber.equals(Number.TEN.toString())) {
            return Number.TEN;
        } else if(stringNumber.equals(Number.NINE.toString())) {
            return Number.NINE;
        } else if(stringNumber.equals(Number.EIGHT.toString())) {
            return Number.EIGHT;
        } else if(stringNumber.equals(Number.SEVEN.toString())) {
            return Number.SEVEN;
        } else if(stringNumber.equals(Number.SIX.toString())) {
            return Number.SIX;
        } else if(stringNumber.equals(Number.FIVE.toString())) {
            return Number.FIVE;
        } else if(stringNumber.equals(Number.FOUR.toString())) {
            return Number.FOUR;
        } else if(stringNumber.equals(Number.THREE.toString())) {
            return Number.THREE;
        } else {
            return Number.TWO;
        }
    }

    /**
     * This method starts the game by loading the first card
     */
    private void startGame() {
        loadNextCard();
    }

    /**
     * This method is called when the user clicks on the next card button. It loads up and displays
     * the next card
     */
    private void loadNextCard(){
        //Check if there are any cards left
        if (deck.getNumberOfCardsLeft() <= 0) {
            // TODO: End Game
        } else {
            // get next card
            Card card = deck.getNextCard();
            try {
                InputStream stream = assets.open("cards/"+card.getUri());
                Drawable cardDrawable = Drawable.createFromStream(stream,null);
                cardImageView.setImageDrawable(cardDrawable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // TODO: Implement card specific rules
        String rules = "Rules:\nAce = Waterfall - You must ask a common knowledge question to the person directly to your right and directly to your left. The first player to correctly answer your question decides the " +
                "direction in which the circle drinks during this game. You then drink from your drink for as long as you'd like. As soon as you start drinking, the next person must start drinking, and then " +
                "the person after that, etc. until everyone is drinking. The people after you cannot stop drinking until the person before them in the circle stops drinking." +
                "\nTwo = You - Pick a player and they must take a drink.\n" +
                "Three = Me - Take a drink.\n" +
                "Four = Never Have I Ever - All players put up 4 fingers and take turns saying things they have never done. If any other player has done said thing, they must put down a finger. The first person to put" +
                " all their fingers down must take a drink." +
                "Five = Guys - Men take a drink.\n" +
                "Six = Chicks - Ladies take a drink.\n" +
                "Seven = Heaven - Everyone must point to the sky, the last person to do so takes a drink.\n" +
                "Eight = Date - Choose another player, and for the rest of the game, whenever you take a drink, they must take one as well.\n" +
                "Nine = Rhyme - Say a word, and everyone must continue around the circle taking turns saying a word that ryhmes with the chosen word until someone repeats a word or can't think of one in 3 seconds. " +
                "The loser takes a drink.\n" +
                "Ten = Categories - Pick a category and continue around the circle saying things from within that category until someone repeats a word or can't think of one in 3 seconds. The loser takes a drink.\n" +
                "Jack = Thumb Master - Until the next Jack is drawn, whenever you put your thumb on the table, all other players must do so as well. The last to do so takes a drink.\n" +
                "Queen = Question Master - Until the next Queen is drawn, whenever you ask a question of another player, if they answer they must take a drink.\n" +
                "King = Rule Master - Come up with a rule that must be followed for the rest of the game. Or you may abolish a rule set forth by a previous rule master";
        infoTextView.setText(rules);
    }
}
