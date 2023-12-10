package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class TodayParser extends DataParser {

    @Override
    public Optional<LocalDate> parse(String str) {

        String input = str;

        input = input.trim();

        String pattern = "today";

        try {
            LocalDate date = LocalDate.now();

            if (input.equalsIgnoreCase(pattern)) {
                return Optional.of(date);
            }
        } catch (Exception e) {

        }
        return Optional.empty();

    }
}
