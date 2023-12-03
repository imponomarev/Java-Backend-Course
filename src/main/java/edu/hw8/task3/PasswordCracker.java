package edu.hw8.task3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PasswordCracker {

    private final PasswordGenerator passwordGenerator = new PasswordGenerator();

    private String foundPassword;

    private boolean isFound;

    public String hackOneThread(String hashedPassword) {

        String password = passwordGenerator.generateNext();

        while (!EncodingMD5.getMd5(password)
            .equals(hashedPassword)) {
            password = passwordGenerator.generateNext();
        }

        return password;
    }

    public String hackMultiThreaded(String hashedPassword) {


        try (ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
        )) {

            while (!isFound) {

                executor.submit(() -> {

                    String password = passwordGenerator.generateNext();

                    if (EncodingMD5.getMd5(password)
                        .equals(hashedPassword)) {
                        foundPassword = password;
                        isFound = true;
                    }
                });
            }
        }

        return foundPassword;
    }

}
