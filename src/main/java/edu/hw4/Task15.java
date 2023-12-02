package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task15 {

    public Map<Animal.Type, Integer> getTotalWeightOfAnimalsOfEachSpecies(List<Animal> animals, int k, int l) {
        return animals.stream().filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type,
                Collectors.summingInt(Animal::weight)));
    }
}
