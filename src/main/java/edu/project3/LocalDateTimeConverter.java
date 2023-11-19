package edu.project3;

import com.beust.jcommander.IStringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements IStringConverter<LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(String value) {
        return LocalDate.parse(value, formatter);
    }
}
