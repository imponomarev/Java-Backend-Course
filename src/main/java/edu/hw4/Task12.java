package edu.hw4;

import java.util.List;

public class Task12 {

    public Integer getWeightExceedsHeight(List<Animal> animals) {
        return animals.stream()
            .mapToInt(animal -> animal.weight() > animal.height() ? 1 : 0)
            .sum();
    }
}
