package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task7Test {

    @ParameterizedTest
    @CsvSource({"16, 1, 1", "17, 2, 6", "85, 5, 53", "0, 5, 0", "100, 14, 100"})
    @DisplayName("Cyclic shift to the left")
    void testRotateLeft(int number, int shift, int expected){

        //When
        Task7 task7 = new Task7();
        int result = task7.rotateLeft(number, shift);

        //Then
        Assertions.assertEquals(result, expected);


    }

    @ParameterizedTest
    @CsvSource({"8, 1, 4", "16, 3, 2", "48, 4, 3", "0, 5, 0", "100, 14, 100"})
    @DisplayName("Cyclic shift to the right")
    void testRotateRight(int number, int shift, int expected){

        //When
        Task7 task7 = new Task7();
        int result = task7.rotateRight(number, shift);

        //Then
        Assertions.assertEquals(result, expected);


    }
}
