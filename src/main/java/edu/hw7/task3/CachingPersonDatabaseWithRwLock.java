package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachingPersonDatabaseWithRwLock implements PersonDataBase {

    private final Map<Integer, Person> personMap;
    private final Map<String, List<Integer>> nameIndex;
    private final Map<String, List<Integer>> addressIndex;
    private final Map<String, List<Integer>> phoneIndex;
    private final Map<Integer, Boolean> availableIndex;
    private final ReadWriteLock lock;

    public CachingPersonDatabaseWithRwLock() {

        personMap = new HashMap<>();
        nameIndex = new HashMap<>();
        addressIndex = new HashMap<>();
        phoneIndex = new HashMap<>();
        availableIndex = new HashMap<>();
        lock = new ReentrantReadWriteLock();

    }

    private void addToIndex(Map<String, List<Integer>> index, String key, int id) {

        lock.writeLock().lock();

        try {

            List<Integer> ids = index.computeIfAbsent(key, k -> new ArrayList<>());

            if (!ids.contains(id)) {
                ids.add(id);
            }

        } finally {
            lock.writeLock().unlock();
        }
    }

    private void removeFromIndex(Map<String, List<Integer>> index, String key, int id) {

        lock.writeLock().lock();

        try {

            List<Integer> ids = index.get(key);

            if (ids != null) {
                ids.remove(Integer.valueOf(id));
            }

        } finally {
            lock.writeLock().unlock();
        }
    }

    private List<Person> getAvailablePersonsByIds(List<Integer> ids) {

        lock.readLock().lock();

        try {

            List<Person> result = new ArrayList<>();

            for (int id : ids) {

                Boolean available = availableIndex.get(id);

                if (available != null && available) {

                    Person person = personMap.get(id);
                    if (person != null) {
                        result.add(person);
                    }
                }
            }

            return result;

        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void add(Person person) {

        lock.writeLock().lock();

        try {

            personMap.put(person.id(), person);

            addToIndex(nameIndex, person.name(), person.id());
            addToIndex(addressIndex, person.address(), person.id());
            addToIndex(phoneIndex, person.phoneNumber(), person.id());

            availableIndex.put(person.id(), true);

        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {

        lock.writeLock().lock();

        try {

            Person person = personMap.remove(id);

            if (person != null) {

                removeFromIndex(nameIndex, person.name(), id);
                removeFromIndex(addressIndex, person.address(), id);
                removeFromIndex(phoneIndex, person.phoneNumber(), id);

                availableIndex.remove(id);

            }

        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {

        lock.readLock().lock();

        try {

            List<Integer> ids = nameIndex.getOrDefault(name, new ArrayList<>());
            return getAvailablePersonsByIds(ids);

        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {

        lock.readLock().lock();

        try {

            List<Integer> ids = addressIndex.getOrDefault(address, new ArrayList<>());
            return getAvailablePersonsByIds(ids);

        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {

        lock.readLock().lock();

        try {

            List<Integer> ids = phoneIndex.getOrDefault(phone, new ArrayList<>());
            return getAvailablePersonsByIds(ids);

        } finally {
            lock.readLock().unlock();
        }
    }
}
