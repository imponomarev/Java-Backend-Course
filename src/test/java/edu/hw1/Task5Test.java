package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task5Test {

    @ParameterizedTest
    @CsvSource({"11211230, true", "13001120, true", "23336014, true", "4356, false", "532325, true", "11, true", "1, false"})
    @DisplayName("Is a number or its descendants palindromes")
    void testIsPalindromeDescendant(int number, boolean expected){
        //When
        Task5 task5 = new Task5();
        boolean result = task5.isPalindromeDescendant(number);

        //Then
        Assertions.assertEquals(result, expected);
    }
}
