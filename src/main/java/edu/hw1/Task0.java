package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task0 {
    private Logger logger = LogManager.getLogger();

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void helloWorld() {
        logger.info("Hello World!");
    }
}
