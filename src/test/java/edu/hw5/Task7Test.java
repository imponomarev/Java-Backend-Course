package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task7Test {

    @ParameterizedTest
    @CsvSource({"'0101', 'true'", "'111100', 'false'", " , 'false'",
    "'12023', 'false'"})
    void binaryMatch1Test(String input, boolean expected) {

        //Given
        Task7 task7 = new Task7();

        //When
        boolean result = task7.binaryMatch(input);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"'0110', 'true'", "'11110001', 'true'", " , 'false'",
        "'12023', 'false'", "'1000', 'false'"})
    void binaryMatch2Test(String input, boolean expected) {

        //Given
        Task7 task7 = new Task7();

        //When
        boolean result = task7.binaryMatch2(input);

        //Then
        Assertions.assertEquals(expected, result);
    }


    @ParameterizedTest
    @CsvSource({"'010', 'true'", "'111', 'true'", "' ', 'false'",
        "'12023', 'false'", "'1111', 'false'", " , 'false'"})
    void binaryMatch3Test(String input, boolean expected) {

        //Given
        Task7 task7 = new Task7();

        //When
        boolean result = task7.binaryMatch3(input);

        //Then
        Assertions.assertEquals(expected, result);
    }
}
