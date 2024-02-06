package edu.hw6;

import edu.hw6.task4.OutputStreamsComposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

class Task4Test {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void outputStreamsCompositionTest() throws IOException {

        //Given
        Path expected = Files.createTempFile("temp", ".txt");

        try (
            PrintWriter printWriter = new PrintWriter(expected.toFile());

        ) {
            printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");
        }

        //When
        Path result = OutputStreamsComposition.printTextInFileUsingStreams();

        //Then
        Assertions.assertTrue(areFilesEqual(expected, result));
    }

    private static boolean areFilesEqual(Path file1Path, Path file2Path) {

        try {
            byte[] file1Content = Files.readAllBytes(file1Path);
            byte[] file2Content = Files.readAllBytes(file2Path);

            return java.util.Arrays.equals(file1Content, file2Content);

        } catch (IOException e) {
            LOGGER.error("The error appeared during the comparison process", e);
        }

        return false;
    }
}
