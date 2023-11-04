package edu.hw4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import edu.hw4.validationTask19.AgeValidationError;
import edu.hw4.validationTask19.HeightValidationError;
import edu.hw4.validationTask19.ValidatorError;
import edu.hw4.validationTask19.WeightValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;

class Task20Test {

    @Test
    void validateAnimalsTest() {

        Animal cat = new Animal("Tom", CAT, M, 5, 20, 21, true);
        Animal cat2 = new Animal("Tommy", CAT, F, -4, 10, 15, true);
        Animal dog = new Animal("Buttz", DOG, M, 3, -101, 10, true);
        Animal dog2 = new Animal("Butty", DOG, M, 4, 105, -20, false);
        Animal bird = new Animal("Eagle", BIRD, F, -1, -200, 1, true);
        Animal bird2 = new Animal("Golub", BIRD, M, 0, 10, 11, false);
        Animal spider = new Animal("Black widow", SPIDER, F, 1, 20, 1, true);
        Animal spider2 = new Animal("Tarantula", SPIDER, M, 1, 25, 1, true);
        Animal fish1 = new Animal("Rybka", FISH, F, 1, 7, 1, false);
        Animal fish2 = new Animal("Shark", FISH, F, 5, 250, 300, true);
        Animal fish3 = new Animal("Whale", FISH, M, 150, 1000, 3000, false);
        Animal fish4 = new Animal("Ramp", FISH, M, 10, 150, 30, true);

        List<Animal> animals =
            Arrays.asList(cat, dog, bird, cat2, dog2, bird2, spider, spider2, fish1, fish2, fish3, fish4);

        Task19 task19 = new Task19();
        Task20 task20 = new Task20();

        Map<String, Set<ValidatorError>> resultOfValidation = task19.validateAnimals(animals);

        Map<String, Set<ValidatorError>> expected = new HashMap<>();

        Set<ValidatorError> errorsSet1 = new HashSet<>();
        errorsSet1.add(new AgeValidationError());

        Set<ValidatorError> errorsSet2 = new HashSet<>();
        errorsSet2.add(new HeightValidationError());

        Set<ValidatorError> errorsSet3 = new HashSet<>();
        errorsSet3.add(new WeightValidationError());

        Set<ValidatorError> errorsSet4 = new HashSet<>();
        errorsSet4.add(new AgeValidationError());
        errorsSet4.add(new HeightValidationError());

        expected.put("Tommy", errorsSet1);
        expected.put("Buttz", errorsSet2);
        expected.put("Butty", errorsSet3);
        expected.put("Eagle", errorsSet4);

        Map<String, String> result = task20.beautifyValidation(resultOfValidation);

        for (Map.Entry<String, String> entry : result.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            System.out.println(key + ": " + value);
        }
        Assertions.assertEquals(expected, resultOfValidation);
    }
}
