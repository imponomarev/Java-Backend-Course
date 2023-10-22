package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConsoleHangman {

    private Logger logger = LogManager.getLogger();
    private static final int MAX_ATTEMPTS = 5;
    private Session session;

    private final WordBase dictionary;

    public ConsoleHangman(WordBase dictionary) {
        this.dictionary = dictionary;
    }

    public void run() {
        String word = dictionary.randomWord();

        session = new Session(word, MAX_ATTEMPTS);

        logger.info("Guess the word: " + hideWord(word));
        logger.info("You have " + MAX_ATTEMPTS + " attempts.");
        logger.info("If you want to leave - press 1");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            logger.info("Guess a letter:");
            String input = scanner.nextLine();
            if (input.length() != 1) {
                logger.info("Invalid input. Please enter a single letter.");
                continue;
            }
            char guess = input.charAt(0);
            GuessResult result = tryGuess(session, guess);
            printState(result);
            if (result instanceof GuessResult.Defeat || result instanceof GuessResult.Win) {
                break;
            }
        }
    }

    private GuessResult tryGuess(Session session, char guess) {
        if (guess == '1') {
            return session.giveUp();
        }
        return session.guess(guess);
    }

    private void printState(GuessResult guessResult) {
        logger.info(" ");
        logger.info("The word: " + new String(guessResult.state()));
        logger.info("Mistake " + guessResult.attempt() + " out of " + guessResult.maxAttempts());
        logger.info(" ");
        logger.info(guessResult.message());
        logger.info(" ");
    }

    private String hideWord(String word) {
        StringBuilder hiddenWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.append("*");
        }
        return hiddenWord.toString();
    }
}
