package edu.hw10.task1;

import java.lang.annotation.Annotation;

public interface FieldGenerator {

    Object generate(Annotation[] annotations);

}
