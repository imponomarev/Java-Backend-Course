package edu.hw11;

import edu.hw11.task3.Fibonacci;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.Implementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


class Task3Test {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void ByteBuddyFibonacciTest() {

        int expected = 55;

        Class<?> dynamicType = new ByteBuddy(ClassFileVersion.JAVA_V21)
            .subclass(Object.class)
            .name("FibonacciImpl")
            .defineMethod("fib", int.class, Visibility.PUBLIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(new Fibonacci()))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();


        int result = 0;

        try {
            Object instance = dynamicType.getConstructor().newInstance();
            result = (int) dynamicType.getMethod("fib", int.class).invoke(instance, 10);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        Assertions.assertEquals(expected, result);
    }
}
