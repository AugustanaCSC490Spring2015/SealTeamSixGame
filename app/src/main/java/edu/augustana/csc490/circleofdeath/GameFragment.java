package edu.augustana.csc490.circleofdeath;

import java.io.IOException;
import java.io.InputStream;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import edu.augustana.csc490.circleofdeath.enums.*;
import edu.augustana.csc490.circleofdeath.enums.Number;
import edu.augustana.csc490.circleofdeath.utils.NumberUtils;
import edu.augustana.csc490.circleofdeath.utils.SuitUtils;

/**
 * GameFragment class controls the game fragment of the app
 */
public class GameFragment extends Fragment {

    // For debugging
    private static final String TAG = "CardGame Activity";

    private ImageView cardImageView;
    private Button nextCardButton;
    private TextView ruleTextView;
    private TextView playerView;
    private TextView cardNameView;
    private ImageView cupView;

    private AssetManager assets;

    /**
     * Called when the fragment is created. It initializes variables and starts a new game
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the view
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        // Get assets
        assets = getActivity().getAssets();

        // get references to the view
        cardImageView = (ImageView) view.findViewById(R.id.cardView);
        nextCardButton = (Button) view.findViewById(R.id.newCardButton);
        ruleTextView = (TextView) view.findViewById(R.id.infoTextView);
        ruleTextView.setMovementMethod(new ScrollingMovementMethod()); // make the view scroll
        cardNameView = (TextView) view.findViewById(R.id.cardTextView);
        playerView = (TextView) view.findViewById(R.id.playerView);

        // Create a new deck
        newDeck();

        // Set the listener for the nextCardButton
        nextCardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadNextCard();
            }
        });

        //animation for Game Over screen - code will be moved eventually


        // Start the game
        startGame();

        // return the view
        return view;
    }

    /**
     * newDeck() creates a new Deck by creating a Card object from each image in the assets/cards
     * folder
     */
    public void newDeck() {
        // create a new empty deck
        GameManager.deck = new Deck();

        try {
            // get a String[] array containing the names of every image in the assets/cards folder
            String[] paths = assets.list("cards");

            // for each image, create a Card object and add it to the Deck
            for (String path : paths) {
                GameManager.deck.addCard(newCard(path));
            }
        } catch (IOException exception) {
            Log.e(TAG, "Error loading image file names", exception);
        }

        GameManager.deck.shuffle(); // shuffle the deck
    }

    /**
     * This method takes in the file name of a card image in the form of a string, parses out the
     * suit and number information, then builds and returns a Card object containing the information
     *
     * @param cardImageName A String in the form number_suit.png
     * @return A Card object
     */
    private Card newCard(String cardImageName) {
        // create another string without the .png extension
        String cardExtensionRemoved = cardImageName.replace(".png", "");

        // find the index that separates the number and suit and create separate strings for each
        int spaceIndex = cardExtensionRemoved.indexOf('_');
        String suitString = cardExtensionRemoved.substring(spaceIndex + 1);
        String numberString = cardExtensionRemoved.substring(0, spaceIndex);

        // Convert strings to enums
        Suit suit = SuitUtils.getEnumSuitFromString(suitString);
        Number number = NumberUtils.getEnumNumberFromString(numberString);

        // create and return the Card
        return new Card(suit, number, cardImageName);
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
    private void loadNextCard() {
        //Check if there are any cards left
        if (GameManager.deck.getNumberOfCardsLeft() <= 0) {
            // TODO: End Game
            //displays game over screen
            Intent intent = new Intent(getActivity(), GameOverActivity.class);
            startActivity(intent);

        } else {
            // get next card
            Card card = GameManager.deck.getNextCard();
            try {
                InputStream stream = assets.open("cards/" + card.getUri());
                Drawable cardDrawable = Drawable.createFromStream(stream, null);
                cardImageView.setImageDrawable(cardDrawable);
                cardNameView.setText(card.getNumber().toString().substring(0,1) + card.getNumber().toString().substring(1).toLowerCase() + " " + card.getSuit().toString().substring(0,1) + card.getSuit().toString().substring(1).toLowerCase());
                String cardName = "default_"+ card.getNumber().toString().toLowerCase();
                int identifier = getResources().getIdentifier(cardName, "string", GameFragment.this.getClass().getPackage().getName());
                if (identifier !=0){
                    ruleTextView.setText(getResources().getString(identifier));
                    ruleTextView.scrollTo(0,0);
                }
                if (card.getNumber().toString().toLowerCase().equals("queen")){
                    GameManager.setCurrentPlayerAsQuestionMaster();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (GameManager.getPlayersSize() != 0){
            String questionMaster = "";
            if (GameManager.isCurrentPlayerQuestionMaster()){
                questionMaster = '\n' + "*Question Master";
            }
            playerView.setText(GameManager.getCurrentPlayerName() + questionMaster);
        }

        GameManager.incrementCurrentPlayer();
    }
}
