package edu.hw10.task2;

public class FibCalculatorClass implements FibCalculator {
    @Override
    public long fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
