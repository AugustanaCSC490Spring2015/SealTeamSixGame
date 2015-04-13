package edu.augustana.csc490.circleofdeath;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
 * Created by horuscuevas11 on 4/9/2015.
 */
public class GameFragment extends Fragment{
     private static final String TAG = "CardGame Activity";

    private static final int CARDS_IN_GAME = 52;

    private List<String> cardFileNameList;

    private ImageView cardImageView;
    private Button nextCardButton;
    private TextView infoTextView;

    private int deckPosition;

    private Deck deck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        cardFileNameList = new ArrayList<String>();

        cardImageView = (ImageView) view.findViewById(R.id.cardView);
        nextCardButton = (Button) view.findViewById(R.id.newCardButton);
        infoTextView = (TextView) view.findViewById(R.id.infoTextView);
        infoTextView.setMovementMethod(new ScrollingMovementMethod());

        newDeck();

        nextCardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadNextCard();
            }
        });

        return view;
    }

    /**
     * newDeck() creates a new Deck by creating a Card object from each image in the assets/cards
     * folder
     */
    public void newDeck(){
        // create a new empty deck
        deck = new Deck();

        // get assets
        AssetManager assets = getActivity().getAssets();

        cardFileNameList.clear(); // will be removed eventually



        try{
            // get a String[] array containing the names of every image in the assets/cards folder
            String[] paths = assets.list("cards");

            // for each image, create a Card object and add it to the Deck
            for(String path: paths){
                cardFileNameList.add(path.replace(".png", "")); // will be removed eventually
                //Log.w(TAG, "path: " + path);

                deck.addCard(newCard(path));
            }
        }
        catch (IOException exception){
            Log.e(TAG, "Error loading image file names", exception);
        }

        deck.shuffle(); // shuffle the deck

        Collections.shuffle(cardFileNameList); // will be removed eventually

        deckPosition = 0;
        loadNextCard();


    }

    private Card newCard(String cardImageName) {
        String cardExtensionRemoved = cardImageName.replace(".png", "");
        int spaceIndex = cardExtensionRemoved.indexOf('_');
        String suitString = cardExtensionRemoved.substring(spaceIndex+1);
        String numberString = cardExtensionRemoved.substring(0,spaceIndex);

        Suit suit = getSuitFromString(suitString);
        Number number = getNumberFromString(numberString);
        Card card = new Card(suit,number,cardImageName);

        return card;
    }

    private Suit getSuitFromString(String stringSuit) {
        stringSuit = stringSuit.toUpperCase();
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

    private Number getNumberFromString(String stringNumber) {
        stringNumber = stringNumber.toUpperCase();
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

    private void loadNextCard(){
        if(deckPosition>=CARDS_IN_GAME){
            newDeck();
        }
        String nextImage = cardFileNameList.remove(0);
        AssetManager assets = getActivity().getAssets();

        try{
            InputStream stream = assets.open("cards"+ "/"+nextImage+".png");
            Drawable card = Drawable.createFromStream(stream, nextImage);
            cardImageView.setImageDrawable(card);

        } catch (IOException exception){
            Log.e(TAG, "Error loading " + nextImage, exception);
        }
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
        Collections.shuffle(cardFileNameList);
        deckPosition++;

    }
}
