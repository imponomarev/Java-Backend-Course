package edu.hw5;

import edu.hw5.task3.DataParser;
import edu.hw5.task3.HyphenDateParser1;
import edu.hw5.task3.HyphenDateParser2;
import edu.hw5.task3.SlashDateParser1;
import edu.hw5.task3.SlashDateParser2;
import edu.hw5.task3.SomeDaysAgoParser;
import edu.hw5.task3.TodayParser;
import edu.hw5.task3.TomorrowParser;
import edu.hw5.task3.YesterdayParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Task3Test {

    List<DataParser> parsers = initializeParsersList();

    DataParser hyphenParser1 = addParsersToList(parsers);

    private static List<DataParser> initializeParsersList() {

        List<DataParser> parsers = new ArrayList<>();

        DataParser hyphenParser1 = new HyphenDateParser1();
        DataParser hyphenParser2 = new HyphenDateParser2();
        DataParser slashParser1 = new SlashDateParser1();
        DataParser slashParser2 = new SlashDateParser2();
        DataParser tomorrowParser = new TomorrowParser();
        DataParser todayParser = new TodayParser();
        DataParser yesterdayParser = new YesterdayParser();
        DataParser someDaysAgoParser = new SomeDaysAgoParser();

        parsers.add(hyphenParser1);
        parsers.add(hyphenParser2);
        parsers.add(slashParser1);
        parsers.add(slashParser2);
        parsers.add(tomorrowParser);
        parsers.add(todayParser);
        parsers.add(yesterdayParser);
        parsers.add(someDaysAgoParser);

        return parsers;
    }

    public static DataParser addParsersToList(List<DataParser> parsers) {

        if (parsers.isEmpty()) {
            return null;
        }

        DataParser previousParser = null;
        for (DataParser parser : parsers) {
            if (previousParser != null) {
                previousParser.setNextParser(parser);
            }
            previousParser = parser;
        }

        return parsers.get(0);
    }

    @Test
    void parseHyphenDate1() {

        //Given
        String input = "2020-10-10";

        Optional<LocalDate> expected = Optional.of(LocalDate.of(2020, 10, 10));

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void parseHyphenDate2() {

        //Given
        String input = "2020-12-2";

        Optional<LocalDate> expected = Optional.of(LocalDate.of(2020, 12, 2));

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void parseSlashDate1() {

        //Given
        String input = "1/3/1976";

        Optional<LocalDate> expected = Optional.of(LocalDate.of(1976, 3, 1));

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void parseSlashDate2() {

        //Given
        String input = "1/3/20";

        Optional<LocalDate> expected = Optional.of(LocalDate.of(2020, 3, 1));

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void parseTomorrowDate() {

        //Given
        String input = "tomorrow";

        Optional<LocalDate> expected = Optional.of(LocalDate.now().plusDays(1));

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void parseTodayDate() {

        //Given
        String input = "today";

        Optional<LocalDate> expected = Optional.of(LocalDate.now());

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void parseYesterdayDate() {

        //Given
        String input = "yesterday";

        Optional<LocalDate> expected = Optional.of(LocalDate.now().minusDays(1));

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void parseSomeDaysAgoDate1() {

        //Given
        String input = "1 day ago";

        Optional<LocalDate> expected = Optional.of(LocalDate.now().minusDays(1));

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void parseSomeDaysAgoDate2() {

        //Given
        String input = "2234 days ago";

        Optional<LocalDate> expected = Optional.of(LocalDate.now().minusDays(2234));

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void parseWithUnsuccessfulResult() {

        //Given
        String input = "11.22.33";

        Optional<LocalDate> expected = Optional.empty();

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void parseWithEmptyInput() {

        //Given
        String input = "";

        Optional<LocalDate> expected = Optional.empty();

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void parseDateWithSpaceInBeginning() {

        //Given
        String input = "    1/3/1976  ";

        Optional<LocalDate> expected = Optional.of(LocalDate.of(1976, 3, 1));

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }
}
