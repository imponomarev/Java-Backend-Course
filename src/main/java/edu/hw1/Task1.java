package edu.hw1;

public class Task1 {

    static final int SECONDS_IN_MINUTE = 60;

    public int minutesToSeconds(String time) {
        String[] splitedTime = time.split(":");
        int minutes = Integer.parseInt(splitedTime[0]);
        int seconds = Integer.parseInt(splitedTime[1]);
        int result = 0;

        if (seconds >= SECONDS_IN_MINUTE) {
            return -1;
        }
        else {
            result = minutes * SECONDS_IN_MINUTE + seconds;
        }
        return result;
    }
}
