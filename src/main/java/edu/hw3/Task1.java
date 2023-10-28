package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public class Task1 {

    private final int LOWER_BORDER_OF_CAPITAL_LETTERS = 64;

    private final int UPPER_LIMIT_OF_CAPITAL_LETTERS = 91;

    private final int LOWER_BOUND_OF_LOWERCASE_LETTERS = 96;

    private final int UPPER_BOUND_OF_LOWERCASE_LETTERS = 123;


    public String atbash(String input) {

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        Map<Character, Integer> alphabetMap = new HashMap<>();

        for (int i = 0; i < alphabet.length(); i++) {
            alphabetMap.put(alphabet.charAt(i), i + 1);
        }

        StringBuilder answer = new StringBuilder(input.length());

        for (int i = 0; i < input.length(); i++) {

            boolean upper = false;

            char value = input.charAt(i);

            if (!((value > LOWER_BORDER_OF_CAPITAL_LETTERS && value < UPPER_LIMIT_OF_CAPITAL_LETTERS) || (value > LOWER_BOUND_OF_LOWERCASE_LETTERS && value < UPPER_BOUND_OF_LOWERCASE_LETTERS))) {
                answer.append(value);
                continue;
            }

            if (Character.isUpperCase(value)) {
                upper = true;
                value = Character.toLowerCase(value);
            }

            int index = alphabetMap.get(value);

            for (Map.Entry<Character, Integer> entry : alphabetMap.entrySet()) {
                if (entry.getValue().equals(alphabetMap.size() - index + 1)) {
                    Character newVal = entry.getKey();
                    if (upper) {
                        answer.append(Character.toUpperCase(newVal));
                        upper = false;
                    } else {
                        answer.append(newVal);
                    }
                }
            }
        }

        String strAnswer = String.valueOf(answer);

        return strAnswer;
    }
}
