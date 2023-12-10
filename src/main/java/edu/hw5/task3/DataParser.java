package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DataParser {

    public DataParser nextParser;

    public DataParser setNextParser(DataParser nextDataParser) {
        this.nextParser = nextDataParser;
        return nextDataParser;
    }

    public Optional<LocalDate> parseDate(String input) {

        Optional<LocalDate> date = parse(input);

        if (date.isPresent()) {

            return date;

        } else if (nextParser != null) {

            return nextParser.parseDate(input);

        } else {

            return Optional.empty();

        }
    }

    public abstract Optional<LocalDate> parse(String string);
}
