package edu.hw4;

import java.util.List;

public class Task9 {

    public Integer getCountOfPaws(List<Animal> animals) {
        if (animals.isEmpty()) {
            return 0;
        }

        return animals.stream()
            .mapToInt(Animal::paws).sum();
    }
}
