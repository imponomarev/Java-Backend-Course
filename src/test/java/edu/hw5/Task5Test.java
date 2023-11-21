package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task5Test {

    Task5 task5 = new Task5();

    @ParameterizedTest
    @CsvSource({"'А123ВЕ777','true'", "'А123ВЕ45', 'true'",
    "'О777ОО177', 'true'", "'К456МН01', 'true'",
    "'А123ВЕ777   ', 'true'"})
    void validateLicensePlatesTestReturnsTrue(String number, boolean expected) {

        //When
        boolean result = task5.validateLicensePlates(number);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @ParameterizedTest
    @CsvSource({"'123АВЕ777', 'false'",
        "'А123ВГ77', 'false'", "'А123ВЕ7777', 'false'",
        "'', 'false'"})
    void validateLicensePlatesTestReturnsFalse(String number, boolean expected) {

        //When
        boolean result = task5.validateLicensePlates(number);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void validateLicensePlatesWitnNullTest() {

        //Given
        boolean expected = false;

        //When
        boolean result = task5.validateLicensePlates(null);

        //Then
        Assertions.assertEquals(expected, result);

    }
}
