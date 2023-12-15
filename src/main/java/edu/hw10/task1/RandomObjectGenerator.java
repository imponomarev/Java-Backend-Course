package edu.hw10.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RandomObjectGenerator {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Map<Class<?>, FieldGenerator> FIELD_GENERATORS = new HashMap<>();

    static {
        FIELD_GENERATORS.put(boolean.class, new BooleanGenerator());
        FIELD_GENERATORS.put(byte.class, new ByteGenerator());
        FIELD_GENERATORS.put(double.class, new DoubleGenerator());
        FIELD_GENERATORS.put(float.class, new FloatGenerator());
        FIELD_GENERATORS.put(int.class, new IntGenerator());
        FIELD_GENERATORS.put(String.class, new StringGenerator());
    }

    public <T> T nextObject(Class<T> clazz, String factoryMethodName) {

        try {

            return processClass(clazz, factoryMethodName);

        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            LOGGER.error(e.getMessage());
            return null;
        }

    }

    public <T> T nextObject(Class<T> clazz) {

        try {

            return processClass(clazz, null);

        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            LOGGER.error(e.getMessage());
            return null;
        }

    }

    private <T> T processClass(Class<T> clazz, String factoryMethodName)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Method method = null;

        if (factoryMethodName != null) {

            method = Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.getName().equals(factoryMethodName))
                .findFirst()
                .orElse(null);

        }

        if (method == null) {

            Constructor<T> constructor = getConstructor(clazz);

            Object[] args = initArguments(constructor.getParameters());

            return clazz.cast(constructor.newInstance(args));

        } else {

            Object[] args = initArguments(method.getParameters());

            return clazz.cast(method.invoke(null, args));

        }
    }


    private Object[] initArguments(Parameter[] parameters) {

        Object[] arguments = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            arguments[i] = FIELD_GENERATORS.get(parameters[i].getType()).generate(parameters[i].getAnnotations());
        }

        return arguments;
    }

    private <T> Constructor<T> getConstructor(Class<T> clazz) {

        Constructor<?> maxArgsConstructor = null;

        try {

            maxArgsConstructor = clazz.getDeclaredConstructors()[0];

        } catch (IndexOutOfBoundsException e) {
            LOGGER.error(e.getMessage());
        }

        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {

            if (constructor.getParameterCount() >
                maxArgsConstructor.getParameterCount()) {

                maxArgsConstructor = constructor;

            }
        }

        return (Constructor<T>) maxArgsConstructor;
    }
}
