package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MonteCarloPi {

    private static final AtomicInteger CIRCLE_COUNT = new AtomicInteger(0);

    private static final AtomicLong TOTAL_COUNT = new AtomicLong(0);

    private static final Object LOCK = new Object();

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int FOUR = 4;


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

                synchronized (LOCK) {
                    CIRCLE_COUNT.addAndGet(localCircleCount);
                    TOTAL_COUNT.addAndGet(localTotalCount);
                }

            });
            threads[i].start();
        }

        for (Thread thread : threads) {

            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.error("Error during calculation pi!", e);
            }
        }

        return FOUR * (CIRCLE_COUNT.get() / (double) TOTAL_COUNT.get());
    }
}
