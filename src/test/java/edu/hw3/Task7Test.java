package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task7Test {

    @Test
    void nullKeyInTreeMapAddingTest() {

        Task7 task7 = new Task7();

        task7.tree.put(null, "test");

        Assertions.assertTrue(task7.tree.containsKey(null));
    }
}
