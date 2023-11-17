package edu.hw6;

import edu.hw6.task3.AbstractFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertAll;

class Task3Test {

    private static File createFile(Path directory, String prefix, String suffix, byte[] magicBytes, long fileSize) throws IOException {

        Path filePath = Files.createTempFile(directory, prefix, suffix);
        File file = filePath.toFile();
        file.canRead();

        try (FileOutputStream fos = new FileOutputStream(String.valueOf(file))) {
            fos.write(magicBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            byte[] data = new byte[(int) fileSize];
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    @Test
    void AbstractFilterTestWithFiles() throws IOException {

        //Directory
        Path dir = Files.createTempDirectory("test-directory-for-filter");



        //Files
        byte[] magicBytes1 = { (byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G' };

        long fileSize1 = 100L;

        byte[] magicBytes2 = {(byte) 0xEF, (byte) 'T', (byte) 'X', (byte) 'T'};

        long fileSize2 = 200L;

        long fileSize3 = 10L;

        File file1 = createFile(dir, "temp-", ".png", magicBytes1, fileSize1);

        File file2 = createFile(dir, "temp2-", ".txt", magicBytes2, fileSize2);

        File file3 = createFile(dir, "temp3-", ".png", magicBytes1, fileSize2);

        File file4 = createFile(dir, "temp4%-", ".txt", magicBytes2, fileSize3);

        file4.canWrite();


        //When
        AbstractFilter filter1 = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.READABLE)
            .and(AbstractFilter.largerThan(10L))
            .and(AbstractFilter.magicNumber(0x89, 'P', 'N', 'G'))
            .and(AbstractFilter.globMatches("*.png"))
            .and(AbstractFilter.regexMatches(".*temp.*"));

        AbstractFilter filter2 = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.WRITEABLE)
            .and(AbstractFilter.largerThan(5L))
            .and(AbstractFilter.magicNumber(0xEF, 'T', 'X', 'T'))
            .and(AbstractFilter.globMatches("*.txt"))
            .and(AbstractFilter.regexMatches(".*%.*"));


        //Then
        List<File> filteredFiles1 = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter1)) {
            entries.forEach(entry -> filteredFiles1.add(entry.toFile()));
        }

        List<File> filteredFiles2 = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter2)) {
            entries.forEach(entry -> filteredFiles2.add(entry.toFile()));
        }

        assertAll(
            () -> Assertions.assertEquals(2, filteredFiles1.size()),
            () -> Assertions.assertEquals(file1, filteredFiles1.get(0)),
            () -> Assertions.assertTrue(filteredFiles1.contains(file3)),
            () -> Assertions.assertEquals(file4, filteredFiles2.get(0))
        );

        Files.walk(dir)
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
    }
}
