package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;


public class Task18 {

    public Animal getHaviestFish(List<Animal> ... animals) {
        return Arrays.stream(animals)
            .flatMap(Collection::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH).max(Comparator.comparing(Animal::weight)).orElse(null);
    }

}
