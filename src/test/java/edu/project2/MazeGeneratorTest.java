package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MazeGeneratorTest {


    @Test
    void testMazeGeneratorWithWrongInput(){

        Assertions.assertThrows(GeneratorException.class, () -> {
            MazeGenerator invalidGenerator = new MazeGenerator(-10, 10);
        });

    }


    @Test
    void testGenerate() {

        Generator generator = new MazeGenerator(10, 10);

        Maze maze = generator.generate();

        Assertions.assertNotNull(maze);
    }

    @Test
    void testGenerateMazeSize() {

        Generator generator = new MazeGenerator(10, 10);

        Maze maze = generator.generate();

        Assertions.assertEquals(10, maze.getHeight());
        Assertions.assertEquals(10, maze.getWidth());
    }
}
