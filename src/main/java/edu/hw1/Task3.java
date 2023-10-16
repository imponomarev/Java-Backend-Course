package edu.hw1;

public class Task3 {

    public boolean isNestable(int[] a1, int[] a2) {

        if (min(a1) <= min(a2)) {
            return false;
        }
        else {
            return max(a1) < max(a2);
        }
    }

    private int min(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int number : arr) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }

    private int max(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int number : arr) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }
}
