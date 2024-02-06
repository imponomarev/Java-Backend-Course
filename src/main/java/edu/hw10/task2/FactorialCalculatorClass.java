package edu.hw10.task2;

public class FactorialCalculatorClass implements FactorialCalculator {

    @Override
    public long factorial(int number) {
        long result = 1;

        for (int i = 2; i <= number; i++) {
            result *= i;
        }

        return result;
    }
}
