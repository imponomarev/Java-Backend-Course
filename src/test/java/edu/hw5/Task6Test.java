package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task6Test {

    Task6 task6 = new Task6();

    @ParameterizedTest
    @CsvSource({"'abc', 'achfdbaabgabcaabg', 'true'", "'gk', 'geeks', 'true'", "'gs', 'geeks', 'true'",
        "'', '', 'true'", "'', 'adsasd', 'true'"})
    void IsSubsequenceTestReturnsTrue(String S, String T, boolean expected) {

        //When
        boolean result = task6.isSubsequence(S, T);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({", , 'false'", "'abc', 'gjhfknjgfnh', 'false'",
        ",'asd', 'false'"})
    void IsSubsequenceTestReturnsFalse(String S, String T, boolean expected) {

        //When
        boolean result = task6.isSubsequence(S, T);

        //Then
        Assertions.assertEquals(expected, result);
    }
}
