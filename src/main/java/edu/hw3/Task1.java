package edu.hw3;

public class Task1 {

    private final int lowerBorderOfCapitalLetters = 65;

    private final int upperLimitOfCapitalLetters = 90;

    private final int lowerBoundOfLowercaseLetters = 97;

    private final int upperBoundOfLowercaseLetters = 122;

    public String atbash(String input) {
        StringBuilder answer = new StringBuilder(input.length());

        for (int i = 0; i < input.length(); i++) {
            char value = input.charAt(i);

            if (!((value >= lowerBorderOfCapitalLetters && value <= upperLimitOfCapitalLetters)
                || (value >= lowerBoundOfLowercaseLetters && value <= upperBoundOfLowercaseLetters))) {
                answer.append(value);
                continue;
            }

            boolean upper = Character.isUpperCase(value);
            int reflectedCode = getReflectedCode(value);

            char reflectedChar = (char) reflectedCode;
            if (upper) {
                reflectedChar = Character.toUpperCase(reflectedChar);
            }

            answer.append(reflectedChar);
        }

        return answer.toString();
    }

    private int getReflectedCode(char c) {
        if (Character.isUpperCase(c)) {
            return upperLimitOfCapitalLetters - (c - lowerBorderOfCapitalLetters);
        } else {
            return upperBoundOfLowercaseLetters - (c - lowerBoundOfLowercaseLetters);
        }
    }
}
