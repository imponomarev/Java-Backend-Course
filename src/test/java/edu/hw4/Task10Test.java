package edu.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;

class Task10Test {

    @Test
    void matchingPawsToAgeTest() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 3, true);
        Animal cat2 = new Animal("Bobbos", CAT, M, 4, 20, 4, true);
        Animal dog = new Animal("Bill", DOG, M, 3, 40, 10, true);
        Animal dog2 = new Animal("Johny", DOG, M, 4, 40, 20, true);
        Animal bird = new Animal("Kesha", BIRD, M, 1, 10, 1, false);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 2, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2);

        Task10 task10 = new Task10();

        List<Animal> result = task10.matchingPawsToAge(animals);

        List<Animal> expected = Arrays.asList(cat, dog, bird, bird2);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void emptyListTest() {

        Task10 task10 = new Task10();

        List<Animal> animals = new ArrayList<>();

        List<Animal> result = task10.matchingPawsToAge(animals);

        Assertions.assertEquals(null, result);
    }
}
