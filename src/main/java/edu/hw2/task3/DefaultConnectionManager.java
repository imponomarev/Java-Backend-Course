package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {

    static final double SEPARATOR = 0.5;

    @Override
    public Connection getConnection() {
        double randomState = Math.random();
        if (randomState >= SEPARATOR) {
            return new StableConnection();
        } else {
            return new FaultyConnection();
        }
    }
}
