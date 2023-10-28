package edu.hw3;

public class Task4 {

    private final int MAX_NUMBER = 3999;
    private final int THOUSAND = 1000;
    private final int NINE_HUNDRED = 900;
    private final int FIVE_HUNDRED = 500;
    private final int FOUR_HUNDRED = 400;
    private final int ONE_HUNDRED = 100;
    private final int NINETY = 90;
    private final int FIFTY = 50;
    private final int FORTY = 40;
    private final int TEN = 10;
    private final int NINE = 9;
    private final int FIVE = 5;
    private final int FOUR = 4;
    private final int ONE = 1;



    public String convertToRoman(int input) {

        int value = input;

        StringBuilder romanNumeral = new StringBuilder();

        int[] arabicValues = {THOUSAND, NINE_HUNDRED, FIVE_HUNDRED, FOUR_HUNDRED, ONE_HUNDRED, NINETY, FIFTY, FORTY, TEN, NINE, FIVE, FOUR, ONE};
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        if (value > MAX_NUMBER) {
            throw new RuntimeException("Your input too big!");
        }

        for (int i = 0; i < arabicValues.length; i++) {
            if (value < 0) {
                romanNumeral.append("-");
                value = Math.abs(value);
            }

            while (value >= arabicValues[i]) {
                romanNumeral.append(romanSymbols[i]);
                value -= arabicValues[i];
            }
        }
        return romanNumeral.toString();
    }
}
