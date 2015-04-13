package edu.augustana.csc490.circleofdeath;

import edu.augustana.csc490.circleofdeath.enums.Suit;
import edu.augustana.csc490.circleofdeath.enums.Number;

/**
 * Created by Dan on 4/13/15.
 */
public class Card {
    private Suit suit;
    private Number number;
    private String uri;

    public Card(Suit suit, Number number, String uri) {
        this.suit = suit;
        this.number = number;
        this.uri = uri;
    }

    public Suit getSuit() {
        return suit;
    }

    public Number getNumber() {
        return number;
    }

    public String getUri() {
        return uri;
    }
}
