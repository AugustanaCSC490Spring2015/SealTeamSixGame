package edu.augustana.csc490.circleofdeath.utils;

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

    public static String getStringFromEnumNumber(Number number){

        // find which enum the number is equal to and return the String
        if (number.toString().equals(Number.ACE.toString())){
            return "Ace";
        } else if (number.toString().equals(Number.KING.toString())){
            return "King";
        } else if (number.toString().equals(Number.QUEEN.toString())){
            return "Queen";
        } else if (number.toString().equals(Number.JACK.toString())){
            return "Jack";
        } else if (number.toString().equals(Number.TEN.toString())){
            return "Ten";
        } else if (number.toString().equals(Number.NINE.toString())){
            return "Nine";
        } else if (number.toString().equals(Number.EIGHT.toString())){
            return "Eight";
        } else if (number.toString().equals(Number.SEVEN.toString())){
            return "Seven";
        } else if (number.toString().equals(Number.SIX.toString())){
            return "Six";
        } else if (number.toString().equals(Number.FIVE.toString())){
            return "Five";
        } else if (number.toString().equals(Number.FOUR.toString())){
            return "Four";
        } else if (number.toString().equals(Number.THREE.toString())){
            return "Three";
        } else {
            return "Two";
        }
    }
}
