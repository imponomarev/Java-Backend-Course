package edu.hw10.task2;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task2Test {



    @Test
    void saveToFileCalculatedFibNumbers() {

        FibCalculator c = new FibCalculatorClass();
        FibCalculator proxy = CacheProxy.create(c, FibCalculator.class);
        Map<String, String> map = new HashMap<>();

        long answer1 = proxy.fib(6);
        long answer2 = proxy.fib(7);
        long answer3 = proxy.fib(15);

        Path path = Paths.get("src/main/resources/test-file.txt");

        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] split = line.split(":");
                    String key = split[0];
                    String result = split[1];
                    map.put(key, result);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        assertThat(answer1).isEqualTo(8);
        assertThat(answer2).isEqualTo(13);
        assertThat(answer3).isEqualTo(610);
        assertTrue(map.containsValue("8"));
        assertTrue(map.containsValue("13"));
        assertTrue(map.containsValue("610"));
    }

    @Test
    void saveToFileCalculatedFactorialNumbers() {

        FactorialCalculator c = new FactorialCalculatorClass();
        FactorialCalculator proxy = CacheProxy.create(c, FactorialCalculator.class);
        Map<String, String> map = new HashMap<>();

        long answer1 = proxy.factorial(6);
        long answer2 = proxy.factorial(7);
        long answer3 = proxy.factorial(9);

        Path path = Paths.get("src/main/resources/test-file.txt");

        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] split = line.split(":");
                    String key = split[0];
                    String result = split[1];
                    map.put(key, result);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        assertThat(answer1).isEqualTo(720);
        assertThat(answer2).isEqualTo(5040);
        assertThat(answer3).isEqualTo(362880);
        assertTrue(map.containsValue("720"));
        assertTrue(map.containsValue("5040"));
        assertTrue(map.containsValue("362880"));
    }
}
