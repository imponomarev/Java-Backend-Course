package edu.hw8;

import edu.hw8.task3.EncodingMD5;
import edu.hw8.task3.PasswordCracker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


class Task3Test {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final double NANO = 1_000_000_000.0;

    private static Stream<Arguments> providePasswords() {
        return Stream.of(
            Arguments.of("abc"),
            Arguments.of("rip"),
            Arguments.of("gta"),
            Arguments.of("a1bb")
        );
    }

    @ParameterizedTest
    @MethodSource("providePasswords")
    void testPasswordHackOneThread(String password) {

        String hashed_password = EncodingMD5.getMd5(password);

        PasswordCracker passwordCracker = new PasswordCracker();

        var startTime = System.nanoTime();

        String hacked_password = passwordCracker.hackOneThread(hashed_password);

        var endTime = System.nanoTime();

        assertEquals(
            password,
            hacked_password
        );

        LOGGER.info("hack time: {}.", (endTime - startTime) / NANO);

    }

    @ParameterizedTest
    @MethodSource("providePasswords")
    void testPasswordHackMultiThreaded() {

        String password = "pwd";

        String hashed_password = EncodingMD5.getMd5(password);

        PasswordCracker passwordCracker = new PasswordCracker();

        var startTime = System.nanoTime();

        String hacked_password = passwordCracker.hackMultiThreaded(hashed_password);

        var endTime = System.nanoTime();

        assertEquals(
            password,
            hacked_password
        );

        LOGGER.info("hack time: {}.", (endTime - startTime) / NANO);
    }




}
