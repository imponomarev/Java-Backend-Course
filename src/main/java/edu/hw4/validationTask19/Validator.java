package edu.hw4.validationTask19;

import edu.hw4.Animal;
import java.util.HashSet;
import java.util.Set;

public class Validator {


    public Set<ValidationError> validate(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        validateAge(animal, errors);
        validateHeight(animal, errors);
        validateWeight(animal, errors);

        return errors;

    }


    private Set<ValidationError> validateAge(Animal animal, Set<ValidationError> errors) {
        if (animal.age() < 0) {
            errors.add(new AgeValidationError());
        }
        return errors;
    }

    private Set<ValidationError> validateHeight(Animal animal, Set<ValidationError> errors) {
        if (animal.height() < 0) {
            errors.add(new HeightValidationError());
        }
        return errors;
    }

    private Set<ValidationError> validateWeight(Animal animal, Set<ValidationError> errors) {
        if (animal.weight() < 0) {
            errors.add(new WeightValidationError());
        }
        return errors;
    }
}
