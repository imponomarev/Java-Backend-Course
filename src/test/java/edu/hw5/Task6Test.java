package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task6Test {

    @ParameterizedTest
    @CsvSource({"'abc', 'achfdbaabgabcaabg', 'true'", "'gk', 'geeks', 'true'", "'gs', 'geeks', 'true'",
    ", , 'false'", "'', '', 'true'", "'abc', 'gjhfknjgfnh', 'false'",
    ",'asd', 'false'", "'', 'adsasd', 'true'"})
    void IsSubsequenceTest(String S, String T, boolean expected) {

        //Given
        Task6 task6 = new Task6();

        //When
        boolean result = task6.isSubsequence(S, T);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"'asd^', 'asdasd'", "'{}adssad', 'asdasdasdas'",
    "'igigif+', 'asdsadsasd'", "'<asdsa', 'adasdsad'",
    "'asd[asd]', 'asdasdad'", "'a.*asd', 'asdasdsa'",
    "'sad(2)asd', 'asdsad'", "'adsasd>','asdasd'",
    "'asdad|asd', 'asdasdasd'", "'hghghg$sa', 'ugfggfkjgf'",
    "'\\adasdas?s', 'adadsg'"})
    void IsSubsequenceTestWithWrongInput(String S, String T) {

        //Given
        Task6 task6 = new Task6();

        //When & Then
        Assertions.assertThrows(RuntimeException.class, () -> task6.isSubsequence(S, T));

    }
}
