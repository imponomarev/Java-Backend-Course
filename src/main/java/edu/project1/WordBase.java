package edu.project1;

public class WordBase implements Dictionary {
    private final String[] words;

    public WordBase(String[] words) {
        this.words = words;
    }

    @Override
    public String randomWord() {
        int randomIndex = (int) (Math.random() * words.length);
        if (words[randomIndex].length() == 0) {
            throw new IlligalWordException();
        }
        return words[randomIndex];
    }
}
