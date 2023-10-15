package edu.hw1;

import java.util.Arrays;
import java.util.Comparator;


public class Task6 {

    int counter = 1;
    static final int KAPREKAR_CONSTANT = 6174;

    public int countK(int number) {
        String strNumber = String.valueOf(number);
        Integer[] digits = new Integer[strNumber.length()];

        for (int i = 0; i < strNumber.length(); i++) {
            digits[i] = Character.getNumericValue(strNumber.charAt(i));
        }

        Arrays.sort(digits);
        Integer[] descending = Arrays.copyOf(digits, digits.length);

        Arrays.sort(descending, Comparator.reverseOrder());

        StringBuilder strAscending = new StringBuilder();
        StringBuilder strDescending = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            strAscending.append(digits[i]);
        }

        for (int i = 0; i < descending.length; i++) {
            strDescending.append(descending[i]);
        }

        int newNumber = Integer.parseInt(strDescending.toString()) - Integer.parseInt(strAscending.toString());

        if (newNumber != KAPREKAR_CONSTANT) {
            counter += 1;
            return countK(newNumber);
        }
        return counter;
    }
}
