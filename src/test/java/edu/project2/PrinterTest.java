package edu.project2;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrinterTest {



    @Test
    void printerTestWithAlreadyDoneMaze() {

        Maze maze = new Maze(5, 5);
        maze.getCell(0, 0).setWalls(7);
        maze.getCell(0, 1).setWalls(9);
        maze.getCell(0, 2).setWalls(7);
        maze.getCell(0, 3).setWalls(1);
        maze.getCell(0, 4).setWalls(9);
        maze.getCell(1, 0).setWalls(13);
        maze.getCell(1, 1).setWalls(6);
        maze.getCell(1, 2).setWalls(3);
        maze.getCell(1, 3).setWalls(10);
        maze.getCell(1, 4).setWalls(12);
        maze.getCell(2, 0).setWalls(4);
        maze.getCell(2, 1).setWalls(3);
        maze.getCell(2, 2).setWalls(11);
        maze.getCell(2, 3).setWalls(5);
        maze.getCell(2, 4).setWalls(10);
        maze.getCell(3, 0).setWalls(4);
        maze.getCell(3, 1).setWalls(3);
        maze.getCell(3, 2).setWalls(9);
        maze.getCell(3, 3).setWalls(12);
        maze.getCell(3, 4).setWalls(13);
        maze.getCell(4, 0).setWalls(6);
        maze.getCell(4, 1).setWalls(11);
        maze.getCell(4, 2).setWalls(6);
        maze.getCell(4, 3).setWalls(2);
        maze.getCell(4, 4).setWalls(10);

        Printer printer = new Printer(maze);
        String[][] result = printer.generateStrMaze();

        String[][] expected ={
            {"██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██"},
            {"██", "  ", "  ", "  ", "  ", "██", "██", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "██"},
            {"██", "██", "██", "  ", "  ", "██", "██", "██", "██", "  ", "  ", "  ", "  ", "  ", "██"},
            {"██", "██", "██", "██", "  ", "  ", "██", "██", "██", "  ", "  ", "██", "██", "  ", "██"},
            {"██", "  ", "██", "██", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "██", "██", "  ", "██"},
            {"██", "  ", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "  ", "██"},
            {"██", "  ", "  ", "██", "██", "██", "██", "██", "██", "██", "██", "██", "  ", "  ", "██"},
            {"██", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "██", "██", "  ", "  ", "  ", "  ", "██"},
            {"██", "  ", "  ", "██", "██", "██", "██", "██", "██", "██", "  ", "  ", "██", "██", "██"},
            {"██", "  ", "  ", "██", "██", "██", "██", "██", "██", "██", "  ", "██", "██", "██", "██"},
            {"██", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "██", "██", "  ", "██", "██", "  ", "██"},
            {"██", "  ", "  ", "██", "██", "██", "  ", "  ", "██", "██", "  ", "██", "██", "  ", "██"},
            {"██", "  ", "  ", "██", "██", "██", "██", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "██"},
            {"██", "  ", "  ", "  ", "  ", "██", "██", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "██"},
            {"██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██", "██"}
        };

        Assertions.assertArrayEquals(expected, result);
    }
}
