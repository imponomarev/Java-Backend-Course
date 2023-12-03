package edu.hw8.task1;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {

    private static final String SERVER_HOST = "localhost";

    private static final int SERVER_PORT = 8080;

    private Socket socket;

    private static final Logger LOGGER = LogManager.getLogger();

    public Client() {
        this.socket = new Socket();
    }


    public void connect() {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }


    public String start(String keyword) {

        connect();

        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder responseBuilder = new StringBuilder();

            buffer.clear();
            buffer.put(keyword.getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            outputStream.write(buffer.array(), 0, buffer.limit());

            buffer.clear();
            int bytesRead = inputStream.read(buffer.array());

            if (bytesRead > 0) {
                String quote = new String(buffer.array(), 0, bytesRead, StandardCharsets.UTF_8);
                LOGGER.info("Сервер: " + quote);
                responseBuilder.append(quote);
            }

            return responseBuilder.toString();

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            disconnect();
        }

        return null;
    }
}
