package edu.project2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProgramLogicTest {

    private final Logger LOGGER = LogManager.getLogger();

    @Test
    void testMainWhenMazeIsSolvableThenPrintSolution() {

        int height = 5;
        int width = 5;
        int startX = 0;
        int startY = 0;
        int endX = 4;
        int endY = 4;

        MazeGenerator generator = new MazeGenerator(height, width);
        Maze maze = generator.generate();

        Printer printer = new Printer(maze);
        MazeSolver solver = new MazeSolver(maze);

        Coordinates start = new Coordinates(startX, startY);
        Coordinates end = new Coordinates(endX, endY);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {

            int[][] solution = solver.solve(start, end);
            printer.generateStrMaze();
            printer.addSolution(solution);
            printer.printMaze();

        } catch (RuntimeException exception) {
            LOGGER.info("Cannot solve this!");
        }

        String printedContent = outContent.toString();
        assertTrue(printedContent.contains("EN"));
        assertTrue(printedContent.contains("EX"));

        System.setOut(System.out);
    }


    @Test
    void testMainWhenMazeIsNotSolvableThenPrintErrorMessage() {

        int height = 5;
        int width = 5;
        int startX = 0;
        int startY = 0;
        int endX = 4;
        int endY = 4;

        MazeGenerator generator = new MazeGenerator(height, width);
        Maze maze = generator.generate();

        Printer printer = new Printer(maze);
        MazeSolver solver = new MazeSolver(maze);

        Coordinates start = new Coordinates(startX, startY);
        Coordinates end = new Coordinates(endX, endY + 1);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Logger logger = Mockito.mock(Logger.class);

        try {
            int[][] solution = solver.solve(start, end);
            printer.generateStrMaze();
            printer.addSolution(solution);
            printer.printMaze();
        } catch (RuntimeException exception) {
            logger.info("Cannot solve this!");
        }

        verify(logger).info("Cannot solve this!");

        System.setOut(System.out);
    }
}
