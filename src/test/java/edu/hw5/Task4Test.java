package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task4Test {

    Task4 task4 = new Task4();

    @ParameterizedTest
    @CsvSource({"'Hello~', 'true'", "'asdcv!', 'true'",
    "'@sjsajd', 'true'", "'pass#', 'true'", "'asdsad$', 'true'",
    "'oracle%', 'true'", "'bip^', 'true'", "'input&', 'true'",
    "'duck*', 'true'", "'|asdasds', 'true'",
    "' hello~!@#$%^&*|', 'true'"})

    void validatePasswordContainsNecessarySymbolsTest(String password, boolean expected) {

        //When
        boolean result = task4.validatePassword(password);

        //Then
        Assertions.assertEquals(expected, result);
    }


    @ParameterizedTest
    @CsvSource({"'asdasd', 'false'", "'', 'false'"})
    void validatePasswordDoesNotContainsNecessarySymbolsTest(String password, boolean expected) {

        //When
        boolean result = task4.validatePassword(password);

        //Then
        Assertions.assertEquals(expected, result);
    }



    @Test
    void validatePasswordWithNullTest() {

        //Given
        boolean expected = false;

        //When
        boolean result = task4.validatePassword(null);

        //Then
        Assertions.assertEquals(expected, result);

    }


}
