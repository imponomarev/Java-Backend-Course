package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    static final double SEPARATOR = 0.5;

    private static final String CONNECTION_CLOSED_MESSAGE = "connection closed";

    Logger logger = LogManager.getLogger();

    @Override
    public void execute(String command) {
        double randomState = Math.random();
        if (randomState >= SEPARATOR) {
            logger.info(command);
        } else {
            throw new ConnectionException("connection is not established", new RuntimeException());
        }
    }

    @Override
    public void close() {
        logger.info(CONNECTION_CLOSED_MESSAGE);
    }
}
