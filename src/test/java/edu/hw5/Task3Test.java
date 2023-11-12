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
import java.util.Optional;

class Task3Test {

    private DataParser initializeParsers() {

        DataParser hyphenParser1 = new HyphenDateParser1();
        DataParser hyphenParser2 = new HyphenDateParser2();
        DataParser slashParser1 = new SlashDateParser1();
        DataParser slashParser2 = new SlashDateParser2();
        DataParser tomorrowParser = new TomorrowParser();
        DataParser todayParser = new TodayParser();
        DataParser yesterdayParser = new YesterdayParser();
        DataParser someDaysAgoParser = new SomeDaysAgoParser();

        hyphenParser1.setNextParser(hyphenParser2);
        hyphenParser2.setNextParser(slashParser1);
        slashParser1.setNextParser(slashParser2);
        slashParser2.setNextParser(tomorrowParser);
        tomorrowParser.setNextParser(todayParser);
        todayParser.setNextParser(yesterdayParser);
        yesterdayParser.setNextParser(someDaysAgoParser);

        return hyphenParser1;
    }

    @Test
    void parseHyphenDate1() {

        //Given
        DataParser hyphenParser1 = initializeParsers();

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
        DataParser hyphenParser1 = initializeParsers();

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
        DataParser hyphenParser1 = initializeParsers();

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
        DataParser hyphenParser1 = initializeParsers();

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
        DataParser hyphenParser1 = initializeParsers();

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
        DataParser hyphenParser1 = initializeParsers();

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
        DataParser hyphenParser1 = initializeParsers();

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
        DataParser hyphenParser1 = initializeParsers();

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
        DataParser hyphenParser1 = initializeParsers();

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
        DataParser hyphenParser1 = initializeParsers();

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
        DataParser hyphenParser1 = initializeParsers();

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
        DataParser hyphenParser1 = initializeParsers();

        String input = "    1/3/1976  ";

        Optional<LocalDate> expected = Optional.of(LocalDate.of(1976, 3, 1));

        //When
        Optional<LocalDate> result = hyphenParser1.parseDate(input);

        //Then
        Assertions.assertEquals(expected, result);

    }
}
