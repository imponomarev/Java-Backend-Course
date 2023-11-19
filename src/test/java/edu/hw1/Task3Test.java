package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class Task3Test {

    @Test
    @DisplayName("Checking arrays for nestable: 1 case")
    void testIsNestableArray1(){

        Task3 task3 = new Task3();

        //Given
        int[] array1 = new int[]{1, 2, 3, 4};
        int[] array2 = new int[]{0, 6};


        //When
        boolean result = task3.isNestable(array1, array2);

        //Then
        Assertions.assertThat(result).isTrue();

    }

    @Test
    @DisplayName("Checking arrays for nestable: 2 case")
    void testIsNestableArray2(){

        Task3 task3 = new Task3();

        //Given
        int[] array1 = new int[]{3, 1};
        int[] array2 = new int[]{4, 0};


        //When
        boolean result = task3.isNestable(array1, array2);

        //Then
        Assertions.assertThat(result).isTrue();

    }


    @Test
    @DisplayName("Checking arrays for nestable: 3 case")
    void testIsNestableArray3(){

        Task3 task3 = new Task3();

        //Given
        int[] array1 = new int[]{9, 9, 8};
        int[] array2 = new int[]{8, 9};


        //When
        boolean result = task3.isNestable(array1, array2);

        //Then
        Assertions.assertThat(result).isFalse();

    }


    @Test
    @DisplayName("Checking arrays for nestable: 4 case")
    void testIsNestableArray4(){

        Task3 task3 = new Task3();

        //Given
        int[] array1 = new int[]{1, 2, 3, 4};
        int[] array2 = new int[]{2, 3};


        //When
        boolean result = task3.isNestable(array1, array2);

        //Then
        Assertions.assertThat(result).isFalse();

    }

    @Test
    @DisplayName("Checking arrays for nestable: test with negative numbers 1 case")
    void testIsNestableArray5(){

        Task3 task3 = new Task3();

        //Given
        int[] array1 = new int[]{-1, -2, -3, -4};
        int[] array2 = new int[]{-2, -3};


        //When
        boolean result = task3.isNestable(array1, array2);

        //Then
        Assertions.assertThat(result).isFalse();

    }

    @Test
    @DisplayName("Checking arrays for nestable: test with negative numbers 2 case")
    void testIsNestableArray6(){

        Task3 task3 = new Task3();

        //Given
        int[] array1 = new int[]{-1, -2, -3};
        int[] array2 = new int[]{0, -5, -6};


        //When
        boolean result = task3.isNestable(array1, array2);

        //Then
        Assertions.assertThat(result).isTrue();

    }
}
