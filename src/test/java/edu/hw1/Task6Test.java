package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task6Test {

    @ParameterizedTest
    @CsvSource({"3524, 3", "6621, 5", "6554, 4", "1234, 3", "6174, 1"})
    @DisplayName("Getting the Kaprekar constant from four-digit numbers")
    void testCountK(int number, int expected){

        //When
        Task6 task6 = new Task6();
        int result = task6.countK(number);

        //Then
        Assertions.assertEquals(result, expected);
    }

}
