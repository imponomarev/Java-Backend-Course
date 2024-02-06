package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;

public record MyClazz(@NotNull String name, @Min(1) float age, @Max(50000) byte salary, boolean isMale) {

    public static MyClazz create(@NotNull String name, @Min(1) float age, @Max(50000) byte salary, boolean isMale) {
        return new MyClazz(name, age, salary, isMale);
    }
}
