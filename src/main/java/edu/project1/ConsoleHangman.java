package edu.project1;

import java.util.Scanner;

public class ConsoleHangman {
    private static final int MAX_ATTEMPTS = 5;
    private Session session;

    private final WordBase dictionary;

    public ConsoleHangman(WordBase dictionary) {
        this.dictionary = dictionary;
    }

    public void run() {
        String word = dictionary.randomWord();

        session = new Session(word, MAX_ATTEMPTS);

        System.out.println("Guess the word: " + hideWord(word));
        System.out.println("You have " + MAX_ATTEMPTS + " attempts.");
        System.out.println("If you want to leave - press 1");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Guess a letter:");
            String input = scanner.nextLine();
            if (input.length() != 1) {
                System.out.println("Invalid input. Please enter a single letter.");
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
        System.out.println();
        System.out.println("The word: " + new String(guessResult.state()));
        System.out.println("Mistake " + guessResult.attempt() + " out of " + guessResult.maxAttempts());
        System.out.println();
        System.out.println(guessResult.message());
        System.out.println();
    }

    private String hideWord(String word) {
        StringBuilder hiddenWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            hiddenWord.append("*");
        }
        return hiddenWord.toString();
    }
}
