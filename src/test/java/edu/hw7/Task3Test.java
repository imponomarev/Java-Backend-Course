package edu.hw7;

import edu.hw7.task3.CachingPersonDatabase;
import edu.hw7.task3.CachingPersonDatabaseWithRwLock;
import edu.hw7.task3.Person;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;


class Task3Test {

    private CachingPersonDatabase database = new CachingPersonDatabase();

    private CachingPersonDatabaseWithRwLock databaseWithRwLock = new CachingPersonDatabaseWithRwLock();

    @Test
    void synchronizedSearch() {

        Person person1 = new Person(1, "Ivan", "Pushkina 2", "124465656");
        Person person2 = new Person(2, "Petr", "Lenina 23", "2132312");
        Person person3 = new Person(3, "Bob", "Vladimirskaya 40", "434554657");

        database.add(person1);
        database.add(person2);
        database.add(person3);

        assertEquals(person2, database.findByName(person2.name()).get(0));
        assertEquals(person1, database.findByAddress(person1.address()).get(0));
        assertEquals(person3, database.findByPhone(person3.phoneNumber()).get(0));

        database.delete(person2.id());

        assertEquals(new ArrayList<>(), database.findByName(person2.name()));
        assertEquals(new ArrayList<>(), database.findByAddress(person2.address()));
        assertEquals(new ArrayList<>(), database.findByPhone(person2.phoneNumber()));
    }

    @Test
    void syncParallelSearch() throws InterruptedException {
        List<Person> personList = List.of(
            new Person(1, "Ivan", "Pushkina 2", "124465656"),
            new Person(2, "Petr", "Lenina 23", "2132312"),
            new Person(3, "Bob", "Vladimirskaya 40", "434554657"),
            new Person(4, "Jack", "adsakjsd", "65685675687"),
            new Person(5, "Chuck", "Grow street", "86886868"),
            new Person(6, "Rick", "ghghghgdf", "85665675876"),
            new Person(7, "Sasha", "khkhkhkh", "8998598569"),
            new Person(8, "Vitya", "lksdlkfsmd", "12345677")
        );

        List<Thread> adders = new ArrayList<>();
        List<Thread> removers = new ArrayList<>();
        List<Thread> readers = new ArrayList<>();
        AtomicInteger failCount = new AtomicInteger(0);

        personList.forEach(p -> {

            adders.add(new Thread(() -> database.add(p)));
            removers.add(new Thread(() -> database.delete(p.id())));

            readers.add(new Thread(() -> {

                synchronized (database) {

                    if (database.findByName(p.name()) != null) {

                        if (!(database.findByName(p.name()).equals(database.findByAddress(p.address()))
                            && database.findByAddress(p.address()).equals(database.findByPhone(p.phoneNumber())))) {

                            failCount.getAndIncrement();

                        }
                    }
                }
            }));
        });

        adders.forEach(Thread::start);
        removers.forEach(Thread::start);
        readers.forEach(Thread::start);

        for (Thread thread : adders) {
            thread.join();
        }
        for (Thread thread : removers) {
            thread.join();
        }
        for (Thread thread : readers) {
            thread.join();
        }

        assertEquals(0, failCount.get());
    }


    @Test
    void synchronizedSearchWithRwLock() {

        Person person1 = new Person(1, "Ivan", "Pushkina 2", "124465656");
        Person person2 = new Person(2, "Petr", "Lenina 23", "2132312");
        Person person3 = new Person(3, "Bob", "Vladimirskaya 40", "434554657");

        databaseWithRwLock.add(person1);
        databaseWithRwLock.add(person2);
        databaseWithRwLock.add(person3);

        assertEquals(person2, databaseWithRwLock.findByName(person2.name()).get(0));
        assertEquals(person1, databaseWithRwLock.findByAddress(person1.address()).get(0));
        assertEquals(person3, databaseWithRwLock.findByPhone(person3.phoneNumber()).get(0));

        databaseWithRwLock.delete(person2.id());

        assertEquals(new ArrayList<>(), databaseWithRwLock.findByName(person2.name()));
        assertEquals(new ArrayList<>(), databaseWithRwLock.findByAddress(person2.address()));
        assertEquals(new ArrayList<>(), databaseWithRwLock.findByPhone(person2.phoneNumber()));
    }


    @Test
    void syncParallelSearchWithRwLock() throws InterruptedException {

        List<Person> personList = List.of(
            new Person(1, "Ivan", "Pushkina 2", "124465656"),
            new Person(2, "Petr", "Lenina 23", "2132312"),
            new Person(3, "Bob", "Vladimirskaya 40", "434554657"),
            new Person(4, "Jack", "adsakjsd", "65685675687"),
            new Person(5, "Chuck", "Grow street", "86886868"),
            new Person(6, "Rick", "ghghghgdf", "85665675876"),
            new Person(7, "Sasha", "khkhkhkh", "8998598569"),
            new Person(8, "Vitya", "lksdlkfsmd", "12345677")
        );

        List<Thread> adders = new ArrayList<>();
        List<Thread> removers = new ArrayList<>();
        List<Thread> readers = new ArrayList<>();
        AtomicInteger failCount = new AtomicInteger(0);

        personList.forEach(p -> {

            adders.add(new Thread(() -> databaseWithRwLock.add(p)));
            removers.add(new Thread(() -> databaseWithRwLock.delete(p.id())));

            readers.add(new Thread(() -> {

                synchronized (databaseWithRwLock) {

                    if (databaseWithRwLock.findByName(p.name()) != null) {

                        if (!(databaseWithRwLock.findByName(p.name()).equals(databaseWithRwLock.findByAddress(p.address()))
                            && databaseWithRwLock.findByAddress(p.address()).equals(databaseWithRwLock.findByPhone(p.phoneNumber())))) {

                            failCount.getAndIncrement();

                        }
                    }
                }
            }));
        });

        adders.forEach(Thread::start);
        removers.forEach(Thread::start);
        readers.forEach(Thread::start);

        for (Thread thread : adders) {
            thread.join();
        }
        for (Thread thread : removers) {
            thread.join();
        }
        for (Thread thread : readers) {
            thread.join();
        }

        assertEquals(0, failCount.get());
    }





}
