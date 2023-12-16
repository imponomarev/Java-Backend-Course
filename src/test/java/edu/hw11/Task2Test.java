package edu.hw11;

import edu.hw11.task2.ArithmeticUtils;
import edu.hw11.task2.ProductionSumImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Task2Test {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void replacingSumWithMultiplicationTest() {

        ByteBuddyAgent.install();

        new ByteBuddy().redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation
            .to(ProductionSumImpl.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(),
            ClassReloadingStrategy.fromInstalledAgent())
            .getLoaded();

        try {

            int result = ArithmeticUtils.sum(3, 3);

            assertEquals(9, result);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
