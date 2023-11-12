package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class YesterdayParser extends DataParser {

    @Override
    public Optional<LocalDate> parse(String input) {

        input = input.trim();

        String pattern = "yesterday";

        try {
            LocalDate date = LocalDate.now();
            date = date.minusDays(1);

            if (input.equalsIgnoreCase(pattern)) {
                return Optional.of(date);
            }
        } catch (Exception e) {

        }
        return Optional.empty();

    }
}
