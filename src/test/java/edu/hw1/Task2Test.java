package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task2Test {

    @ParameterizedTest
    @CsvSource({"12345, 5", "-12345, 5", "0, 1"})
    @DisplayName("Counting the number of digits in decimal form of a number")
    void testCountDigits(int input, int expected){

        Task2 task2 = new Task2();

        //When
        int result = task2.countDigits(input);

        //Then
        Assertions.assertThat(result).isEqualTo(expected);

    }
}
