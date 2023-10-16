package edu.hw1;

import java.util.Arrays;
import java.util.Comparator;


public class Task6 {

    static final int KAPREKAR_CONSTANT = 6174;

    public int countK(int number) {
        return countKRecursive(number, 0);
    }

    private int countKRecursive(int number, int steps){

        if (number == KAPREKAR_CONSTANT) {
            return steps;
        }

        String strNumber = String.valueOf(number);
        int[] digits = new int[strNumber.length()];

        for (int i = 0; i < strNumber.length(); i++) {
            digits[i] = Character.getNumericValue(strNumber.charAt(i));
        }

        Arrays.sort(digits);

        Integer[] temp = new Integer[digits.length];
        for (int i = 0; i < digits.length; i++) {
            temp[i] = digits[i];
        }

        Arrays.sort(temp, Comparator.reverseOrder());

        int[] descending = Arrays.copyOf(digits, digits.length);

        for (int i = 0; i < temp.length; i++) {
            descending[i] = temp[i];
        }

        StringBuilder strAscending = new StringBuilder(strNumber.length());
        StringBuilder strDescending = new StringBuilder(strNumber.length());

        for (int i = 0; i < digits.length; i++) {
            strAscending.append(digits[i]);
        }

        for (int i = 0; i < descending.length; i++) {
            strDescending.append(descending[i]);
        }

        int newNumber = Integer.parseInt(strDescending.toString()) - Integer.parseInt(strAscending.toString());

        return countKRecursive(newNumber, steps + 1);
    }
}
