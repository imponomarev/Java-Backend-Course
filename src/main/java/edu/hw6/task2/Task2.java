package edu.hw6.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Task2 {

    private static final Logger LOGGER = LogManager.getLogger();

    public Path cloneFile(Path sourcePath) throws IOException {

        String fileName = sourcePath.getFileName().toString();

        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));

        String extension = fileName.substring(fileName.lastIndexOf('.'));

        String newFileName = baseName;

        int copyNumber = 1;

        while (Files.exists(sourcePath.resolveSibling(newFileName + extension))) {

            newFileName = baseName + " — копия";

            if (copyNumber > 1) {
                newFileName += " (" + copyNumber + ")";
            }
            copyNumber++;
        }

        Path destinationPath = sourcePath.resolveSibling(newFileName + extension);

        try {

            Files.copy(sourcePath, destinationPath, StandardCopyOption.COPY_ATTRIBUTES);

            LOGGER.info("Файл склонирован с новым именем: " + destinationPath);

        } catch (Exception e) {

            LOGGER.error("Возникла ошибка при клонировании файла: " + e.getMessage());

            throw new IOException(e);
        }

        return destinationPath;
    }
}
