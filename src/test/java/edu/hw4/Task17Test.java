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
import static edu.hw4.Animal.Type.SPIDER;

class Task17Test {

    Task17 task17 = new Task17();

    @Test
    void spidersBiteMoreOftenThanDogsTestTrue() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 21, true);
        Animal cat2 = new Animal("Tommy", CAT, F, 4, 10, 15, true);
        Animal dog = new Animal("Buttz", DOG, M, 3, 101, 10, true);
        Animal dog2 = new Animal("Butty", DOG, M, 4, 105, 20, false);
        Animal bird = new Animal("Eagle", BIRD, F, 1, 200, 1, true);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 11, false);
        Animal spider = new Animal("Black widow", SPIDER, F, 1, 20, 1, true);
        Animal spider2 = new Animal("Tarantula", SPIDER, M, 1, 25, 1, true);


        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2);

        Boolean result = task17.spidersBiteMoreOftenThanDogs(animals);

        Assertions.assertTrue(result);
    }


    @Test
    void spidersBiteMoreOftenThanDogsTestFalse() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 21, true);
        Animal cat2 = new Animal("Tommy", CAT, F, 4, 10, 15, true);
        Animal dog = new Animal("Buttz", DOG, M, 3, 101, 10, true);
        Animal dog2 = new Animal("Butty", DOG, M, 4, 105, 20, true);
        Animal bird = new Animal("Eagle", BIRD, F, 1, 200, 1, true);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 11, false);
        Animal spider = new Animal("Black widow", SPIDER, F, 1, 20, 1, true);
        Animal spider2 = new Animal("Tarantula", SPIDER, M, 1, 25, 1, false);


        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2);

        Boolean result = task17.spidersBiteMoreOftenThanDogs(animals);

        Assertions.assertFalse(result);
    }

    @Test
    void spidersBiteMoreOftenThanDogsTestFalseWithEnoughData() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 21, true);
        Animal cat2 = new Animal("Tommy", CAT, F, 4, 10, 15, true);
        Animal dog = new Animal("Buttz", DOG, M, 3, 101, 10, true);
        Animal dog2 = new Animal("Butty", DOG, M, 4, 105, 20, true);
        Animal bird = new Animal("Eagle", BIRD, F, 1, 200, 1, true);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 11, false);

        List<Animal> animals = Arrays.asList(cat, dog, bird, cat2, dog2, bird2);

        Boolean result = task17.spidersBiteMoreOftenThanDogs(animals);

        Assertions.assertFalse(result);
    }


}
