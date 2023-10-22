package edu.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProjectTest {
    private Session session;

    @BeforeEach
    void setUp() {
        session = new Session("hello", 5);
    }

    @Test
    @DisplayName("game doesn't start if word's length is invalid test")
    void hangmanTest1() {
        Assertions.assertThrows(IlligalWordException.class, () -> { new Session("", 5);});
    }

    @Test
    @DisplayName("game always returns defeat after exceeding max attempts")
    void hangmanTest2() {
        for (int i = 0; i < 5; i++) {
            session.guess('a');
        }
        GuessResult result = session.guess('a');
        Assertions.assertTrue(result instanceof GuessResult.Defeat);
    }

    @Test
    @DisplayName("game updates state correctly on successful guess")
    void hangmanTest3() {
        GuessResult result = session.guess('h');
        Assertions.assertTrue(result instanceof GuessResult.SuccessfulGuess);
        Assertions.assertArrayEquals(new char[]{'h', 0, 0, 0, 0}, result.state());
    }

    @Test
    @DisplayName("game updates state correctly on failed guess")
    void hangmanTest4() {
        GuessResult result = session.guess('a');
        Assertions.assertTrue(result instanceof GuessResult.FailedGuess);
        Assertions.assertArrayEquals(new char[]{0, 0, 0, 0, 0}, result.state());
    }
}
