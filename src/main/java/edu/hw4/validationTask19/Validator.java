package edu.hw4.validationTask19;

import edu.hw4.Animal;
import java.util.HashSet;
import java.util.Set;

public class Validator {


    public Set<ValidatorError> validate(Animal animal) {
        Set<ValidatorError> errors = new HashSet<>();

        validateAge(animal, errors);
        validateHeight(animal, errors);
        validateWeight(animal, errors);

        return errors;

    }


    private Set<ValidatorError> validateAge(Animal animal, Set<ValidatorError> errors) {
        if (animal.age() < 0) {
            errors.add(new AgeValidationError());
        }
        return errors;
    }

    private Set<ValidatorError> validateHeight(Animal animal, Set<ValidatorError> errors) {
        if (animal.height() < 0) {
            errors.add(new HeightValidationError());
        }
        return errors;
    }

    private Set<ValidatorError> validateWeight(Animal animal, Set<ValidatorError> errors) {
        if (animal.weight() < 0) {
            errors.add(new WeightValidationError());
        }
        return errors;
    }
}
