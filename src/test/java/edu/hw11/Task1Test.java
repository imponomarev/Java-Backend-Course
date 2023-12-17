package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Task1Test {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void testHelloBuddy(){
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString")).intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        try {

            assertThat(dynamicType
                .getDeclaredConstructor()
                .newInstance()
                .toString())
                .isEqualTo("Hello, ByteBuddy!");

        } catch (InstantiationException | InvocationTargetException | IllegalAccessException
        | NoSuchMethodException e) {
            LOGGER.error(e.getMessage());
        }
    }


}
