package edu.hw1;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class Task0Test {

    @Test
    void testForLogger() {
        Task0 task0 = new Task0();
        Logger logger = Mockito.mock(Logger.class);
        task0.setLogger(logger);

        task0.helloWorld();

        Mockito.verify(logger).info("Hello World!");
    }
}
