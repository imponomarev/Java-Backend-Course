package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task3 {
    static final double SEPARATOR = 0.5;


    public interface Connection extends AutoCloseable {
        void execute(String command);
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public static class ConnectionException extends RuntimeException {
        public ConnectionException(String message) {
            super(message);
        }

    }

    public static class StableConnection implements Connection {
        Logger logger = LogManager.getLogger();

        private static final String CONNECTION_CLOSED_MESSAGE = "connection closed";

        @Override
        public void execute(String command) {
            logger.info(command);
        }

        @Override
        public void close() {
            logger.info(CONNECTION_CLOSED_MESSAGE);

        }
    }

    public static class FaultyConnection implements Connection {

        Logger logger = LogManager.getLogger();

        private static final String CONNECTION_CLOSED_MESSAGE = "connection closed";

        @Override
        public void execute(String command) {
            double randomState = Math.random();
            if (randomState >= SEPARATOR) {
                logger.info(command);
            } else {
                throw new ConnectionException("connection is not established");
            }
        }

        @Override
        public void close() {
            logger.info(CONNECTION_CLOSED_MESSAGE);
        }
    }

    public static class DefaultConnectionManager implements ConnectionManager {

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

    public static class FaultyConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            return new FaultyConnection();
        }
    }

    public static final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;


        public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
            this.manager = manager;
            this.maxAttempts = maxAttempts;
        }

        public void updatePackages() {
            tryExecute("apt update && apt upgrade -y");
        }

        void tryExecute(String command) {
            for (int i = 0; i < maxAttempts; i++) {
                try {
                    Connection connection = manager.getConnection();
                    boolean succes = true;
                    try {
                        connection.execute(command);
                    } catch (ConnectionException exception) {
                        succes = false;
                    }
                    if (succes) {
                        return;
                    }
                    try {
                        connection.close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } catch (ConnectionException e) {
                    throw new ConnectionException("failure connection");
                }
            }
            throw new ConnectionException("failed to execute command: " + command);
        }
    }
}
