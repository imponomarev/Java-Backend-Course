package edu.hw3;

public class Task4 {

    private static final int MAXIMUM = 3999;


    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};



    public String convertToRoman(int input) {

        int value = input;

        StringBuilder romanNumeral = new StringBuilder();

        if (value > MAXIMUM) {
            throw new RuntimeException("Your input too big!");
        }

        for (int i = 0; i < VALUES.length; i++) {
            if (value < 0) {
                romanNumeral.append("-");
                value = Math.abs(value);
            }

            while (value >= VALUES[i]) {
                romanNumeral.append(SYMBOLS[i]);
                value -= VALUES[i];
            }
        }
        return romanNumeral.toString();
    }
}
