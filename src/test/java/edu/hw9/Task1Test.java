package edu.hw9;

import edu.hw9.task1.Stats;
import edu.hw9.task1.StatsCollector;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class Task1Test {

    @Test
    void testStatsCollectorPushMultithreading() throws InterruptedException {

        StatsCollector collector = new StatsCollector();

        int numThreads = 10;
        int valuesPerThread = 100;
        int totalValues = numThreads * valuesPerThread;

        CountDownLatch latch = new CountDownLatch(numThreads);

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {

            executorService.submit(() -> {
                try {
                    for (int j = 0; j < valuesPerThread; j++) {
                        collector.push("metric_name", new double[]{Math.random() * 10});
                    }
                } finally {
                    latch.countDown();
                }
            });

        }

        assertTrue(latch.await(5, TimeUnit.SECONDS));

        executorService.shutdown();
        assertTrue(executorService.awaitTermination(5, TimeUnit.SECONDS));

        assertEquals(totalValues, collector.stats().get(0).dataSize());
    }


    @Test
    void testPushAndStatsWithConcurrentPushes() throws InterruptedException {

        StatsCollector collector = new StatsCollector();
        int numThreads = 10;
        int numValuesPerThread = 100;
        int totalValues = numThreads * numValuesPerThread;
        CountDownLatch latch = new CountDownLatch(numThreads);

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {

            executorService.submit(() -> {
                double[] values = new double[numValuesPerThread];
                for (int j = 0; j < numValuesPerThread; j++) {
                    values[j] = 1.0;
                }
                collector.push("metric_name", values);
                latch.countDown();
            });
        }

        executorService.shutdown();
        latch.await();

        assertEquals(1, collector.stats().size());

        Stats stats = collector.stats().get(0);

        assertEquals(totalValues, stats.dataSize());

        double expectedSum = numThreads * numValuesPerThread;
        assertEquals(expectedSum, stats.sum());

        double expectedAverage = expectedSum / totalValues;
        assertEquals(expectedAverage, stats.average());

        assertEquals(1.0, stats.min());
        assertEquals(1.0, stats.max());
    }



    @Test
    void testMultipleThreadsPushAndStats() throws InterruptedException {

        StatsCollector collector = new StatsCollector();
        int numThreads = 10;
        int numValuesPerThread = 100;

        CountDownLatch pushLatch = new CountDownLatch(numThreads);
        CountDownLatch statsLatch = new CountDownLatch(numThreads);

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {

            int threadIndex = i;
            executorService.submit(() -> {
                double[] values = new double[numValuesPerThread];
                for (int j = 0; j < numValuesPerThread; j++) {
                    values[j] = threadIndex + j;
                }
                collector.push("metric_" + threadIndex, values);
                pushLatch.countDown();
            });
            executorService.submit(() -> {
                List<Stats> stats = collector.stats();
                for (Stats s : stats) {
                    if (s.metricName().equals("metric_" + threadIndex)) {

                        assertEquals(numValuesPerThread, s.dataSize());

                        double expectedSum = (numValuesPerThread - 1) * numValuesPerThread / 2
                            + threadIndex * numValuesPerThread;

                        assertEquals(expectedSum, s.sum());

                        double expectedAverage = expectedSum / numValuesPerThread;
                        assertEquals(expectedAverage, s.average());

                        assertEquals(threadIndex, s.min());
                        assertEquals(threadIndex + numValuesPerThread - 1, s.max());
                        break;
                    }
                }
                statsLatch.countDown();
            });
        }
        executorService.shutdown();
        pushLatch.await();
        statsLatch.await();
    }
}
