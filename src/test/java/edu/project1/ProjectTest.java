package edu.project1;


import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ProjectTest {

    private Session session;

    private ConsoleHangman consoleHangman;

    @BeforeEach
    void setUp() {
        session = new Session("hello", 5);

        String[] words = {" "};
        WordBase wordBase = new WordBase(words);

        Logger logger = getLogger();

        consoleHangman = new ConsoleHangman(wordBase, logger);
    }

    @Test
    @DisplayName("game doesn't start if word's length is invalid test")
    void hangmanTest1() {
        assertThrows(IlligalWordException.class, () -> {
            new Session("", 5);
        });
    }

    @Test
    @DisplayName("game always returns defeat after exceeding max attempts")
    void hangmanTest2() {
        session.guess('z');
        session.guess('x');
        session.guess('t');
        session.guess('p');
        GuessResult result = session.guess('a');
        assertTrue(result instanceof GuessResult.Defeat);
    }

    @Test
    @DisplayName("game updates state correctly on successful guess")
    void hangmanTest3() {
        GuessResult result = session.guess('h');
        assertTrue(result instanceof GuessResult.SuccessfulGuess);
        Assertions.assertArrayEquals(new char[] {'h', 0, 0, 0, 0}, result.state());
    }

    @Test
    @DisplayName("game updates state correctly on failed guess")
    void hangmanTest4() {
        GuessResult result = session.guess('a');
        assertTrue(result instanceof GuessResult.FailedGuess);
        Assertions.assertArrayEquals(new char[] {0, 0, 0, 0, 0}, result.state());
    }


    @Test
    @DisplayName("сhecking the masking of words")
    void hangmanTest5() {
        String hiddenWord = consoleHangman.hideWord("test");
        assertEquals("****", hiddenWord);
    }

    @Test
    @DisplayName("test print state when game in progress then correct output")
    void hangmanTest6() {
        GuessResult mockGuessResult = new GuessResult.SuccessfulGuess("test".toCharArray(), 1, 5, "Hit!");
        assertDoesNotThrow(() -> consoleHangman.printState(mockGuessResult));
    }

    @Test
    @DisplayName("give up check test")
    void hangmanTest7() {
        Session session = new Session("test", 5);
        GuessResult result = consoleHangman.tryGuess(session, '1');
        assertTrue(result instanceof GuessResult.Defeat);
    }
}
