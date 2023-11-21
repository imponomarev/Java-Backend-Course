package edu.hw4;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task5 {

    public Animal.Sex preferentialSex(List<Animal> animals) {

        if (animals.isEmpty()) {
            return null;
        }

        return Collections.max(
                animals
                    .stream()
                    .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
                    .entrySet(),
                Comparator.comparingLong(Map.Entry::getValue)
            )
            .getKey();
    }
}
