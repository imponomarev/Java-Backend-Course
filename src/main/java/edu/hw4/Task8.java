package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Task8 {

    public Optional<Animal> getHeaviestAnimalBelowK(List<Animal> animals, int k) {

        if (k < 0) {
            return Optional.empty();
        }

        return animals
            .stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }
}
