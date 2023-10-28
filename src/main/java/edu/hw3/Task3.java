package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public class Task3 {
    public <T> Map<T, Integer> freqDict(T[] input) {

        Map<T, Integer> dictionary = new HashMap<>();

        for (int i = 0; i < input.length; i++) {

            if (!dictionary.containsKey(input[i])) {
                dictionary.put(input[i], 1);
            } else {
                for (Map.Entry<T, Integer> entry : dictionary.entrySet()) {

                    if (input[i].equals(entry.getKey())) {
                        entry.setValue(entry.getValue() + 1);
                    }
                }
            }
        }
        return dictionary;
    }
}
