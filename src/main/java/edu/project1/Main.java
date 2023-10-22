package edu.project1;


public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        String[] words = {"poker", "worker", "joker", "docker"};
        WordBase wordBase = new WordBase(words);
        ConsoleHangman hangman = new ConsoleHangman(wordBase);
        hangman.run();
    }
}
