package edu.hw3;

public class Task4 {

    public String convertToRoman(int input) {

        StringBuilder romanNumeral = new StringBuilder();

        int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        if (input > 3999) {
            throw new RuntimeException("Your input too big!");
        }

        for (int i = 0; i < arabicValues.length; i++) {
            if (input < 0) {
                romanNumeral.append("-");
                input = Math.abs(input);
            }

            while (input >= arabicValues[i]) {
                romanNumeral.append(romanSymbols[i]);
                input -= arabicValues[i];
            }
        }
        return romanNumeral.toString();
    }
}
