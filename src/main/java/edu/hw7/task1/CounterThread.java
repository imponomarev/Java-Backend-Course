package edu.hw7.task1;

public class CounterThread extends Thread {

    private static final int COUNT_OF_ITERATIONS = 1000;

    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < COUNT_OF_ITERATIONS; i++) {
            counter.increment();
        }
    }
}
