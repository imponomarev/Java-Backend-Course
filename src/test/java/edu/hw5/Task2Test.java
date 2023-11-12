package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task2Test {

    @Test
    void findFridayThirteenTest() {

        //Given
        Task2 task2 = new Task2();

        //When
        String result =  task2.findFridayThirteen(1925);

        //Then
        Assertions.assertEquals("[1925-02-13, 1925-03-13, 1925-11-13]", result);
    }

    @Test
    void findFridayThirteenTest2() {

        //Given
        Task2 task2 = new Task2();

        //When
        String result =  task2.findFridayThirteen(2024);

        //Then
        Assertions.assertEquals("[2024-09-13, 2024-12-13]", result);
    }
    @Test
    void findFridayThirteenTest3() {

        //Given
        Task2 task2 = new Task2();

        //When
        String result =  task2.findFridayThirteen(1926);

        //Then
        Assertions.assertEquals("[1926-08-13]", result);
    }

    @Test
    void findNearFridayThirteenTest() {
        //Given
        Task2 task2 = new Task2();

        //When
        String result = task2.findNearFridayThirteen("1925-02-13");

        //Then
        Assertions.assertEquals("1925-03-13", result);
    }

    @Test
    void findNearFridayThirteenTest2() {
        //Given
        Task2 task2 = new Task2();

        //When
        String result = task2.findNearFridayThirteen("1925-11-13");

        //Then
        Assertions.assertEquals("1926-08-13", result);
    }
}
