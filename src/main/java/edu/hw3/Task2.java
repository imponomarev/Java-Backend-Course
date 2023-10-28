package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private final String NOT_BALANCED = "the cluster cannot be balanced";

    public String[] clusterize(String input) {

        List<String> cluster = new ArrayList<>();

        int open = 0;
        int openCounter = 0;
        int close = 0;
        int closeCounter = 0;
        int startIndex = -1;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                open++;
                openCounter++;

                if (open == 1) {
                    startIndex = i;
                }
            } else if (input.charAt(i) == ')') {
                if (open == 0) {
                    throw new RuntimeException(NOT_BALANCED);
                } else {
                    close++;
                    closeCounter++;

                    if (open == close) {
                        cluster.add(input.substring(startIndex, i + 1));
                        open = 0;
                        close = 0;
                        startIndex = -1;
                    }
                }
            }
        }
        if (openCounter != closeCounter) {
            throw new RuntimeException(NOT_BALANCED);
        } else {
            return cluster.toArray(new String[0]);
        }
    }
}
