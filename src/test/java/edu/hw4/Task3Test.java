package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;

class Task3Test {
    @Test
    void countingAnimalsTest() {

        Animal cat = new Animal("Tom", CAT, M, 3, 20, 3, true);
        Animal cat2 = new Animal("Bobby", CAT, M, 3, 20, 3, true);
        Animal dog = new Animal("Bill", DOG, M, 2, 40, 10, true);
        Animal dog2 = new Animal("Johny", DOG, M, 2, 40, 10, true);
        Animal bird = new Animal("Kesha", BIRD, M, 1, 10, 1, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2);

        Task3 task3 = new Task3();

        Map<Animal.Type, Integer> expected = Map.of(CAT, 2, DOG, 2, BIRD, 1);

        Map<Animal.Type, Integer> result = task3.countingAnimals(animals);

        Assertions.assertEquals(result, expected);
    }
}
