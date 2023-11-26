package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MonteCarloPi {

    private static final AtomicInteger circleCount = new AtomicInteger(0);

    private static final AtomicLong totalCount = new AtomicLong(0);

    private static final Object lock = new Object();


    private MonteCarloPi() {}

    public static double calculatePi(int iterations, int numThreads,  ThreadLocalRandom random) {

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {

            threads[i] = new Thread(() -> {

                int localCircleCount = 0;
                int localTotalCount = 0;

                for (int j = 0; j < iterations / numThreads; j++) {

                    double x = random.nextDouble();
                    double y = random.nextDouble();

                    double distance = Math.sqrt(x * x + y * y);

                    if (distance <= 1) {
                        localCircleCount++;
                    }

                    localTotalCount++;
                }

                synchronized (lock) {
                    circleCount.addAndGet(localCircleCount);
                    totalCount.addAndGet(localTotalCount);
                }

            });
            threads[i].start();
        }

        for (Thread thread : threads) {

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return 4.0 * (circleCount.get() / (double) totalCount.get());
    }
}
