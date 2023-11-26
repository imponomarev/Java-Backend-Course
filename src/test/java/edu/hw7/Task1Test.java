package edu.hw7;

import edu.hw7.task1.Counter;
import edu.hw7.task1.CounterThread;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {


    @Test
    void testIncrement() {

        Counter counter = new Counter();
        counter.increment();
        assertEquals(1, counter.getCount());
    }

    @Test
    void testGetCount() {

        Counter counter = new Counter();
        assertEquals(0, counter.getCount());
    }

    @Test
    void testRun() throws InterruptedException {

        Counter counter = new Counter();
        CounterThread thread = new CounterThread(counter);
        thread.start();
        thread.join();
        assertEquals(1000, counter.getCount());

    }

    @Test
    void testThreadSafety() throws InterruptedException {

        Counter counter = new Counter();
        int numThreads = 100;
        int numIncrementsPerThread = 1000;

        CounterThread[] threads = new CounterThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new CounterThread(counter);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }

        assertEquals(numThreads * numIncrementsPerThread, counter.getCount());
    }
}
