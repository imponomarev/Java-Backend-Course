package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class ByteGenerator implements FieldGenerator {

    private static final Random RANDOM = new Random();

    @Override
    public Object generate(Annotation[] annotations) {

        byte min = Byte.MIN_VALUE;

        byte max = Byte.MAX_VALUE;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = (byte) minAnnotation.value();
            }
            if (annotation instanceof Max maxAnnotation) {
                max = (byte) maxAnnotation.value();
            }
        }

        return (byte) RANDOM.nextInt(min, max);
    }
}
