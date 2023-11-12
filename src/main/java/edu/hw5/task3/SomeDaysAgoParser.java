package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SomeDaysAgoParser extends DataParser {

    @Override
    public Optional<LocalDate> parse(String str) {

        String input = str;

        input = input.trim();

        String regex = "^\\d+\\s+day(s)?\\s+ago$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);



        try {
            int countOfDays = Integer.parseInt(input.split("\\s+")[0]);

            LocalDate date = LocalDate.now();
            date = date.minusDays(countOfDays);

            if (matcher.matches()) {
                return Optional.of(date);
            }
        } catch (Exception e) {

        }
        return Optional.empty();

    }
}
