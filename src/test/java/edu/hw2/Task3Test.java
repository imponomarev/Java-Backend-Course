package edu.hw2;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    private Logger logger;

    @Test
    @DisplayName("faulty connection manager's connection check test")
    void faultyConnectionTest() {
        //Given
        Task3.FaultyConnectionManager manager = new Task3.FaultyConnectionManager();

        //When
        Task3.Connection connection = manager.getConnection();

        Task3.FaultyConnection expected = new Task3.FaultyConnection();

        //Then
        assertEquals(connection.getClass(), expected.getClass());
    }

}
