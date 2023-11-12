package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task5Test {

    @ParameterizedTest
    @CsvSource({"'А123ВЕ777','true'", "'А123ВЕ45', 'true'",
    "'О777ОО177', 'true'", "'123АВЕ777', 'false'",
    "'А123ВГ77', 'false'", "'А123ВЕ7777', 'false'",
    "'К456МН01', 'true'", "'', 'false'",
    "'А123ВЕ777   ', 'true'"})
    void validateLicensePlatesTest(String number, boolean expected) {

        //Given
        Task5 task5 = new Task5();

        //When
        boolean result = task5.validateLicensePlates(number);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void validateLicensePlatesTest2() {

        //Given
        Task5 task5 = new Task5();
        boolean expected = false;

        //When
        boolean result = task5.validateLicensePlates(null);

        //Then
        Assertions.assertEquals(expected, result);

    }
}
