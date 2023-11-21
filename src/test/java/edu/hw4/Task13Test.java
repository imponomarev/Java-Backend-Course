package edu.hw4;


import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;

class Task13Test {

    Task13 task13 = new Task13();

    @Test
    void getAnimalsWithNameOfMoreThanTwoWordsTest() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 21, true);
        Animal cat2 = new Animal("Bobbos Beavis Catvils", CAT, M, 4, 10, 15, true);
        Animal dog = new Animal("Butt-Head-Dogins", DOG, M, 3, 101, 10, true);
        Animal dog2 = new Animal("Johny", DOG, M, 4, 105, 20, true);
        Animal bird = new Animal("Eagle", BIRD, M, 1, 200, 1, true);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 11, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2);

        List<Animal> result = task13.getAnimalsWithNameOfMoreThanTwoWords(animals);

        List<Animal> expected = Arrays.asList(cat2, dog);

        Assertions.assertTrue(expected.size() == result.size() && expected.containsAll(result) && result.containsAll(expected));
    }
}
