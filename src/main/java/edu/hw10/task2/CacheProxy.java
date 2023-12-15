package edu.hw10.task2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheProxy implements InvocationHandler {

    private final Object target;
    private final Map<String, Object> memoryCache = new HashMap<>();
    private static final String PATH = "src/main/resources/test-file.txt";

    private static final Logger LOGGER = LogManager.getLogger();

    private CacheProxy(Object target) {
        this.target = target;
        Path path = Paths.get(PATH);

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(T target, Class<?> targetClass) {

        return (T) Proxy.newProxyInstance(
            targetClass.getClassLoader(),
            new Class<?>[] {targetClass},
            new CacheProxy(target)
        );

    }

    @SuppressWarnings("ReturnCount")
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.isAnnotationPresent(Cache.class)) {
            Cache cacheAnnotation = method.getAnnotation(Cache.class);

            if (cacheAnnotation.persist()) {

                String cacheKey = generateCacheKey(method, args);

                if (memoryCache.containsKey(cacheKey)) {
                    return memoryCache.get(cacheKey);
                }

                Object result = readFromDisk();
                if (result != null) {
                    memoryCache.put(cacheKey, result);
                    return result;
                }

                result = method.invoke(target, args);

                memoryCache.put(cacheKey, result);

                writeToDisk(cacheKey, result);

                return result;

            } else {

                String cacheKey = generateCacheKey(method, args);

                if (memoryCache.containsKey(cacheKey)) {
                    return memoryCache.get(cacheKey);
                }

                Object result = method.invoke(target, args);
                memoryCache.put(cacheKey, result);
                return result;
            }
        }

        return method.invoke(target, args);
    }

    private String generateCacheKey(Method method, Object[] args) {
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(method.getName());
        for (Object arg : args) {
            keyBuilder.append("_").append(arg.toString());
        }
        return keyBuilder.toString();
    }

    private Object readFromDisk() {

        try {
            File cacheFile = new File(PATH);
            if (cacheFile.exists()) {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(cacheFile))) {
                    return inputStream.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    private void writeToDisk(String cacheKey, Object result) {

        Path path = Paths.get(PATH);

        try {
            File cacheFile = new File(PATH);
            if (cacheFile.exists()) {
                if (containsKeyInFile(cacheKey)) {
                    return;
                }
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path,
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE
            )) {
                writer.write(cacheKey + ":" + result);
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private boolean containsKeyInFile(String cacheKey) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(cacheKey + ":")) {
                    return true;
                }
            }
        }
        return false;
    }
}
