package edu.augustana.csc490.circleofdeath;

import edu.augustana.csc490.circleofdeath.enums.Suit;

/**
 * Created by Dan on 4/14/15.
 */
public class Rule {
    String cardValue;
    String cardRule;

    public Rule(String cardValue, String cardRule) {
        this.cardRule = cardRule;
        this.cardValue = cardValue;
    }

    public String getCardValue() {
        return cardValue;
    }

    public String getCardRule() {
        return cardRule;
    }
}
