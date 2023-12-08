package edu.hw9.task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {

    private Map<String, List<Double>> data;
    private Map<String, ReentrantReadWriteLock> locks;

    public StatsCollector() {
        data = new ConcurrentHashMap<>();
        locks = new HashMap<>();
    }

    public void push(String metricName, double[] values) {

        ReentrantReadWriteLock lock;

        synchronized (this) {
            lock = locks.computeIfAbsent(metricName, k -> new ReentrantReadWriteLock());
        }

        lock.writeLock().lock();
        try {

            List<Double> metricData = data.computeIfAbsent(metricName, k -> new ArrayList<>());

            for (double value : values) {
                metricData.add(value);
            }

        } finally {
            lock.writeLock().unlock();
        }
    }

    public List<Stats> stats() {

        List<Stats> result = new ArrayList<>();

        for (Map.Entry<String, List<Double>> entry : data.entrySet()) {

            String metricName = entry.getKey();
            List<Double> metricData = entry.getValue();

            ReentrantReadWriteLock lock = locks.get(metricName);
            lock.readLock().lock();

            try {
                double sum = 0;
                double min = Double.MAX_VALUE;
                double max = Double.MIN_VALUE;

                for (double value : metricData) {
                    sum += value;
                    min = Math.min(min, value);
                    max = Math.max(max, value);
                }

                double average = sum / metricData.size();

                result.add(new Stats(metricName, sum, average, min, max, metricData.size()));

            } finally {
                lock.readLock().unlock();
            }
        }

        return result;
    }
}
