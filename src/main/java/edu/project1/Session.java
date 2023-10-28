package edu.project1;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    List<Character> falseLiteralsList = new ArrayList<>();

    private static final String YOU_LOST = "You lost! The word was: ";

    public Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.userAnswer = new char[answer.length()];
        this.maxAttempts = maxAttempts;
        this.attempts = 0;

        if (answer.length() == 0) {
            throw new IlligalWordException();
        }

    }

    GuessResult guess(char guess) {

        boolean hit = false;
        boolean alreadyGuessed = false;


        for (int i = 0; i < answer.length(); i++) {
            if (Character.toLowerCase(guess) == Character.toLowerCase(answer.charAt(i))) {
                userAnswer[i] = answer.charAt(i);
                hit = true;

            }
        }
        if (!hit) {
            if (!falseLiteralsList.contains(Character.toLowerCase(guess))) {
                falseLiteralsList.add(Character.toLowerCase(guess));
                attempts++;
            } else {
                alreadyGuessed = true;
            }
        }

        GuessResult result;

        if (isWin()) {
            result = new GuessResult.Win(userAnswer, attempts, maxAttempts, "Congratulations! You won!");
        } else if (attempts >= maxAttempts) {
            result =  new GuessResult.Defeat(answer.toCharArray(), attempts, maxAttempts, YOU_LOST + answer);
        } else if (hit) {
            result =  new GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts, "Hit!");
        } else if (alreadyGuessed) {
            result = new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts, "You already guessed that letter.");
        } else {
            result =  new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts, "Missed, mistake "
                + attempts + " out of " + maxAttempts + ".");
        }
        return result;
    }

    private boolean isWin() {
        for (char c : userAnswer) {
            if (c == 0) {
                return false;
            }
        }
        return true;
    }

    GuessResult giveUp() {
        return new GuessResult.Defeat(answer.toCharArray(), attempts, maxAttempts, YOU_LOST + answer);
    }

    private GuessResult win(){
        return new GuessResult.Win(userAnswer, attempts, maxAttempts, "Congratulations! You won!");
    }






}
