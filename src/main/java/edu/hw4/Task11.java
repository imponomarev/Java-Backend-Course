package edu.hw4;

import java.util.List;

public class Task11 {

    private static final int LIMITATION = 100;

    public List<Animal> getTallAndBiting(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.bites() && animal.height() > LIMITATION).toList();
    }
}
