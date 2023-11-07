package edu.project1;

import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.getLogger;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Logger logger = getLogger();
        String[] words = {"poker", "worker", "joker", "docker"};
        WordBase wordBase = new WordBase(words);
        ConsoleHangman hangman = new ConsoleHangman(wordBase, logger);
        hangman.run();
    }
}
