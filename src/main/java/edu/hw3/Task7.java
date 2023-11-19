package edu.hw3;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Task7 {

    Map<String, String> tree = new TreeMap<>(nullComparator());

    private static <T> Comparator<T> nullComparator() {
        return (obj1, obj2) -> {

            if (obj1 == null && obj2 == null) {
                return 0;
            } else if (obj1 == null) {
                return -1;
            } else if (obj2 == null) {
                return 1;
            } else {
                return obj1.toString().compareTo(obj2.toString());
            }

        };
    }
}
