package edu.hw1;

public class Task2 {
    public int countDigits(int number){
        int countOfDigits = 0;

        if (number == 0){
            countOfDigits = 1;
        }
        while (Math.abs(number) > 0){
            number /= 10;
            countOfDigits++;
        }
        return countOfDigits;
    }
}
