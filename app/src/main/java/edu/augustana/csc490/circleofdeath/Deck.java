package edu.augustana.csc490.circleofdeath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Deck Class holds an ArrayList of Card objects and contains methods for using the cards
 */
public class Deck {

    private List<Card> deck;

    /**
     * The constructor method creates an empty deck with no cards
     */
    public Deck() {
        deck = new ArrayList<>();
    }

    /**
     * This method adds the given card to the ArrayList
     * @param card the card to add to the deck
     */
    public void addCard(Card card) {
        deck.add(card);
    }

    /**
     * Shuffles the deck
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Getter method for the number of cards left in the deck
     * @return an int of the cards remaining
     */
    public int getNumberOfCardsLeft() {
        return deck.size();
    }

    /**
     * This method returns and removes the next card from the deck
     * @return The next card
     */
    public Card getNextCard() {
        return deck.remove(0);
    }

}
