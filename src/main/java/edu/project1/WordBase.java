package edu.project1;

import java.util.Random;

public class WordBase implements Dictionary {
    private final String[] words;
    private final Random random;

    public WordBase(String[] words) {
        this.words = words;
        this.random = new Random();

    }

    @Override
    public String randomWord() {
        int randomIndex = random.nextInt(words.length);
        if (words[randomIndex].length() == 0) {
            throw new IlligalWordException();
        }
        return words[randomIndex];
    }
}
