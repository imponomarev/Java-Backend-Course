package edu.hw2.task3;


public final class PopularCommandExecutor {
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
        Exception reasonForRetry = null;

        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                boolean succes = true;
                try {
                    connection.execute(command);
                } catch (ConnectionException exception) {
                    succes = false;
                    reasonForRetry = exception;
                }
                if (succes) {
                    return;
                }
            } catch (ConnectionException e) {
                reasonForRetry = e;
                throw new ConnectionException("failure connection", e);
            } catch (Exception e) {
                reasonForRetry = e;
                throw new RuntimeException(e);

            }
        }
        throw new ConnectionException("failed to execute command: " + command +
            " after " + maxAttempts + " attempts", reasonForRetry);
    }
}
