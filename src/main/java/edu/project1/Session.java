package edu.project1;

public class Session {

    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

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

        for (int i = 0; i < answer.length(); i++) {
            if (Character.toLowerCase(guess) == Character.toLowerCase(answer.charAt(i))) {
                userAnswer[i] = answer.charAt(i);
                hit = true;
            }
        }
        if (!hit) {
            attempts++;
        }
        if (isWin()) {
            return new GuessResult.Win(userAnswer, attempts, maxAttempts, "Congratulations! You won!");
        } else if (attempts >= maxAttempts) {
            return new GuessResult.Defeat(answer.toCharArray(), attempts, maxAttempts, YOU_LOST + answer);
        } else if (hit) {
            return new GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts, "Hit!");
        } else {
            return new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts, "Missed, mistake "
                + attempts + " out of " + maxAttempts + ".");
        }
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





}
