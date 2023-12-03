package edu.hw8.task1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Server {

    private static final int PORT = 8080;

    private static final int MAX_CONNECTIONS = 5;

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int BUFF_SIZE = 1024;


    private static final String[] KEYWORDS = {
        "личности",
        "оскорбления",
        "глупый",
        "интеллект",
    };

    private static final String[] QUOTES = {
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления",
    };

    private final ExecutorService executorService;

    private final Semaphore semaphore;

    private boolean running;

    public Server() {
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
        semaphore = new Semaphore(MAX_CONNECTIONS);
        running = true;
    }

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            LOGGER.info("Server started on port " + PORT);

            while (running) {

                Socket clientSocket = serverSocket.accept();

                try {

                    semaphore.acquire();

                    executorService.execute(new ClientHandler(clientSocket));

                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void stop() {
        running = false;
        executorService.shutdown();
    }

    public class ClientHandler implements Runnable {

        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {

            try (InputStream inputStream = clientSocket.getInputStream();
                 OutputStream outputStream = clientSocket.getOutputStream()) {

                ByteBuffer buffer = ByteBuffer.allocate(BUFF_SIZE);

                while (true) {

                    buffer.clear();
                    int bytesRead = inputStream.read(buffer.array());

                    if (bytesRead > 0) {

                        String keyword = new String(buffer.array(), 0, bytesRead, StandardCharsets.UTF_8);
                        String quote = getQuoteForKeyword(keyword);
                        buffer.clear();
                        buffer.put(quote.getBytes(StandardCharsets.UTF_8));

                        buffer.flip();
                        outputStream.write(buffer.array(), 0, buffer.limit());

                    } else {
                        break;
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                } finally {
                    semaphore.release();
                }
            }
        }

        private String getQuoteForKeyword(String keyword) {

            for (int i = 0; i < KEYWORDS.length; i++) {
                if (KEYWORDS[i].equalsIgnoreCase(keyword)) {
                    return QUOTES[i];
                }
            }

            return "К сожалению, я не знаю цитаты для данного слова";
        }
    }
}
