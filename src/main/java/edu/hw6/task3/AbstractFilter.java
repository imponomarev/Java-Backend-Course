package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    Logger LOGGER = LogManager.getLogger();

    String SIZE_ERROR = "Error during check size of file";

    boolean accept(Path entry) throws IOException;

    default AbstractFilter and(AbstractFilter other) {
        return entry -> this.accept(entry) && other.accept(entry);
    }

    AbstractFilter REGULAR_FILE = Files::isRegularFile;

    AbstractFilter DIRECTORY = Files::isDirectory;

    AbstractFilter READABLE = Files::isReadable;

    AbstractFilter WRITEABLE = Files::isWritable;

    static AbstractFilter largerThan(long size) {
        return entry -> {
            try {
                return Files.size(entry) > size;
            } catch (IOException e) {
                LOGGER.error(SIZE_ERROR);
                return false;
            }
        };
    }

    static AbstractFilter smallerThan(long size) {
        return entry -> {
            try {
                return Files.size(entry) < size;
            } catch (IOException e) {
                LOGGER.error(SIZE_ERROR);
                return false;
            }
        };
    }

     static AbstractFilter globMatches(String glob) {
        return entry -> {
            Path fileName = entry.getFileName();
            FileSystem fileSystem = FileSystems.getDefault();
            return fileName != null && fileSystem.getPathMatcher("glob:" + glob).matches(fileName);
        };
    }

    static AbstractFilter regexMatches(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return entry -> pattern.matcher(entry.getFileName().toString()).matches();
    }

    static AbstractFilter magicNumber(int magicByte1, char... values) {

        byte[] magicBytes = new byte[values.length + 1];
        magicBytes[0] = (byte) magicByte1;

        for (int i = 0; i < values.length; i++) {
            magicBytes[i + 1] = (byte) values[i];
        }

        return entry -> {
            try {
                byte[] fileBytes = Files.readAllBytes(entry);
                if (fileBytes.length < magicBytes.length) {
                    return false;
                }
                for (int i = 0; i < magicBytes.length; i++) {
                    if (fileBytes[i] != magicBytes[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                LOGGER.error("Error during check magic numbers in file");
                return false;
            }
        };
    }
}
