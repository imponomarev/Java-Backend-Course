package edu.hw4;

import java.util.List;

public class Task17 {

    public Boolean spidersBiteMoreOftenThanDogs(List<Animal> animals) {
        long dogBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();

        long spiderBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();

        if (spiderBites == 0 && dogBites == 0) {
            return false;
        }

        return spiderBites > dogBites;
    }
}
