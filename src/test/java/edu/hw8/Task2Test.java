package edu.hw8;

import edu.hw8.task2.FixedThreadPool;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {

    @Test
    void testFixedThreadPoolWithFibonacciNumbers() {

        long[] fibs = new long[10];
        long[] expected_fibs = new long[] {
            1, 1, 2, 3, 5, 8, 13, 21, 34, 55,
        };

        try (FixedThreadPool threadPool = new FixedThreadPool(4)) {
            threadPool.start();

            for (int i = 0; i < 10; i++) {
                final int index = i;
                threadPool.execute(() -> {
                    fibs[index] = calculateFibonacci(index);
                });
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(
                expected_fibs[i],
                fibs[i]
            );
        }
    }

    private static long calculateFibonacci(int n) {

        if (n <= 1) {
            return 1;

        } else {
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }

    }

}
