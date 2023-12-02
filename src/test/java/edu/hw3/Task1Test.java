package edu.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;


class Task1Test {

    private Task1 task1;

    @BeforeEach
    public void setUp() {
        task1 = new Task1();
    }

    @ParameterizedTest
    @CsvSource({"'Hello world!', 'Svool dliow!'", "'Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler', " +
        "'Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi'",
    "'123абвabcdABDФБГ', '123абвzyxwZYWФБГ'", "'xyz 123   абг aaa', 'cba 123   абг zzz'"})
    @DisplayName("The Atbash cipher test")
    void atbastTest(String input, String expected) {

        //When
        String result = task1.atbash(input);

        //Then
        assertEquals(result, expected);


    }


}
