package edu.hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {

    @Test
    @DisplayName("Complex expression test")
    public void testExpr1() {

        var two = new Task1.Expr.Constant(2);
        var four = new Task1.Expr.Constant(4);
        var negOne = new Task1.Expr.Negate(new Task1.Expr.Constant(1));
        var sumTwoFour = new Task1.Expr.Addition(two, four);
        var mult = new Task1.Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Task1.Expr.Exponent(mult, 2);
        var result = new Task1.Expr.Addition(exp, new Task1.Expr.Constant(1));

        Assertions.assertEquals(37, result.evaluate());


    }

    @Test
    @DisplayName("Negate with negative value")
    public void testExpr2(){
        var minusFive = new Task1.Expr.Constant(-5);
        var result = new Task1.Expr.Negate(minusFive);

        Assertions.assertEquals(5, result.evaluate());
    }
}
