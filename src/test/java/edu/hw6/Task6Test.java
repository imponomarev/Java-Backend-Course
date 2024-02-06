package edu.hw6;

import edu.hw6.task6.PortsScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task6Test {

    @Test
    void portsScannerTest() {
        Assertions.assertDoesNotThrow(PortsScanner::infoAboutPorts);
    }
}
