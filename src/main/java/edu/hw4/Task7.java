package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task7 {

    public Animal getCertainOldestAnimal(List<Animal> animals, int k) {

        if (k <= 0 || k > animals.size()) {
            return null;
        }

        return animals.stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }
}
