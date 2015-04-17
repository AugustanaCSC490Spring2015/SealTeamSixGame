package edu.augustana.csc490.circleofdeath;

import java.io.IOException;
import java.io.InputStream;

import android.app.Fragment;
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
    private TextView infoTextView;
    private TextView cardNameView;
    private ImageView cupView;

    private Deck deck;
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
        infoTextView = (TextView) view.findViewById(R.id.infoTextView);
        infoTextView.setMovementMethod(new ScrollingMovementMethod()); // make the view scroll
        cardNameView = (TextView) view.findViewById(R.id.cardTextView);

        cupView = (ImageView) view.findViewById(R.id.gameOverImage);
       /* cupView.setBackgroundResource((R.drawable.cup_animation));
        AnimationDrawable cupAnimation = (AnimationDrawable) cupView.getBackground();
        cupAnimation.start();*/

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
        deck = new Deck();

        try {
            // get a String[] array containing the names of every image in the assets/cards folder
            String[] paths = assets.list("cards");

            // for each image, create a Card object and add it to the Deck
            for (String path : paths) {
                deck.addCard(newCard(path));
            }
        } catch (IOException exception) {
            Log.e(TAG, "Error loading image file names", exception);
        }

        deck.shuffle(); // shuffle the deck
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
        if (deck.getNumberOfCardsLeft() <= 0) {
            // TODO: End Game
        } else {
            // get next card
            Card card = deck.getNextCard();
            try {
                InputStream stream = assets.open("cards/" + card.getUri());
                Drawable cardDrawable = Drawable.createFromStream(stream, null);
                cardImageView.setImageDrawable(cardDrawable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // TODO: Implement card specific rules and remove infoTextView
        infoTextView.setText(
                "Rules\n" +
                        getResources().getString(R.string.default_ace) + "\n" +
                        getResources().getString(R.string.default_king) + "\n" +
                        getResources().getString(R.string.default_queen) + "\n" +
                        getResources().getString(R.string.default_jack) + "\n" +
                        getResources().getString(R.string.default_ten) + "\n" +
                        getResources().getString(R.string.default_nine) + "\n" +
                        getResources().getString(R.string.default_eight) + "\n" +
                        getResources().getString(R.string.default_seven) + "\n" +
                        getResources().getString(R.string.default_six) + "\n" +
                        getResources().getString(R.string.default_five) + "\n" +
                        getResources().getString(R.string.default_four) + "\n" +
                        getResources().getString(R.string.default_three) + "\n" +
                        getResources().getString(R.string.default_two)
        );
    }
}
