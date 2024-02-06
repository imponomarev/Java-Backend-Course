package edu.hw9;

import edu.hw9.task2.FileSearch;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {

    private static final String ROOT_PATH = "src/main/resources";
    private static final String TEST_DIR_PREFIX = "testDir";
    private static final String TEST_FILE_PREFIX = "testFile";
    private static final String TEST_FILE_EXTENSION = ".txt";
    private static final int NUM_DIRS_WITH_FILES = 2;
    private static final int NUM_FILES_PER_DIR = 1001;
    private static final long FILE_SIZE_THRESHOLD = 10;

    @BeforeEach
    void setUp() throws IOException {
        createTestDirectoriesWithFiles();
    }

    @AfterEach
    void tearDown() throws IOException {
        deleteTestDirectoriesWithFiles();
    }

    @Test
    void testFindDirectoriesWithMoreThan1000Files() {

        File root = new File(ROOT_PATH);

        List<File> directories = FileSearch.findDirectoriesWithMoreThan1000Files(root);

        assertEquals(NUM_DIRS_WITH_FILES, directories.size());
    }

    @Test
    void testFindFilesMatchingPredicate() {

        File root = new File(ROOT_PATH);

        List<File> files = FileSearch.findFilesMatchingPredicate(root, TEST_FILE_EXTENSION, FILE_SIZE_THRESHOLD);

        assertEquals(NUM_DIRS_WITH_FILES * NUM_FILES_PER_DIR, files.size());
    }

    private void createTestDirectoriesWithFiles() throws IOException {

        for (int i = 1; i <= NUM_DIRS_WITH_FILES; i++) {
            String dirPath = ROOT_PATH + File.separator + TEST_DIR_PREFIX + i;
            Files.createDirectory(Path.of(dirPath));

            for (int j = 1; j <= NUM_FILES_PER_DIR; j++) {
                String filePath = dirPath + File.separator + TEST_FILE_PREFIX + j + TEST_FILE_EXTENSION;
                FileWriter writer = new FileWriter(filePath);
                writer.write("Test file content");
                writer.close();
            }
        }
    }


    private void deleteTestDirectoriesWithFiles() throws IOException {
        for (int i = 1; i <= NUM_DIRS_WITH_FILES; i++) {
            String dirPath = ROOT_PATH + File.separator + TEST_DIR_PREFIX + i;
            Files.walk(Path.of(dirPath))
                .sorted((a, b) -> -a.compareTo(b))
                .map(Path::toFile)
                .forEach(File::delete);
        }
    }
}
