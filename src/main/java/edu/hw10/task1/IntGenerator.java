package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class IntGenerator implements FieldGenerator {

    private static final Random random = new Random();

    @Override
    public Object generate(Annotation[] annotations) {

        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                max = maxAnnotation.value();
            }
        }

        return random.nextInt(min, max);
    }
}
