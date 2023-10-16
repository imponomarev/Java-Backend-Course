package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task0 {
    private Logger LOGGER = LogManager.getLogger();

    public void setLogger(Logger logger) {
        this.LOGGER = logger;
    }

    public void helloWorld() {
        LOGGER.info("Hello World!");
    }
}
