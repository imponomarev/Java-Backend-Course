package edu.hw4;

import java.util.List;

public class Task10 {

    public List<Animal> matchingPawsToAge(List<Animal> animals) {
        if (animals.isEmpty()) {
            return null;
        }
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }
}
