package edu.hw5;

import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Task2 {

    public String findFridayThirteen(int year) {

        List<LocalDate> fridays = new ArrayList<>();

        LocalDate date = LocalDate.of(year, 1, 13);

        while (date.getYear() ==  year) {

            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(date);
            }
            date = date.plusMonths(1);
        }

        return fridays.toString();
    }

    public String findNearFridayThirteen(String input) {

        LocalDate date = LocalDate.parse(input);

        while (true) {
            LocalDate nextFriday = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            if (nextFriday.getDayOfMonth() == 13) {
                return nextFriday.toString();
            } else {
                date = nextFriday;
            }
        }
    }
}
