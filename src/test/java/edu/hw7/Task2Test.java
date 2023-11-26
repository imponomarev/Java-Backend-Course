package edu.hw7;

import edu.hw7.task2.FactorialCalculator;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {

    @Test
    void testCalculateFactorialWithSmallNumber() {

        long number = 5;
        BigInteger expectedFactorial = BigInteger.valueOf(120);

        BigInteger factorial = FactorialCalculator.calculateFactorial(number);

        assertEquals(expectedFactorial, factorial);
    }

    @Test
    void testCalculateFactorialWithLargeNumber() {

        long number = 20;
        BigInteger expectedFactorial = new BigInteger("2432902008176640000");

        BigInteger factorial = FactorialCalculator.calculateFactorial(number);

        assertEquals(expectedFactorial, factorial);
    }

    @Test
    void testCalculateFactorialWithZero() {

        long number = 0;
        BigInteger expectedFactorial = BigInteger.ONE;

        BigInteger factorial = FactorialCalculator.calculateFactorial(number);

        assertEquals(expectedFactorial, factorial);
    }
}
