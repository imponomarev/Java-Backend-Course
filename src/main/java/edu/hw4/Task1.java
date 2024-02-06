package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task1 {

    public List<Animal> sortAnimalsByHeight(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::height))
            .toList();
    }
}
