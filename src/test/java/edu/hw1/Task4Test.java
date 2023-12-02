package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task4Test {

    @ParameterizedTest
    @CsvSource({"'123456', '214365'", "'hTsii  s aimex dpus rtni.g', 'This is a mixed up string.'", "'badce', 'abcde'", "'a', 'a'", "'ab', 'ba'", "'tenet', 'etent'"})
    @DisplayName("Correcting the order of characters in a string")
    void testFixString(String input, String expected){

        // When
        Task4 task4 = new Task4();
        String result = task4.fixString(input);

        //Then
        Assertions.assertEquals(result, expected);

    }
}
