package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.util.Random;

public class BooleanGenerator implements FieldGenerator {

    private static final Random RANDOM = new Random();

    @Override
    public Object generate(Annotation[] annotations) {
        return RANDOM.nextBoolean();
    }
}
