package edu.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task2Test {

    private Task2 task2;

    @BeforeEach
    public void setUp() {
        task2 = new Task2();
    }


    @Test
    @DisplayName("clusterize function test with right input case 1")
    void testClusterizeWithRightInput1() {

        //Given
        String input = "()()()";
        String[] expected = {"()", "()", "()"};

        //When
        String[] result = task2.clusterize(input);

        //Then
        assertArrayEquals(expected, result);
    }

    @Test
    @DisplayName("clusterize function test with right input case 2")
    void testClusterizeWithRightInput2() {

        //Given
        String input = "((()))";
        String[] expected = {"((()))"};

        //When
        String[] result = task2.clusterize(input);

        //Then
        assertArrayEquals(expected, result);
    }

    @Test
    @DisplayName("clusterize function test with right input case 3")
    void testClusterizeWithRightInput3() {

        //Given
        String input = "((()))(())()()(()())";
        String[] expected = {"((()))", "(())", "()", "()", "(()())"};

        //When
        String[] result = task2.clusterize(input);

        //Then
        assertArrayEquals(expected, result);
    }

    @Test
    @DisplayName("clusterize function test with right input case 4")
    void testClusterizeWithRightInput4() {

        //Given
        String input = "((())())(()(()()))";
        String[] expected = {"((())())", "(()(()()))"};

        //When
        String[] result = task2.clusterize(input);

        //Then
        assertArrayEquals(expected, result);
    }

    @Test
    @DisplayName("clusterize function test with wrong input case 1")
    void testClusterizeWithWrongInput1() {

        //Given
        String input = ")()()";

        assertThrows(RuntimeException.class, () -> task2.clusterize(input));
    }

    @Test
    @DisplayName("clusterize function test with wrong input case 2")
    void testClusterizeWithWrongInput2() {

        //Given
        String input = "(()()";

        assertThrows(RuntimeException.class, () -> task2.clusterize(input));
    }

    @Test
    @DisplayName("clusterize function test with empty input")
    void testClusterizeWhenInputIsEmpty() {

        //Given
        String input = "";

        //When
        String[] result = task2.clusterize(input);

        //Then
        assertArrayEquals(new String[0], result);
    }


}
