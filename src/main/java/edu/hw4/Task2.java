package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task2 {

    public List<Animal> outputKSortedElements(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

}
