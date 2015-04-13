package edu.augustana.csc490.circleofdeath;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.io.FileReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;

/**
 * Created by horuscuevas11 on 4/8/2015.
 */
public class Deck {
    private List<Card> cards;
    private int position;

    public Deck() {
        //Code courtesy of Dan and I's team Egret project.
        String filename = "Deck.csv";

        cards = new ArrayList<Card>();
        Card oneH = new Card(1, "Hearts");
        cards.add(oneH);
        Card twoH = new Card(2, "Hearts");
        cards.add(twoH);
        Card threeH = new Card(3, "Hearts");
        cards.add(threeH);
        Card fourH = new Card(4, "Hearts");
        cards.add(fourH);



        /*try {
            CSVReader reader = new CSVReader(new FileReader(filename));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                Card newCard = new Card(Integer.parseInt(nextLine[0]),
                        nextLine[1]);
                cards.add(newCard);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // File Not Found - print to stack trace
            e.printStackTrace();
        } catch (IOException e) {
            // IOException - print to stack trace
            e.printStackTrace();
        } */
        shuffle();
        position = 0;
    }

    public void shuffle() {
        Collections.shuffle(cards, new Random());
    }

    public Card getCard(){
        int temp = position;
        nextCard();
        return cards.get(temp);
    }

    private void nextCard(){
        if(position >= cards.size()-1){
            shuffle();
            position = -1;
        }
        position++;
    }
}
