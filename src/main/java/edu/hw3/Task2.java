package edu.hw3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Task2 {

    private static final String UNBALANCED = "the cluster cannot be balanced";

    public String[] clusterize(String input) {
        List<String> cluster = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push(i);
            } else if (input.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    throw new RuntimeException(UNBALANCED);
                } else {
                    int startIndex = stack.pop();
                    if (stack.isEmpty()) {
                        cluster.add(input.substring(startIndex, i + 1));
                    }
                }
            }
        }

        if (!stack.isEmpty()) {
            throw new RuntimeException(UNBALANCED);
        } else {
            return cluster.toArray(new String[0]);
        }
    }
}
