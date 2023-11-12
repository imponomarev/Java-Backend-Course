package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HyphenDateParser1 extends DataParser {

    @Override
    public Optional<LocalDate> parse(String input) {

        input = input.trim();

        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        try {
            LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (matcher.matches()) {
                return Optional.of(date);
            }
        } catch (Exception e) {

        }
        return Optional.empty();
    }
}
