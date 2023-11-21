package edu.hw4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;

class Task6Test {

    Task6 task6 = new Task6();

    @Test
    void getHeviestAnimalsTest() {

        Animal cat = new Animal("Tom", CAT, M, 3, 20, 3, true);
        Animal cat2 = new Animal("Bobbos", CAT, M, 3, 20, 4, true);
        Animal dog = new Animal("Bill", DOG, M, 2, 40, 10, true);
        Animal dog2 = new Animal("Johny", DOG, M, 2, 40, 20, true);
        Animal bird = new Animal("Kesha", BIRD, M, 1, 10, 1, false);
        Animal bird2 = new Animal("Golub", BIRD, M, 1, 10, 2, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2);

        Map<Animal.Type, Animal> expected = Map.of(CAT, cat2 , DOG, dog2, BIRD, bird2);

        Map<Animal.Type, Animal> result = task6.getHeviestAnimals(animals);

        Assertions.assertEquals(result, expected);
    }
}
