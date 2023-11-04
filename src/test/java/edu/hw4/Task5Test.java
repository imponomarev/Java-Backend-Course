package edu.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;

class Task5Test {

    @Test
    void preferentialSexIsMaleTest() {

        Animal cat = new Animal("Tom", CAT, M, 3, 20, 3, true);
        Animal cat2 = new Animal("Bobbos", CAT, M, 3, 20, 3, true);
        Animal dog = new Animal("Bill", DOG, M, 2, 40, 10, true);
        Animal dog2 = new Animal("Johny", DOG, M, 2, 40, 10, true);
        Animal bird = new Animal("Kesha", BIRD, F, 1, 10, 1, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2);

        Task5 task5 = new Task5();

        Animal.Sex result = task5.preferentialSex(animals);

        Assertions.assertEquals(result, M);
    }

    @Test
    void preferentialSexIsFemaleTest() {

        Animal cat = new Animal("Tom", CAT, F, 3, 20, 3, true);
        Animal cat2 = new Animal("Bobbos", CAT, F, 3, 20, 3, true);
        Animal dog = new Animal("Bill", DOG, M, 2, 40, 10, true);
        Animal dog2 = new Animal("Johny", DOG, M, 2, 40, 10, true);
        Animal bird = new Animal("Kesha", BIRD, F, 1, 10, 1, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2);

        Task5 task5 = new Task5();

        Animal.Sex result = task5.preferentialSex(animals);

        Assertions.assertEquals(result, F);
    }

    @Test
    void emptyListTest() {

        Task5 task5 = new Task5();

        List<Animal> animals = new ArrayList<>();

        Animal.Sex result = task5.preferentialSex(animals);

        Assertions.assertEquals(null, result);
    }
}
