package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class TomorrowParser extends DataParser {

    @Override
    public Optional<LocalDate> parse(String input) {

        input = input.trim();

        String pattern = "tomorrow";

        try {
            LocalDate date = LocalDate.now();
            date = date.plusDays(1);

            if (input.equalsIgnoreCase(pattern)) {
                return Optional.of(date);
            }
        } catch (Exception e) {

        }
        return Optional.empty();

    }
}
