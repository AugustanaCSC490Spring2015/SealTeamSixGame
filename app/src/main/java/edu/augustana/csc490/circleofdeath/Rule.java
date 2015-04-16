package edu.augustana.csc490.circleofdeath;

/**
 * The Rule class holds a card value (ex: Ace) and a card rule (Waterfall - player must...)
 * Note: unsure if this is the best way to implement, may want to amke the card value an enum
 * for consistency
 */
public class Rule {
    String cardValue;
    String cardRule;

    public Rule(String cardValue, String cardRule) {
        this.cardRule = cardRule;
        this.cardValue = cardValue;
    }

    /**
     * Getter method
     * @return The card's value
     */
    public String getCardValue() {
        return cardValue;
    }

    /**
     * Getter method
     * @return The card's rule
     */
    public String getCardRule() {
        return cardRule;
    }
}
