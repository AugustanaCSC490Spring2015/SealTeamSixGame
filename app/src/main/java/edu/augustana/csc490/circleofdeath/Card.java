package edu.augustana.csc490.circleofdeath;

import android.graphics.drawable.Drawable;

import java.io.InputStream;

import edu.augustana.csc490.circleofdeath.enums.Suit;
import edu.augustana.csc490.circleofdeath.enums.Number;

/**
 * The Card class holds enum values for the card's number and suit as well as the image name within
 * the assets/cards folder
 */
public class Card {
    private Suit suit;
    private Number number;
    private String uri; // the filename of the card in the assets/cards/ folder
    private double x;
    private double y;

    /**
     * Constructor sets values for the Card
     * @param suit The suit of the card
     * @param number The Number of the Card
     * @param uri the filename of the Card
     */
    public Card(Suit suit, Number number, String uri) {
        this.suit = suit;
        this.number = number;
        this.uri = uri;

    }

    /**
     * Getter method that returns the Card's suit
     * @return the card's Suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Getter method that returns the card's Number
     * @return the card's Number
     */
    public Number getNumber() {
        return number;
    }

    /**
     * Getter method that returns the card's filename in the assets/cards folder
     * @return the card's filename
     */
    public String getUri() {
        return uri;
    }


/*
 * It sets the coordinates of the card into the xCard and yCard parameters
 */
    public void setCoordinate(double xCard, double yCard){
        x = xCard;
        y = yCard;
    }

    // return a double of the x of the card
    public double getX(){
        return x;
    }

    // returns a double of the y of the card
    public double getY(){
        return y;
    }

    /**
     * @return a string description of the card
      */
    @Override
    public String toString(){
        return uri + "suit :" +suit+" ; number: "+ number+" at location (" + x + "," + y+")";
    }
}
