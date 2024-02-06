package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachingPersonDatabase implements PersonDataBase {

    private final Map<Integer, Person> personMap;
    private final Map<String, List<Integer>> nameIndex;
    private final Map<String, List<Integer>> addressIndex;
    private final Map<String, List<Integer>> phoneIndex;
    private final Map<Integer, Boolean> availableIndex;


    public CachingPersonDatabase() {

        personMap = new HashMap<>();
        nameIndex = new HashMap<>();
        addressIndex = new HashMap<>();
        phoneIndex = new HashMap<>();
        availableIndex = new HashMap<>();

    }

    private void addToIndex(Map<String, List<Integer>> index, String key, int id) {

        List<Integer> ids = index.computeIfAbsent(key, k -> new ArrayList<>());

        if (!ids.contains(id)) {
            ids.add(id);
        }

    }

    private void removeFromIndex(Map<String, List<Integer>> index, String key, int id) {

        List<Integer> ids = index.get(key);

        if (ids != null) {
            ids.remove(Integer.valueOf(id));
        }

    }

    private List<Person> getAvailablePersonsByIds(List<Integer> ids) {

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

    }

    @Override
    public synchronized void add(Person person) {

        personMap.put(person.id(), person);

        addToIndex(nameIndex, person.name(), person.id());
        addToIndex(addressIndex, person.address(), person.id());
        addToIndex(phoneIndex, person.phoneNumber(), person.id());

        availableIndex.put(person.id(), true);

    }

    @Override
    public synchronized void delete(int id) {

        Person person = personMap.remove(id);

        if (person != null) {

            removeFromIndex(nameIndex, person.name(), id);
            removeFromIndex(addressIndex, person.address(), id);
            removeFromIndex(phoneIndex, person.phoneNumber(), id);

            availableIndex.remove(id);
        }

    }

    @Override
    public synchronized List<Person> findByName(String name) {

        List<Integer> ids = nameIndex.getOrDefault(name, new ArrayList<>());

        return getAvailablePersonsByIds(ids);

    }

    @Override
    public synchronized List<Person> findByAddress(String address) {

        List<Integer> ids = addressIndex.getOrDefault(address, new ArrayList<>());

        return getAvailablePersonsByIds(ids);

    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {

        List<Integer> ids = phoneIndex.getOrDefault(phone, new ArrayList<>());

        return getAvailablePersonsByIds(ids);

    }
}
