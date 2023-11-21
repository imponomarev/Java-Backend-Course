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
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;

class Task18Test {

    Task18 task18 = new Task18();

    @Test
    void getHeaviestFishTest() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 21, true);
        Animal cat2 = new Animal("Tommy", CAT, F, 4, 10, 15, true);
        Animal dog = new Animal("Buttz", DOG, M, 3, 101, 10, true);
        Animal dog2 = new Animal("Butty", DOG, M, 4, 105, 20, false);
        Animal bird = new Animal("Eagle", BIRD, F, 1, 200, 1, true);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 11, false);
        Animal spider = new Animal("Black widow", SPIDER, F, 1, 20, 1, true);
        Animal spider2 = new Animal("Tarantula", SPIDER, M, 1, 25, 1, true);
        Animal fish1 = new Animal("Rybka", FISH, F, 1, 7, 1, false);
        Animal fish2 = new Animal("Shark", FISH, F, 5, 250, 300, true);
        Animal fish3 = new Animal("Whale", FISH, M, 150, 1000, 3000, false);
        Animal fish4 = new Animal("Ramp", FISH, M, 10, 150, 30, true);

        List<Animal> animals1 = Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2, fish1, fish2);
        List<Animal> animals2 = Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2, fish3);
        List<Animal> animals3 = Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2, fish4);

        Animal result = task18.getHaviestFish(animals1, animals2, animals3);

        Assertions.assertEquals(fish3, result);
    }

    @Test
    void getHeaviestFishTestWithEmptyList() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 21, true);
        Animal cat2 = new Animal("Tommy", CAT, F, 4, 10, 15, true);
        Animal dog = new Animal("Buttz", DOG, M, 3, 101, 10, true);
        Animal dog2 = new Animal("Butty", DOG, M, 4, 105, 20, false);
        Animal bird = new Animal("Eagle", BIRD, F, 1, 200, 1, true);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 11, false);
        Animal spider = new Animal("Black widow", SPIDER, F, 1, 20, 1, true);
        Animal spider2 = new Animal("Tarantula", SPIDER, M, 1, 25, 1, true);
        Animal fish1 = new Animal("Rybka", FISH, F, 1, 7, 1, false);
        Animal fish2 = new Animal("Shark", FISH, F, 5, 250, 300, true);
        Animal fish3 = new Animal("Whale", FISH, M, 150, 1000, 3000, false);
        Animal fish4 = new Animal("Ramp", FISH, M, 10, 150, 30, true);

        List<Animal> animals1 = Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2, fish1, fish2);
        List<Animal> animals2 = Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2, fish3);
        List<Animal> animals3 = Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2, fish4);
        List<Animal> animals4 = new ArrayList<>();

        Animal result = task18.getHaviestFish(animals1, animals2, animals3, animals4);

        Assertions.assertEquals(fish3, result);
    }

    @Test
    void getHeaviestFishTestWithListsWithoutFish() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 21, true);
        Animal cat2 = new Animal("Tommy", CAT, F, 4, 10, 15, true);
        Animal dog = new Animal("Buttz", DOG, M, 3, 101, 10, true);
        Animal dog2 = new Animal("Butty", DOG, M, 4, 105, 20, false);
        Animal bird = new Animal("Eagle", BIRD, F, 1, 200, 1, true);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 11, false);
        Animal spider = new Animal("Black widow", SPIDER, F, 1, 20, 1, true);
        Animal spider2 = new Animal("Tarantula", SPIDER, M, 1, 25, 1, true);

        List<Animal> animals1 = Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2);
        List<Animal> animals2 = Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2);
        List<Animal> animals3 = Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2);

        Animal result = task18.getHaviestFish(animals1, animals2, animals3);

        Assertions.assertEquals(null, result);
    }
}
