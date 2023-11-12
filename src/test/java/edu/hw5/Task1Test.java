package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.text.ParseException;

class Task1Test {

    @Test
    void averagePerSessionTest1() throws ParseException {

        //Given
        Task1 task1 = new Task1();
        String[] input = {"2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 01:20"};

        //When
        String result = task1.averagePerSession(input);

        //Then
        Assertions.assertEquals("3ч 40м", result);
    }

    @Test
    void averagePerSessionTest2() throws ParseException {

        //Given
        Task1 task1 = new Task1();
        String[] input = {"2022-03-12, 20:20 - 2022-03-12, 20:20", "2022-04-01, 21:30 - 2022-04-01, 21:30"};

        //When
        String result = task1.averagePerSession(input);

        //Then
        Assertions.assertEquals("0м", result);
    }

    @Test
    void averagePerSessionTest3() throws ParseException {

        //Given
        Task1 task1 = new Task1();
        String[] input = {"2022-03-11, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 21:30"};

        //When
        String result = task1.averagePerSession(input);

        //Then
        Assertions.assertEquals("1д 1ч 45м", result);
    }

    @Test
    void averagePerSessionTest4() throws ParseException {

        //Given
        Task1 task1 = new Task1();
        String[] input = {};

        //When
        String result = task1.averagePerSession(input);

        //Then
        Assertions.assertEquals(null, result);
    }

    @Test
    void averagePerSessionTest5(){

        //Given
        Task1 task1 = new Task1();
        String[] input = {"2022-03-12, 20:20 - 2022-03-12, 23:50", ""};

        //Then
        Assertions.assertThrows(ParseException.class, () -> task1.averagePerSession(input));
    }

    @Test
    void averagePerSessionTest6() throws ParseException {

        //Given
        Task1 task1 = new Task1();
        String[] input = {"2022-03-12, 20:20 - 2022-03-12, 23:50"};

        //When
        String result = task1.averagePerSession(input);

        //Then
        Assertions.assertEquals("3ч 30м", result);
    }

    @Test
    void averagePerSessionTest7() throws ParseException {

        //Given
        Task1 task1 = new Task1();
        String[] input = {"2022-03-12, 10:00 - 2022-03-12, 12:30",
            "2022-03-12, 14:00 - 2022-03-12, 16:30",
            "2022-03-12, 18:00 - 2022-03-12, 20:30",
            "2022-03-12, 22:00 - 2022-03-13, 01:00"};

        //When
        String result = task1.averagePerSession(input);

        //Then
        Assertions.assertEquals("2ч 37м", result);
    }

    @Test
    void averagePerSessionTest8() throws ParseException {

        //Given
        Task1 task1 = new Task1();
        String[] input = {"2022-03-12, 10:00 - 2022-03-12, 10:03",
            "2022-03-12, 12:00 - 2022-03-12, 12:05",
            "2022-03-12, 14:00 - 2022-03-12, 14:10"};

        //When
        String result = task1.averagePerSession(input);

        //Then
        Assertions.assertEquals("6м", result);
    }
}
