package edu.hw1;

public class Task1 {
    public int minutesToSeconds (String time){
        String[] splitedTime = time.split(":");
        int minutes = Integer.parseInt(splitedTime[0]);
        int seconds = Integer.parseInt(splitedTime[1]);
        int result = 0;

        if (seconds >= 60){
            return -1;
        }
        else{
            result = minutes*60 + seconds;
        }
        return result;
    }
}
