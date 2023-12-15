package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class DoubleGenerator implements FieldGenerator {

    private static final Random RANDOM = new Random();

    @Override
    public Object generate(Annotation[] annotations) {

        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min =  minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                max =  maxAnnotation.value();
            }
        }

        return RANDOM.nextDouble(min, max);
    }
}
