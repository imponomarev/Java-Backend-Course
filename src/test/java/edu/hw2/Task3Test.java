package edu.hw2;

import edu.hw2.task3.*;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;



class Task3Test {

    private Logger logger;

    private ConnectionManager connectionManager;
    private PopularCommandExecutor commandExecutor;

    @Test
    @DisplayName("faulty connection manager's connection check test")
    void faultyConnectionTest() {
        //Given
        FaultyConnectionManager manager = new FaultyConnectionManager();

        //When
        Connection connection = manager.getConnection();

        FaultyConnection expected = new FaultyConnection();

        //Then
        assertEquals(connection.getClass(), expected.getClass());
    }

    @BeforeEach
    public void setUp() {

        connectionManager = Mockito.mock(ConnectionManager.class);

        commandExecutor = new PopularCommandExecutor(connectionManager, 3);
    }

    @Test
    @DisplayName("update packages successful execution check test")
    void testUpdatePackagesSuccessfulExecution() throws Exception {

        Connection connection = Mockito.mock(Connection.class);

        Mockito.when(connectionManager.getConnection()).thenReturn(connection);

        commandExecutor.updatePackages();

        Mockito.verify(connectionManager, Mockito.times(1)).getConnection();

        Mockito.verify(connection, Mockito.times(1)).execute("apt update && apt upgrade -y");

        Mockito.verify(connection, Mockito.times(1)).close();
    }

    @Test
    @DisplayName("update packages connection exception after max attempts check test")
    void testUpdatePackagesConnectionExceptionAfterMaxAttempts() throws Exception {

        Connection connection = Mockito.mock(Connection.class);

        Mockito.when(connectionManager.getConnection()).thenReturn(connection);

        Mockito.doThrow(new ConnectionException("connection is not established", new RuntimeException()))
            .when(connection).execute("apt update && apt upgrade -y");

        Assertions.assertThrows(
            ConnectionException.class,
            () -> commandExecutor.updatePackages()
        );

        Mockito.verify(connectionManager, Mockito.times(3)).getConnection();

        Mockito.verify(connection, Mockito.times(3)).execute("apt update && apt upgrade -y");

        Mockito.verify(connection, Mockito.times(3)).close();
    }
}
