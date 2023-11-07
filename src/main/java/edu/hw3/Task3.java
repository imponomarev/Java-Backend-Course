package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public class Task3 {
    public <T> Map<T, Integer> freqDict(T[] input) {
        Map<T, Integer> dictionary = new HashMap<>();

        for (T element : input) {
            dictionary.merge(element, 1, Integer::sum);
        }

        return dictionary;
    }
}
