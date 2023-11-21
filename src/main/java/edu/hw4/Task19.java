package edu.hw4;

import edu.hw4.validationTask19.ValidationError;
import edu.hw4.validationTask19.Validator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Task19 {

    public Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        Validator validator = new Validator();

        return animals.stream()
            .filter(animal -> !(validator.validate(animal).isEmpty()))
            .collect(Collectors.toMap(
                Animal::name,
                animal -> validator.validate(animal)));
    }
}
