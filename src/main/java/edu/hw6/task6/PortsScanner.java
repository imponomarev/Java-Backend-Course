package edu.hw6.task6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class PortsScanner {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int FIRST_PORT = 1;

    private static final int LAST_PORT = 49151;

    private static final Set<InfoAboutPort> INFO_ABOUT_PORT_SET = new TreeSet<>();

    private static final String TCP = "TCP";

    private static final String UDP = "UDP";

    private static final Map<Integer, String> TCP_PORTS = new HashMap<>();

    private static final Map<Integer, String> UDP_PORTS = new HashMap<>();

    private static final int MAX_LENGTH_OF_LINE = 4;

    static {
        useKnownPorts();
    }

    public static void infoAboutPorts() {

        String pattern = "{}\t{}\t{}";
        String pattern2 = "{}\t\t\t{}\t\t{}";

        scanTcpPorts();
        scanUdpPorts();
        LOGGER.info(pattern, "Протокол", "Порт", "Сервис");

        for (var info : INFO_ABOUT_PORT_SET) {
            LOGGER.info(pattern2, info.protocol(), info.port(), info.description());
        }
    }

    private static void scanTcpPorts() {

        for (int port = FIRST_PORT; port <= LAST_PORT; port++) {
            try (
                ServerSocket serverSocket = new ServerSocket(port);
            ) {

                serverSocket.setReuseAddress(true);

            } catch (IOException e) {
                INFO_ABOUT_PORT_SET.add(new InfoAboutPort(port, TCP, TCP_PORTS.getOrDefault(port, "")));
            }
        }
    }

    private static void scanUdpPorts() {

        for (int port = FIRST_PORT; port <= LAST_PORT; port++) {
            try (
                DatagramSocket datagramSocket = new DatagramSocket(port);
            ) {

                datagramSocket.setReuseAddress(true);

            } catch (IOException e) {
                INFO_ABOUT_PORT_SET.add(new InfoAboutPort(port, UDP, UDP_PORTS.getOrDefault(port, "")));
            }
        }
    }

    private static void useKnownPorts() {

        try (Scanner scanner = new Scanner(new File("src/main/resources/ports"))) {

            while (scanner.hasNext()) {
                var line = scanner.nextLine();
                var splitedLine = line.split(":");

                if (splitedLine.length == MAX_LENGTH_OF_LINE) {

                    TCP_PORTS.put(Integer.parseInt(splitedLine[0]),
                        splitedLine[MAX_LENGTH_OF_LINE - 1]);

                    UDP_PORTS.put(Integer.parseInt(splitedLine[0]),
                        splitedLine[MAX_LENGTH_OF_LINE - 1]);
                } else {
                    switch (splitedLine[1]) {

                        case (TCP) -> TCP_PORTS.put(Integer.parseInt(splitedLine[0]),
                            splitedLine[splitedLine.length - 1]);

                        case (UDP) -> UDP_PORTS.put(Integer.parseInt(splitedLine[0]),
                            splitedLine[splitedLine.length - 1]);

                        default -> {

                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error during using known ports", e);
        }
    }
}
