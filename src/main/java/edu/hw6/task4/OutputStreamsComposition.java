package edu.hw6.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class OutputStreamsComposition {

    private static final Logger LOGGER = LogManager.getLogger();

    public static Path printTextInFileUsingStreams() throws IOException {

        Path filePath = Files.createTempFile(null, null);

        try (
            OutputStream outputStream = Files.newOutputStream(filePath);

            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);

            PrintWriter printWriter = new PrintWriter(outputStreamWriter);

        ) {
            printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (Exception e) {
            LOGGER.error("Error appeared during creating outputStream", e);
        }
        return filePath;
    }
}
