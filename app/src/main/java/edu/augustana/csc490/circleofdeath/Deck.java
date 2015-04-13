package edu.augustana.csc490.circleofdeath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dan on 4/13/15.
 */
public class Deck {

    private List<Card> deck;

    /**
     * The constructor method creates an empty deck with no cards
     */
    public Deck() {
        deck = new ArrayList<>();
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

}
