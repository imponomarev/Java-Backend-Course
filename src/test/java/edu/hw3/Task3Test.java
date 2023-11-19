package edu.hw3;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task3Test {

    private Task3 task3;

    @BeforeEach
    public void setup() {
        task3 = new Task3();
    }

    @Test
    @DisplayName("Test freqDict with 1 input string value")
    void testFreqDict1() {

        //Given
        String[] input = new String[]{"java"};
        Map<String, Integer> expected = new HashMap<>();
        expected.put("java", 1);

        //When
        Map<String, Integer> actual = task3.freqDict(input);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test freqDict with 3 input unique string values")
    void testFreqDict2() {

        //Given
        String[] input = new String[]{"python", "java", "rust"};
        Map<String, Integer> expected = new HashMap<>();
        expected.put("python", 1);
        expected.put("java", 1);
        expected.put("rust", 1);

        //When
        Map<String, Integer> actual = task3.freqDict(input);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test freqDict with 3 input string values with duplicates")
    void testFreqDict3() {

        //Given
        String[] input = new String[]{"python", "java", "rust", "python", "python", "java"};
        Map<String, Integer> expected = new HashMap<>();
        expected.put("python", 3);
        expected.put("java", 2);
        expected.put("rust", 1);

        //When
        Map<String, Integer> actual = task3.freqDict(input);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test freqDict with 4 input int values with duplicates")
    void testFreqDict4() {

        //Given
        Integer[] input = new Integer[]{1, 1, 2, 2, 3, 3, 4, 4, 4, 4};
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 2);
        expected.put(2, 2);
        expected.put(3, 2);
        expected.put(4, 4);

        //When
        Map<Integer, Integer> actual = task3.freqDict(input);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test freqDict with 2 empty strings")
    void testFreqDict5() {

        //Given
        String[] input = new String[]{" ", " "};
        Map<String, Integer> expected = new HashMap<>();
        expected.put(" ", 2);

        //When
        Map<String, Integer> actual = task3.freqDict(input);

        //Then
        assertEquals(expected, actual);
    }
}
