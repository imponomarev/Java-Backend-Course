package edu.hw10.task2;

public interface FactorialCalculator {

    @Cache(persist = true)
    long factorial(int number);

}
