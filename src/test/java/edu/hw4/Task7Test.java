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

class Task7Test {

    Task7 task7 = new Task7();

    @Test
    void getCertainOldestAnimalTest() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 3, true);
        Animal cat2 = new Animal("Bobbos", CAT, M, 4, 20, 4, true);
        Animal dog = new Animal("Bill", DOG, M, 3, 40, 10, true);
        Animal dog2 = new Animal("Johny", DOG, M, 2, 40, 20, true);
        Animal bird = new Animal("Kesha", BIRD, M, 1, 10, 1, false);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 2, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2);

        Animal result = task7.getCertainOldestAnimal(animals, 2);

        Assertions.assertEquals(cat2, result);
    }

    @Test
    void emptyListTest() {

        List<Animal> animals = new ArrayList<>();

        Animal result = task7.getCertainOldestAnimal(animals, 2);

        Assertions.assertEquals(null, result);
    }

    @Test
    void getCertainOldestAnimalTestWithNegK() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 3, true);
        Animal cat2 = new Animal("Bobbos", CAT, M, 4, 20, 4, true);
        Animal dog = new Animal("Bill", DOG, M, 3, 40, 10, true);
        Animal dog2 = new Animal("Johny", DOG, M, 2, 40, 20, true);
        Animal bird = new Animal("Kesha", BIRD, M, 1, 10, 1, false);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 2, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2);

        Animal result = task7.getCertainOldestAnimal(animals, -2);

        Assertions.assertEquals(null, result);
    }

    @Test
    void getCertainOldestAnimalTestWithTooBigK() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 3, true);
        Animal cat2 = new Animal("Bobbos", CAT, M, 4, 20, 4, true);
        Animal dog = new Animal("Bill", DOG, M, 3, 40, 10, true);
        Animal dog2 = new Animal("Johny", DOG, M, 2, 40, 20, true);
        Animal bird = new Animal("Kesha", BIRD, M, 1, 10, 1, false);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 2, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2);

        Animal result = task7.getCertainOldestAnimal(animals, 100);

        Assertions.assertEquals(null, result);
    }


}
