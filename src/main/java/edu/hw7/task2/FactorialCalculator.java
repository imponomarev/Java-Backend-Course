package edu.hw7.task2;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class FactorialCalculator {

    private FactorialCalculator() {}

    public static BigInteger calculateFactorial(long number) {

        return LongStream.rangeClosed(1, number)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }



}
