package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task4 {

    public Animal getLongestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElse(null);
    }
}
