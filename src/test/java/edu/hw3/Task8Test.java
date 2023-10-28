package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task8Test {

    private Task8 task8;

    @BeforeEach
    public void setup() {
        task8 = new Task8();
    }


    @Test
    @DisplayName("check BackwardIterator on list with integers")
    void testIteratorWithList() {

        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Task8.BackwardIterator<Integer> iterator = task8.new BackwardIterator<>(numbers);

        List<Integer> result = new ArrayList<>();

        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertEquals(Arrays.asList(3, 2, 1), result);
    }

    @Test
    @DisplayName("check BackwardIterator on set with strings")
    void testIteratorWithSet() {

        Set<String> literals = new HashSet<>(Arrays.asList("A", "B", "C"));

        Task8.BackwardIterator<String> iterator = task8.new BackwardIterator<>(literals);

        List<String> result = new ArrayList<>();

        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertEquals(Arrays.asList("C", "B", "A"), result);
    }

    @Test
    @DisplayName("check BackwardIterator on queue with double")
    void testIteratorWithQueue() {

        Queue<Double> numbers = new PriorityQueue<>(Arrays.asList(1.1, 1.2, 1.3));

        Task8.BackwardIterator<Double> iterator = task8.new BackwardIterator<>(numbers);

        List<Double> result = new ArrayList<>();

        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertEquals(Arrays.asList(1.3, 1.2, 1.1), result);
    }

    @Test
    @DisplayName("check BackwardIterator on map")
    void testIteratorWithMap() {

        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        Task8.BackwardIterator<Map.Entry<String, Integer>> iterator = task8.new BackwardIterator<>(map.entrySet());

        List<String> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            keys.add(entry.getKey());
            values.add(entry.getValue());
        }

        assertEquals(Arrays.asList("C", "B", "A"), keys);
        assertEquals(Arrays.asList(3, 2, 1), values);
    }





}
