package edu.hw1;

public class Task2{

    static final int TEN = 10;
    public int countDigits(int number){
        int countOfDigits = 0;
        int num = number;

        if(num == 0){
            countOfDigits = 1;
        }
        while(Math.abs(num) > 0){
            num /= TEN;
            countOfDigits++;
        }
        return countOfDigits;
    }
}
