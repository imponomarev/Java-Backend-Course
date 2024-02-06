package edu.hw4;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;

class Task16Test {

    Task16 task16 = new Task16();

    @Test
    void sortedByTypeGenderNameTest() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 21, true);
        Animal cat2 = new Animal("Tommy", CAT, F, 4, 10, 15, true);
        Animal dog = new Animal("Buttz", DOG, M, 3, 101, 10, true);
        Animal dog2 = new Animal("Butty", DOG, M, 4, 105, 20, true);
        Animal bird = new Animal("Eagle", BIRD, F, 1, 200, 1, true);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 11, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2);

        List<Animal> result = task16.sortedByTypeGenderName(animals);

        List<Animal> expected = Arrays.asList(cat, cat2, dog2, dog, bird2, bird);

        Assertions.assertEquals(expected, result);
    }
}
