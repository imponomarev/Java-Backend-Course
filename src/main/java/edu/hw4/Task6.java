package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Task6 {

    public Map<Animal.Type, Animal> getHeviestAnimals(List<Animal> animals) {

        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparing(Animal::weight)),
                Optional::get
            )));
    }
}
