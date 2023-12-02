package edu.hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Task2.Rectangle()),
            Arguments.of(new Task2.Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Task2.Rectangle rect) {
        Task2.Rectangle rectangle = rect;
        rectangle = rectangle.setWidth(20);
        rectangle = rectangle.setHeight(10);

        assertThat(rectangle.area()).isEqualTo(200.0);
    }


    @Test
    @DisplayName("Rectangle's area check test")
    void testRectanlgeArea(){
        //Given
        Task2.Rectangle rectangle = new Task2.Rectangle(0 , 0);

        //When
        double result1 = rectangle.area();

        //Then
        Assertions.assertEquals(0.0,result1);
    }

    @Test
    @DisplayName("Square's area check test")
    void testSquareArea(){
        //Given
        Task2.Square square = new Task2.Square(-1);

        //When
        double result1 = square.area();

        //Then
        Assertions.assertEquals(1,result1);
    }

}
