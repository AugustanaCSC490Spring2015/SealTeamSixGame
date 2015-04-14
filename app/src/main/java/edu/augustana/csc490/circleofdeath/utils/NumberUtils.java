package edu.augustana.csc490.circleofdeath.utils;

import edu.augustana.csc490.circleofdeath.enums.*;
import edu.augustana.csc490.circleofdeath.enums.Number;

/**
 * This is a utility class for methods involving the Numbers class
 */
public class NumberUtils {

    public static Number getEnumNumberFromString(String enumNumber) {
        enumNumber = enumNumber.toUpperCase();// change to upper case so it will match enum

        // find which enum the string is equal to and return the enum
        if(enumNumber.equals(Number.ACE.toString())) {
            return Number.ACE;
        } else if(enumNumber.equals(Number.KING.toString())) {
            return Number.KING;
        } else if(enumNumber.equals(Number.QUEEN.toString())) {
            return Number.QUEEN;
        } else if(enumNumber.equals(Number.JACK.toString())) {
            return Number.JACK;
        } else if(enumNumber.equals(Number.TEN.toString())) {
            return Number.TEN;
        } else if(enumNumber.equals(Number.NINE.toString())) {
            return Number.NINE;
        } else if(enumNumber.equals(Number.EIGHT.toString())) {
            return Number.EIGHT;
        } else if(enumNumber.equals(Number.SEVEN.toString())) {
            return Number.SEVEN;
        } else if(enumNumber.equals(Number.SIX.toString())) {
            return Number.SIX;
        } else if(enumNumber.equals(Number.FIVE.toString())) {
            return Number.FIVE;
        } else if(enumNumber.equals(Number.FOUR.toString())) {
            return Number.FOUR;
        } else if(enumNumber.equals(Number.THREE.toString())) {
            return Number.THREE;
        } else {
            return Number.TWO;
        }
    }
}
