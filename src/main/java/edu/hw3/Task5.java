package edu.hw3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Task5 {

    private final static String ASC = "ASC";

    private final static String DESC = "DESC";


    public List<Person> parseContacts(String[] input, String order) {
        List<Person> list = new ArrayList<>();

        if (input == null) {
            return list;
        }

        for (int i = 0; i < input.length; i++) {
            String[] parts = input[i].split(" ");
            if (parts.length > 1) {
                list.add(new Person(parts[0], parts[1]));
            } else {
                list.add(new Person(parts[0], ""));
            }
        }

        if (!(order.equals(ASC) || order.equals(DESC))) {
            throw new RuntimeException("Order have to be equals ASC or DESC!");
        }

        if (order.equals(ASC)) {
            list.sort(Comparator.naturalOrder());
        } else if (order.equals(DESC)) {
            list.sort(Comparator.reverseOrder());
        }
        return list;
    }

    class Person implements Comparable<Person> {
        private String name;
        private String surname;

        Person(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        Person(String surname) {
            this.surname = surname;
        }

        @Override
        public int compareTo(Person person) {
            if (!(this.surname.equalsIgnoreCase(person.surname))) {
                return this.surname.compareTo(person.surname);
            }
            return this.name.compareTo(person.name);
        }

        @Override
        public String toString() {
            return name + " " + surname;
        }

    }
}
