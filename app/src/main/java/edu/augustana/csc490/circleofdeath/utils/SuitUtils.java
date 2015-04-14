package edu.augustana.csc490.circleofdeath.utils;

import edu.augustana.csc490.circleofdeath.enums.Suit;

/**
 * This is a utility class for methods involving the Suit class
 */
public class SuitUtils {

    public static Suit getEnumSuitFromString(String enumString) {
        enumString = enumString.toUpperCase(); // change to upper case so it will match enum

        // find which enum the string is equal to and return the enum
        if (enumString.equals(Suit.CLUB.toString())) {
            return Suit.CLUB;
        } else if (enumString.equals(Suit.DIAMOND.toString())) {
            return Suit.DIAMOND;
        } else if (enumString.equals(Suit.HEART.toString())) {
            return Suit.HEART;
        } else {
            return Suit.SPADE;
        }
    }
}
