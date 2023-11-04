package edu.hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;

class Task8Test {

    @Test
    void getHeviestAnimalSortedByHeightTest() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 3, true);
        Animal cat2 = new Animal("Bobbos", CAT, M, 4, 20, 4, true);
        Animal dog = new Animal("Bill", DOG, M, 3, 40, 10, true);
        Animal dog2 = new Animal("Johny", DOG, M, 2, 40, 20, true);
        Animal bird = new Animal("Kesha", BIRD, M, 1, 10, 1, false);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 2, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2);

        Task8 task8 = new Task8();

        Optional<Animal> result = task8.getHeviestAnimalSortedByHeight(animals, 40);

        Assertions.assertEquals(Optional.of(cat2), result);
    }

    @Test
    void emptyListTest() {

        Task8 task8 = new Task8();

        List<Animal> animals = new ArrayList<>();

        Optional<Animal> result = task8.getHeviestAnimalSortedByHeight(animals, 1);

        Assertions.assertEquals(Optional.empty(), result);
    }

    @Test
    void getHeviestAnimalSortedByHeightTestWithNegK() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 3, true);
        Animal cat2 = new Animal("Bobbos", CAT, M, 4, 20, 4, true);
        Animal dog = new Animal("Bill", DOG, M, 3, 40, 10, true);
        Animal dog2 = new Animal("Johny", DOG, M, 2, 40, 20, true);
        Animal bird = new Animal("Kesha", BIRD, M, 1, 10, 1, false);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 2, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2);

        Task8 task8 = new Task8();

        Optional<Animal> result = task8.getHeviestAnimalSortedByHeight(animals, -40);

        Assertions.assertEquals(null, result);
    }
}
