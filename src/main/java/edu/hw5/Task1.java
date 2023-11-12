package edu.hw5;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class Task1 {

    private static final int SIXTY = 60;

    private static final int TWENTY_FOUR = 24;

    public String averagePerSession(String[] input) throws ParseException {

        List<Date> dates = new ArrayList<>();
        List<Long> sessions = new ArrayList<>();

        if (input.length == 0) {
            return null;
        }

        try {
            for (int i = 0; i < input.length; i++) {
                dates.add(new SimpleDateFormat("yyyy-MM-dd, HH:mm").parse(input[i].split(" - ")[0]));
                dates.add(new SimpleDateFormat("yyyy-MM-dd, HH:mm").parse(input[i].split(" - ")[1]));
            }
        } catch (ParseException e) {
            throw new ParseException("Failed to process session!", 0);
        }

        for (int i = 0; i < dates.size(); i += 2) {
            sessions.add(Duration.between(dates.get(i).toInstant(), dates.get(i + 1).toInstant()).toSeconds());
        }

        long average = computeAverage(sessions);

        return convertToOutput(average);
    }

    private long computeAverage(List<Long> sessions) {

        long sum =  sessions.stream().mapToLong(Long::longValue).sum();

        return sum / sessions.size();
    }

    public static String convertToOutput(long seconds) {

        long days = seconds / (SIXTY * SIXTY * TWENTY_FOUR);
        long hours = (seconds % (SIXTY * SIXTY * TWENTY_FOUR)) / (SIXTY * SIXTY);
        long minutes = (seconds % (SIXTY * SIXTY)) / SIXTY;

        StringBuilder formattedTime = new StringBuilder();

        if (days > 0) {
            formattedTime.append(days).append("д ");
        }
        if (hours > 0) {
            formattedTime.append(hours).append("ч ");
        }
        if (minutes > 0 || (days == 0 && hours == 0)) {
            formattedTime.append(minutes).append("м");
        }

        return formattedTime.toString();
    }
}
