package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task4Test {

    @ParameterizedTest
    @CsvSource({"'Hello~', 'true'", "'asdcv!', 'true'",
    "'@sjsajd', 'true'", "'pass#', 'true'", "'asdsad$', 'true'",
    "'oracle%', 'true'", "'bip^', 'true'", "'input&', 'true'",
    "'duck*', 'true'", "'|asdasds', 'true'", "'asdasd', 'false'",
    "' hello~!@#$%^&*|', 'true'", "'', 'false'"})

    void validatePasswordTest(String password, boolean expected) {

        //Given
        Task4 task4 = new Task4();

        //When
        boolean result = task4.validatePassword(password);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void validatePasswordTest2() {

        //Given
        Task4 task4 = new Task4();
        boolean expected = false;

        //When
        boolean result = task4.validatePassword(null);

        //Then
        Assertions.assertEquals(expected, result);

    }


}
