package edu.hw6;

import edu.hw6.task2.Task2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class Task2Test {

    private Task2 task2;

    private Path tempDir;

    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeEach
    void setUp() throws IOException {

        task2 = new Task2();

    }

    @Test
    void firstCopyTest() throws IOException {

        try{
            tempDir = Files.createTempDirectory("test-directory");
        } catch (IOException e) {
            LOGGER.error("Error during create temporary directory", e);
        }

        try {

            Path sourcePath = tempDir.resolve("Tinkoff Bank Biggest Secret.txt");

            Path expected = tempDir.resolve("Tinkoff Bank Biggest Secret — копия.txt");

            Path expectedSecondCopy = tempDir.resolve("Tinkoff Bank Biggest Secret — копия (2).txt");

            Path expectedCopyOfCopy = tempDir.resolve("Tinkoff Bank Biggest Secret — копия (2) — копия.txt");

            Path nonExistentFile = tempDir.resolve("NonexistentFile.txt");

            Files.createFile(sourcePath);

            Path clonedPath = task2.cloneFile(sourcePath);

            Path clonedPath2 = task2.cloneFile(sourcePath);

            Path clonedPath3 = task2.cloneFile(clonedPath2);

            assertAll(
                () -> Assertions.assertTrue(Files.exists(sourcePath)),
                () -> Assertions.assertTrue(Files.exists(clonedPath)),
                () -> Assertions.assertEquals(expected, clonedPath),
                () -> Assertions.assertEquals(expectedSecondCopy, clonedPath2),
                () -> Assertions.assertEquals(expectedCopyOfCopy, clonedPath3),
                () -> assertThatThrownBy(() -> task2.cloneFile(nonExistentFile))
                    .isInstanceOf(IOException.class)
            );

        } catch (IOException e) {
            LOGGER.error("Error during test", e);

        } finally {
            Files.walk(tempDir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
        }
    }

}
