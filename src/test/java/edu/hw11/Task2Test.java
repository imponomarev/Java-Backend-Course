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

class Task2Test {


    @Test
    void replacingSumWithMultiplicationTest() {

        try {

            ByteBuddyAgent.install();

            new ByteBuddy().redefine(ArithmeticUtils.class)
                .method(ElementMatchers.named("sum"))
                .intercept(MethodDelegation
                    .to(ProductionSumImpl.class))
                .make()
                .load(
                    ClassLoader.getSystemClassLoader(),
                    ClassReloadingStrategy.fromInstalledAgent()
                )
                .getLoaded();

            int result = ArithmeticUtils.sum(3, 3);

            assertEquals(9, result);

        } catch (Exception e) {}

    }
}
