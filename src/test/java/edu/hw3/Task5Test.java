package edu.hw3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class Task5Test {

    private Task5 task5;

    @BeforeEach
    public void setup() {
        task5 = new Task5();
    }

    @Test
    @DisplayName("parse contacts function test: ascending order")
    void testParseContacts1() {

        //Given
        String[] input = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String order = "ASC";
        List<Task5.Person> expected = Arrays.asList(
            task5.new Person("Thomas", "Aquinas"),
            task5.new Person("Rene", "Descartes"),
            task5.new Person("David", "Hume"),
            task5.new Person("John", "Locke")
            );

        //When
        List<Task5.Person> result = task5.parseContacts(input, order);

        //Then
        Assertions.assertEquals(expected.toString(), result.toString());
    }

    @Test
    @DisplayName("parse contacts function test: descending order")
    void testParseContacts2() {

        //Given
        String[] input = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String order = "DESC";
        List<Task5.Person> expected = Arrays.asList(
            task5.new Person("John", "Locke"),
            task5.new Person("David", "Hume"),
            task5.new Person("Rene", "Descartes"),
            task5.new Person("Thomas", "Aquinas")
        );

        //When
        List<Task5.Person> result = task5.parseContacts(input, order);

        //Then
        Assertions.assertEquals(expected.toString(), result.toString());
    }

    @Test
    @DisplayName("parse contacts function test: ascending order by name")
    void testParseContacts3() {

        //Given
        String[] input = {"ABB ABDULLA", "AAA ABDULLA", "BBB ABDULLA", "AAB ABDULLA"};
        String order = "ASC";
        List<Task5.Person> expected = Arrays.asList(
            task5.new Person("AAA", "ABDULLA"),
            task5.new Person("AAB", "ABDULLA"),
            task5.new Person("ABB", "ABDULLA"),
            task5.new Person("BBB", "ABDULLA")
        );

        //When
        List<Task5.Person> result = task5.parseContacts(input, order);

        //Then
        Assertions.assertEquals(expected.toString(), result.toString());
    }

    @Test
    @DisplayName("parse contacts function test: descending order by name")
    void testParseContacts4() {

        //Given
        String[] input = {"ABB ABDULLA", "AAA ABDULLA", "BBB ABDULLA", "AAB ABDULLA"};
        String order = "DESC";
        List<Task5.Person> expected = Arrays.asList(
            task5.new Person("BBB", "ABDULLA"),
            task5.new Person("ABB", "ABDULLA"),
            task5.new Person("AAB", "ABDULLA"),
            task5.new Person("AAA", "ABDULLA")
        );

        //When
        List<Task5.Person> result = task5.parseContacts(input, order);

        //Then
        Assertions.assertEquals(expected.toString(), result.toString());
    }

    @Test
    @DisplayName("parse contacts function test: null case")
    void testParseContacts5() {

        //Given
        String[] input = null;
        String order = "DESC";
        List<Task5.Person> expected = new ArrayList<>();

        //When
        List<Task5.Person> result = task5.parseContacts(input, order);

        //Then
        Assertions.assertEquals(expected.toString(), result.toString());
    }

    @Test
    @DisplayName("parse contacts function test: empty array case")
    void testParseContacts6() {

        //Given
        String[] input = {};
        String order = "DESC";
        List<Task5.Person> expected = new ArrayList<>();

        //When
        List<Task5.Person> result = task5.parseContacts(input, order);

        //Then
        Assertions.assertEquals(expected.toString(), result.toString());
    }
}
