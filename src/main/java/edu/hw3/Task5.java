package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task5 {

    class Person implements Comparable<Person> {
        private String name;
        private String surname;

        public Person(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public Person(String surname) {
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

    public List<Person> parseContacts(String[] input, String order) {
        List<Person> list = new ArrayList<>();

        if (input == null) {
            return list;
        }

        for (int i = 0; i < input.length; i++) {
            list.add(new Person(input[i].split(" ")[0], input[i].split(" ")[1]));
        }

        if (order.equals("ASC")) {
            list.sort(Person::compareTo);
        } else if (order.equals("DESC")) {
            list.sort(Person::compareTo);
            list = list.reversed();
        }

        return list;
    }
}
