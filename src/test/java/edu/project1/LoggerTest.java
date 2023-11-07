package edu.project1;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class LoggerTest {
    @BeforeEach
    void setupLogger() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.reconfigure();
    }

    @Test
    void gameCompletesSuccessfullyTest() {
        String[] words = {"test"};
        WordBase wordBase = new WordBase(words);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Appender appender = new AbstractAppender("TestAppender", null, PatternLayout.createDefaultLayout()) {
            @Override
            public void append(LogEvent event) {
                try {
                    outputStream.write(getLayout().toByteArray(event));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(ConsoleHangman.class.getName());
        loggerConfig.addAppender(appender, null, null);
        context.updateLoggers();

        ConsoleHangman consoleHangman = new ConsoleHangman(wordBase, LogManager.getLogger(ConsoleHangman.class));

        String userInput = "t\ne\ns\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        consoleHangman.run();

        String output = outputStream.toString();
        Assertions.assertTrue(output.contains("Congratulations! You won!"));
    }

    @Test
    void gameCompletesUnsuccessfullyTest() {
        String[] words = {"test"};
        WordBase wordBase = new WordBase(words);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Appender appender = new AbstractAppender("TestAppender", null, PatternLayout.createDefaultLayout()) {
            @Override
            public void append(LogEvent event) {
                try {
                    outputStream.write(getLayout().toByteArray(event));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(ConsoleHangman.class.getName());
        loggerConfig.addAppender(appender, null, null);
        context.updateLoggers();

        ConsoleHangman consoleHangman = new ConsoleHangman(wordBase, LogManager.getLogger(ConsoleHangman.class));

        String userInput = "a\nb\nc\nd\nf\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        consoleHangman.run();

        String output = outputStream.toString();
        Assertions.assertTrue(output.contains("You lost! The word was: test"));
    }


    @Test
    void invalidInputTest() {
        String[] words = {"test"};
        WordBase wordBase = new WordBase(words);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Appender appender = new AbstractAppender("TestAppender", null, PatternLayout.createDefaultLayout()) {
            @Override
            public void append(LogEvent event) {
                try {
                    outputStream.write(getLayout().toByteArray(event));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(ConsoleHangman.class.getName());
        loggerConfig.addAppender(appender, null, null);
        context.updateLoggers();

        ConsoleHangman consoleHangman = new ConsoleHangman(wordBase, LogManager.getLogger(ConsoleHangman.class));

        String userInput = "te\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        consoleHangman.run();

        String output = outputStream.toString();

        Assertions.assertTrue(output.contains("Invalid input. Please enter a single letter."));
    }


    @Test
    void exitTest() {
        String[] words = {"test"};
        WordBase wordBase = new WordBase(words);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Appender appender = new AbstractAppender("TestAppender", null, PatternLayout.createDefaultLayout()) {
            @Override
            public void append(LogEvent event) {
                try {
                    outputStream.write(getLayout().toByteArray(event));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(ConsoleHangman.class.getName());
        loggerConfig.addAppender(appender, null, null);
        context.updateLoggers();

        ConsoleHangman consoleHangman = new ConsoleHangman(wordBase, LogManager.getLogger(ConsoleHangman.class));

        String userInput = "1\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        consoleHangman.run();

        String output = outputStream.toString();

        Assertions.assertTrue(output.contains("You lost! The word was: test"));
    }
}
