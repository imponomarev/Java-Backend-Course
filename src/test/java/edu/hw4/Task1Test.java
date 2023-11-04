package edu.hw4;


import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.*;
import static edu.hw4.Animal.Type.*;

class Task1Test {

    @Test
    void heightComparatorTest() {

        Animal cat = new Animal("Tom", CAT, M, 3, 20, 3, true);
        Animal dog = new Animal("Bill", DOG, M, 2, 40, 10, true);
        Animal bird = new Animal("Kesha", BIRD, M, 1, 10, 1, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird);

        Task1 task1 = new Task1();

        List<Animal> expected = Arrays.asList(bird, cat, dog);

        List<Animal> result = task1.sortAnimalsByHeight(animals);

        Assertions.assertEquals(result, expected);
    }

}
