package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int HEIGHT  = 5;

    private static final int WIDTH  = 5;

    private static final int START_X  = 0;

    private static final int START_Y  = 0;

    private static final int END_X  = 4;

    private static final int END_Y  = 4;

    public Main() {

    }


    public static void main(String[] args) {

        MazeGenerator generator = new MazeGenerator(HEIGHT, WIDTH);

        Maze maze = generator.generate();

        Printer printer = new Printer(maze, LOGGER);

        MazeSolver solver = new MazeSolver(maze);

        Coordinates start = new Coordinates(START_X, START_Y);

        Coordinates end = new Coordinates(END_X, END_Y);

        try {
            int[][] solution = solver.solve(start, end);

            printer.addSolution(solution);

            printer.printMaze();

        } catch (RuntimeException exception) {
            LOGGER.info("Cannot solve this!");
        }
    }
}
