package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.ThreadLocalRandom;

public class PiApproximationResults {

    private static final String SEPARATOR = "================================";

    private static final String MS = " ms";

    private static final Logger LOGGER = LogManager.getLogger();

    private PiApproximationResults() {}




    public static void printResults(int[] iterations, int numThreads) {

        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int iteration : iterations) {

            LOGGER.info("Number of simulations: " + iteration);
            LOGGER.info(SEPARATOR);

            long startTime = System.currentTimeMillis();
            double piApproximationSingleThread = MonteCarloPi.calculatePi(iteration, 1, random);
            long endTime = System.currentTimeMillis();
            long singleThreadTime = endTime - startTime;
            double singleThreadError = Math.abs(piApproximationSingleThread - Math.PI);

            LOGGER.info("Single-threaded approximation: " + piApproximationSingleThread);
            LOGGER.info("Single-threaded execution time: " + singleThreadTime + MS);
            LOGGER.info("Single-threaded error: " + singleThreadError);

            LOGGER.info("");

            startTime = System.currentTimeMillis();
            double piApproximationMultiThread = MonteCarloPi.calculatePi(iteration, numThreads, random);
            endTime = System.currentTimeMillis();
            long multiThreadTime = endTime - startTime;
            double multiThreadError = Math.abs(piApproximationMultiThread - Math.PI);

            LOGGER.info("Multi-threaded approximation: " + piApproximationMultiThread);
            LOGGER.info("Multi-threaded execution time: " + multiThreadTime + MS);
            LOGGER.info("Multi-threaded error: " + multiThreadError);

            LOGGER.info(SEPARATOR);
            LOGGER.info("");
        }
    }

}
