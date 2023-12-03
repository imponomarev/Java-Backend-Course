package edu.hw8;

import edu.hw8.task1.Client;
import edu.hw8.task1.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {


    private static final int NUM_CLIENTS = 5;

    private static Server server;

    private static CountDownLatch startLatch;

    private static CountDownLatch connectLatch;

    private static ExecutorService executorService;

    private static List<Client> clients;


    @BeforeAll
    static void startServer() {

        server = new Server();


        Thread thread = new Thread(() -> {
            server.start();
        });

        thread.start();

    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @BeforeAll
    static void setUp() {

        startLatch = new CountDownLatch(1);
        connectLatch = new CountDownLatch(NUM_CLIENTS);
        executorService = Executors.newFixedThreadPool(NUM_CLIENTS);
        clients = new ArrayList<>();
        for (int i = 0; i < NUM_CLIENTS; i++) {
            clients.add(new Client());
        }
    }

    @AfterAll
    static void tearDown() {

        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clients.forEach(Client::disconnect);
    }


    @Test
    void testServerClientConnection() throws InterruptedException {

        Client client = new Client();
        client.connect();

        String response = client.start("интеллект");

        assertEquals(
            "Чем ниже интеллект, тем громче оскорбления",
            response
        );

        client.disconnect();
    }

    @Test
    void testServerConnectFiveClients() throws InterruptedException {

        assertDoesNotThrow(() -> {
            for (int i = 0; i < NUM_CLIENTS; i++) {
                Client client = new Client();
                executorService.execute(() -> {
                    try {
                        startLatch.await();
                        client.connect();
                        connectLatch.countDown();
                        String response = client.start("личности");
                        assertEquals("Не переходи на личности там, где их нет", response);
                        client.disconnect();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

            startLatch.countDown();
            connectLatch.await(5, TimeUnit.SECONDS);

        });
    }
}
