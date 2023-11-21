package edu.hw4;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;

class Task2Test {

    Task2 task2 = new Task2();

    @Test
    void weightComparatorTest() {

        Animal cat = new Animal("Tom", CAT, M, 3, 20, 3, true);
        Animal dog = new Animal("Bill", DOG, M, 2, 40, 10, true);
        Animal bird = new Animal("Kesha", BIRD, M, 1, 10, 1, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird);

        List<Animal> expected = Arrays.asList(dog, cat);

        List<Animal> result = task2.outputKSortedElements(animals, 2);

        Assertions.assertEquals(result, expected);
    }
}
