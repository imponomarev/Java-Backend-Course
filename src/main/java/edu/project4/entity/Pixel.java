package edu.project4.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Pixel {

    private int red;

    private int green;

    private int blue;

    private AtomicInteger hitCount;

    public Pixel(int red, int green, int blue, int hitCount) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hitCount = new AtomicInteger(hitCount);
    }

    public Pixel() {
        this(0, 0, 0, 0);
    }

    public synchronized int getRed() {
        return red;
    }

    public synchronized int getGreen() {
        return green;
    }

    public synchronized int getBlue() {
        return blue;
    }

    public int getHitCount() {
        return hitCount.get();
    }

    public synchronized void setRed(int red) {
        this.red = red;
    }

    public synchronized void setGreen(int green) {
        this.green = green;
    }

    public synchronized void setBlue(int blue) {
        this.blue = blue;
    }

    public void incrementHitCount() {
        this.hitCount.incrementAndGet();
    }
}
