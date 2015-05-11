package edu.augustana.csc490.circleofdeath;

import java.io.IOException;
import java.io.InputStream;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
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
    private TextView ruleTextView;
    private TextView playerView;

    // Images courtesy http://www.freepik.com/free-photos-vectors/icon
    private GridLayout questionMasterLayout;
    private GridLayout thumbMasterLayout;
    private GridLayout ruleMasterLayout;
    private GridLayout mastersLayout;

    GridLayout.LayoutParams playerTextParams;
    GridLayout.LayoutParams questionMasterParams;
    GridLayout.LayoutParams thumbMasterParams;
    GridLayout.LayoutParams ruleMasterParams;

    private static AssetManager assets;

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
        playerView = (TextView) view.findViewById(R.id.playerView);

        questionMasterLayout = (GridLayout) view.findViewById(R.id.questionMaster);
        questionMasterLayout.setVisibility(View.INVISIBLE);
        thumbMasterLayout = (GridLayout) view.findViewById(R.id.thumbMaster);
        thumbMasterLayout.setVisibility(View.INVISIBLE);
        ruleMasterLayout = (GridLayout) view.findViewById(R.id.ruleMaster);
        ruleMasterLayout.setVisibility(View.INVISIBLE);
        mastersLayout = (GridLayout) view.findViewById(R.id.mastersLayout);

        playerTextParams = (GridLayout.LayoutParams) playerView.getLayoutParams();
        questionMasterParams = (GridLayout.LayoutParams) questionMasterLayout.getLayoutParams();
        thumbMasterParams = (GridLayout.LayoutParams) thumbMasterLayout.getLayoutParams();
        ruleMasterParams = (GridLayout.LayoutParams) ruleMasterLayout.getLayoutParams();

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
    public void  newDeck() {
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

    public static Deck returnNewDeck(Context context) {
        // create a new empty deck
        Deck deck = new Deck();

        try {
            // get a String[] array containing the names of every image in the assets/cards folder
            AssetManager assets1 = context.getAssets();
            String[] paths = assets1.list("cards");

            // for each image, create a Card object and add it to the Deck
            for (String path : paths) {
                Card nc = newCard(path);
                deck.addCard(nc);

            }
        } catch (IOException exception) {
            Log.e(TAG, "Error loading image file names", exception);
        }

        return deck;
    }

    /**
     * This method takes in the file name of a card image in the form of a string, parses out the
     * suit and number information, then builds and returns a Card object containing the information
     *
     * @param cardImageName A String in the form number_suit.png
     * @return A Card object
     */
    private static Card newCard(String cardImageName) {
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
            // There are no cards left, check if endless mode is enabled
            if (GameManager.getGameMode() == GameManager.DECK_ENDLESS_MODE) {
                // Endless mode - create a new deck
                newDeck();
            } else {
                //displays game over screen
                Intent intent = new Intent(getActivity(), GameOverActivity.class);
                startActivity(intent);
                return;
            }
        }

        // get next card
        Card card = GameManager.deck.getNextCard();
        try {
            InputStream stream = assets.open("cards/" + card.getUri());
            Drawable cardDrawable = Drawable.createFromStream(stream, null);

            cardImageView.setImageDrawable(cardDrawable);
            ruleTextView.setText(GameManager.getRule(card));

            GameManager.setMasters(card); // sets the player to card Master if i

        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Most of the following logic was inspired by multiple StackOverflows:
         * http://stackoverflow.com/questions/20871690/dont-understand-how-to-use-gridlayout-spec
         * http://stackoverflow.com/questions/13532084/set-rowspan-or-colspan-of-a-child-of-a-gridlayout-programmatically
         * http://developer.android.com/reference/android/widget/GridLayout.LayoutParams.html
         */

        // remove all elements and reset them
        mastersLayout.removeAllViewsInLayout();

        // add player text
        playerTextParams.rowSpec = GridLayout.spec(0);
        playerView.setLayoutParams(playerTextParams);

        // determine how many "Masters" the person is and set parameters of the individual "Masters" layout
        if (GameManager.isCurrentPlayerQuestionMaster() && GameManager.isCurrentPlayerThumbMaster() && GameManager.isCurrentPlayerRuleMaster())
        {
            questionMasterLayout.setVisibility(View.VISIBLE);
            thumbMasterLayout.setVisibility(View.VISIBLE);
            ruleMasterLayout.setVisibility(View.VISIBLE);
            questionMasterParams.rowSpec = GridLayout.spec(1);
            questionMasterLayout.setLayoutParams(questionMasterParams);
            thumbMasterParams.rowSpec = GridLayout.spec(2);
            thumbMasterLayout.setLayoutParams(thumbMasterParams);
            ruleMasterParams.rowSpec = GridLayout.spec(3);
            ruleMasterLayout.setLayoutParams(ruleMasterParams);
        }
        else if (GameManager.isCurrentPlayerQuestionMaster() && GameManager.isCurrentPlayerRuleMaster())
        {
            questionMasterLayout.setVisibility(View.VISIBLE);
            ruleMasterLayout.setVisibility(View.VISIBLE);
            questionMasterParams.rowSpec = GridLayout.spec(1);
            questionMasterLayout.setLayoutParams(questionMasterParams);
            ruleMasterParams.rowSpec = GridLayout.spec(2);
            ruleMasterLayout.setLayoutParams(ruleMasterParams);
            thumbMasterLayout.setVisibility(View.INVISIBLE);
        }
        else if (GameManager.isCurrentPlayerQuestionMaster() && GameManager.isCurrentPlayerThumbMaster())
        {
            questionMasterLayout.setVisibility(View.VISIBLE);
            thumbMasterLayout.setVisibility(View.VISIBLE);
            questionMasterParams.rowSpec = GridLayout.spec(1);
            questionMasterLayout.setLayoutParams(questionMasterParams);
            thumbMasterParams.rowSpec = GridLayout.spec(2);
            thumbMasterLayout.setLayoutParams(thumbMasterParams);
            ruleMasterLayout.setVisibility(View.INVISIBLE);
        }
        else if (GameManager.isCurrentPlayerThumbMaster() && GameManager.isCurrentPlayerRuleMaster())
        {
            thumbMasterLayout.setVisibility(View.VISIBLE);
            ruleMasterLayout.setVisibility(View.VISIBLE);
            thumbMasterParams.rowSpec = GridLayout.spec(1);
            thumbMasterLayout.setLayoutParams(thumbMasterParams);
            ruleMasterParams.rowSpec = GridLayout.spec(2);
            ruleMasterLayout.setLayoutParams(ruleMasterParams);
            questionMasterLayout.setVisibility(View.INVISIBLE);
        }
        else if (GameManager.isCurrentPlayerQuestionMaster())
        {
            questionMasterLayout.setVisibility(View.VISIBLE);
            questionMasterParams.rowSpec = GridLayout.spec(1);
            questionMasterLayout.setLayoutParams(questionMasterParams);
            ruleMasterLayout.setVisibility(View.INVISIBLE);
            thumbMasterLayout.setVisibility(View.INVISIBLE);
        }
        else if (GameManager.isCurrentPlayerThumbMaster())
        {
            thumbMasterLayout.setVisibility(View.VISIBLE);
            thumbMasterParams.rowSpec = GridLayout.spec(1);
            thumbMasterLayout.setLayoutParams(thumbMasterParams);
            questionMasterLayout.setVisibility(View.INVISIBLE);
            ruleMasterLayout.setVisibility(View.INVISIBLE);
        }
        else if (GameManager.isCurrentPlayerRuleMaster())
        {
            ruleMasterLayout.setVisibility(View.VISIBLE);
            ruleMasterParams.rowSpec = GridLayout.spec(1);
            ruleMasterLayout.setLayoutParams(ruleMasterParams);
            questionMasterLayout.setVisibility(View.INVISIBLE);
            thumbMasterLayout.setVisibility(View.INVISIBLE);
        }
        else{
            questionMasterLayout.setVisibility(View.INVISIBLE);
            ruleMasterLayout.setVisibility(View.INVISIBLE);
            thumbMasterLayout.setVisibility(View.INVISIBLE);
        }

        // add back the elements into the GridView
        mastersLayout.addView(playerView);
        mastersLayout.addView(questionMasterLayout);
        mastersLayout.addView(thumbMasterLayout);
        mastersLayout.addView(ruleMasterLayout);

        playerView.setText(GameManager.getCurrentPlayerName());

        GameManager.incrementCurrentPlayer();
    }
}
