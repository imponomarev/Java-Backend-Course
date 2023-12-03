package edu.hw8.task2;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements MyThreadPool {

    private final int numThreads;

    private final BlockingQueue<Runnable> taskQueue;

    private final Thread[] threads;

    private volatile boolean isRunning = false;

    public FixedThreadPool(int numThreads) {
        this.numThreads = numThreads;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new Thread[numThreads];
    }

    public static FixedThreadPool create(int numThreads) {
        return new FixedThreadPool(numThreads);
    }

    @Override
    public void start() {

        if (isRunning) {
            throw new IllegalStateException("Thread pool is already running");
        }

        isRunning = true;

        for (int i = 0; i < numThreads; i++) {

            threads[i] = new Thread(() -> {
                try {
                    while (true) {
                        Runnable task = taskQueue.take();
                        task.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {

        if (!isRunning) {
            throw new IllegalStateException("Thread pool is not running");
        }

        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void close() {

        boolean isFinished = false;

        while (!isFinished) {
            isFinished = taskQueue.isEmpty();
        }

        isRunning = false;

        Arrays.stream(threads).forEach(Thread::interrupt);
    }
}
