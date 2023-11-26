package edu.hw7;

import edu.hw7.task4.MonteCarloPi;
import edu.hw7.task4.PiApproximationResults;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task4Test {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String MS = " ms";



    @Test
    void printResultsTest() {

        int[] iterations = {10000000, 100000000, 1000000000};
        int numThreads = Runtime.getRuntime().availableProcessors();

        PiApproximationResults.printResults(iterations, numThreads);

    }



    @Test
    void testAverageSpeedup() {

        int[] iterations = {10000000, 100000000, 1000000000};
        int numThreads = Runtime.getRuntime().availableProcessors();

        ThreadLocalRandom random = ThreadLocalRandom.current();

        long singleThreadTimeSum = 0;
        long multiThreadTimeSum = 0;

        for (int iteration : iterations) {

            long singleThreadTime = measureSingleThreadTime(iteration, random);
            long multiThreadTime = measureMultiThreadTime(iteration, numThreads, random);

            singleThreadTimeSum += singleThreadTime;
            multiThreadTimeSum += multiThreadTime;
        }

        double averageSingleThreadTime = singleThreadTimeSum / (double) iterations.length;
        double averageMultiThreadTime = multiThreadTimeSum / (double) iterations.length;

        double speedup = averageSingleThreadTime / averageMultiThreadTime;

        LOGGER.info("Average Single-threaded Execution Time: " + averageSingleThreadTime + MS);
        LOGGER.info("Average Multi-threaded Execution Time: " + averageMultiThreadTime + MS);
        LOGGER.info("Speedup: " + speedup);

    }

    private long measureSingleThreadTime(int iterations, ThreadLocalRandom random) {

        long startTime = System.currentTimeMillis();
        MonteCarloPi.calculatePi(iterations, 1, random);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;

    }

    private long measureMultiThreadTime(int iterations, int numThreads, ThreadLocalRandom random) {

        long startTime = System.currentTimeMillis();
        MonteCarloPi.calculatePi(iterations, numThreads, random);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;

    }



    @Test
    void testIncreasingExecutionTimeSingleThread() {

        int[] iterations = {10000000, 100000000, 1000000000};

        ThreadLocalRandom random = ThreadLocalRandom.current();

        long previousExecutionTime = Long.MIN_VALUE;

        for (int iteration : iterations) {
            long executionTime = measureSingleThreadTime(iteration, random);

            assertTrue(executionTime > previousExecutionTime, "Execution time is not increasing");
            previousExecutionTime = executionTime;
        }
    }

    @Test
    void testIncreasingExecutionTimeMultiThread() {

        int[] iterations = {10000000, 100000000, 1000000000};
        int numThreads = Runtime.getRuntime().availableProcessors();

        ThreadLocalRandom random = ThreadLocalRandom.current();

        long previousExecutionTime = Long.MIN_VALUE;

        for (int iteration : iterations) {
            long executionTime = measureMultiThreadTime(iteration, numThreads, random);

            assertTrue(executionTime > previousExecutionTime, "Execution time is not increasing");
            previousExecutionTime = executionTime;
        }
    }

    @Test
    void testDecreasingErrorSingleThread() {

        int[] iterations = {10, 1000, 100000};

        ThreadLocalRandom random = ThreadLocalRandom.current();

        double previousError = Double.MAX_VALUE;

        for (int iteration : iterations) {
            double piApproximation = MonteCarloPi.calculatePi(iteration, 1, random);
            double error = Math.abs(piApproximation - Math.PI);

            assertTrue(error < previousError, "Error is not decreasing");
            previousError = error;
        }
    }

    @Test
    void testDecreasingErrorMultiThread() {

        int[] iterations = {1000, 1000000, 1000000000};
        int numThreads = Runtime.getRuntime().availableProcessors();

        ThreadLocalRandom random = ThreadLocalRandom.current();

        double previousError = Double.MAX_VALUE;

        for (int iteration : iterations) {
            double piApproximation = MonteCarloPi.calculatePi(iteration, numThreads, random);
            double error = Math.abs(piApproximation - Math.PI);

            assertTrue(error < previousError, "Error is not decreasing");
            previousError = error;
        }
    }
}
