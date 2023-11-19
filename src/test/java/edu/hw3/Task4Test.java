package edu.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class Task4Test {

    private Task4 task4;

    @BeforeEach
    public void setup() {
        task4 = new Task4();
    }

    @ParameterizedTest
    @CsvSource({"'2', 'II'", "'12', 'XII'", "'16', 'XVI'", "'-4', '-IV'", "'2356','MMCCCLVI'", "'3999', 'MMMCMXCIX'"})
    void testConvertToRoman(int input, String expected) {

        //When
        String result = task4.convertToRoman(input);

        //Then
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("test convert roman: too big input case")
    void testConvertRomanWrongCase() {

        //Given
        int input = 5000;

        //When & Then
        assertThrows(RuntimeException.class, () -> task4.convertToRoman(input), "Your input too big!");

    }
}
