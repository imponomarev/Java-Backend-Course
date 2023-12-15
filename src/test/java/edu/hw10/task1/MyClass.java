package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;

public class MyClass {


    private String name;


    private int age;


    private double salary;


    public static MyClass createInstance(@NotNull String name, @Min(0) int age, @Max(45000) double salary) {
        return new MyClass(name, age, salary);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public MyClass(@NotNull String name, @Min(1) @Max(100) int age, @Max(100) double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

}

