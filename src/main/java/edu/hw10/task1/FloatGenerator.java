package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class FloatGenerator implements FieldGenerator {

    private static final Random random = new Random();

    @Override
    public Object generate(Annotation[] annotations) {

        float min = Float.MIN_VALUE;

        float max = Float.MAX_VALUE;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = minAnnotation.value();
            }
            if (annotation instanceof Max maxAnnotation) {
                max = maxAnnotation.value();
            }
        }

        return random.nextFloat(min, max);
    }
}
