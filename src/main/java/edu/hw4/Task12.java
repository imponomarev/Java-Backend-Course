package edu.hw4;

import java.util.List;
import java.util.stream.Collectors;

public class Task12 {

    public Integer getWeightExceedsHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .collect(Collectors.summingInt(animal -> 1));
    }
}
