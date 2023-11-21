package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task8Test {

    Task8 task8 = new Task8();

    @ParameterizedTest
    @DisplayName("Checks that the expression is of odd length")
    @CsvSource({"'010', 'true'", "'111100', 'false'", " , 'false'",
        "'12023', 'false'"})
    void binaryMatch1Test(String input, boolean expected) {

        //When
        boolean result = task8.binaryMatch1(input);

        //Then
        Assertions.assertEquals(expected, result);
    }


    @ParameterizedTest
    @DisplayName("Checks that the expression starts with 0 and has an odd length, or starts with 1 and has an even length")
    @CsvSource({"'010', 'true'", "'111100', 'true'", " , 'false'",
        "'12023', 'false'", "'0111', 'false'", "'100', 'false'"})
    void binaryMatch2Test(String input, boolean expected) {

        //When
        boolean result = task8.binaryMatch2(input);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @DisplayName("Checks that the number of 0 in the expression is a multiple of 3")
    @CsvSource({"'010101', 'true'", "'0001000', 'true'", " , 'false'",
        "'12023', 'false'", "'0111', 'false'", "'100', 'false'"})
    void binaryMatch3Test(String input, boolean expected) {

        //When
        boolean result = task8.binaryMatch3(input);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @DisplayName("Takes any string except 11 or 111")
    @CsvSource({"'010101', 'true'", "'0001111', 'true'", " , 'false'",
        "'12023', 'false'", "'111', 'false'", "'11', 'false'"})
    void binaryMatch4Test(String input, boolean expected) {

        //When
        boolean result = task8.binaryMatch4(input);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @DisplayName("Checks that every odd character in the expression is equal to 1")
    @CsvSource({"'111011', 'true'", "'1011111', 'true'", " , 'false'",
        "'12023', 'false'", "'110', 'false'", "'110', 'false'"})
    void binaryMatch5Test(String input, boolean expected) {

        //When
        boolean result = task8.binaryMatch5(input);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @DisplayName("Checks that the expression contains at least two 0s and at most one 1")
    @CsvSource({"'001', 'true'", "'10000', 'true'", " , 'false'",
        "'12023', 'false'", "'11000', 'false'", "'10', 'false'"})
    void binaryMatch6Test(String input, boolean expected) {

        //When
        boolean result = task8.binaryMatch6(input);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @DisplayName("Checks that there are no consecutive 1s in the expression")
    @CsvSource({"'001', 'true'", "'10100', 'true'", " , 'false'",
        "'12023', 'false'", "'10110', 'false'", "'110', 'false'"})
    void binaryMatch7Test(String input, boolean expected) {

        //When
        boolean result = task8.binaryMatch7(input);

        //Then
        Assertions.assertEquals(expected, result);
    }
}
