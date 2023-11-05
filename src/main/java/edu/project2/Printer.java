package edu.project2;

import org.apache.logging.log4j.Logger;

public class Printer {
    private final Maze maze;
    private final String[][] strMaze;
    private final Logger logger;
    private static final int CELL_SIZE = 3;


    public Printer(Maze maze, Logger logger) {
        this.maze = maze;
        this.strMaze = new String[maze.getHeight() * 3][maze.getWidth() * 3];
        this.logger = logger;

    }

    private void initializeStrMaze() {
        for (int i = 0; i < strMaze.length; i++) {
            for (int j = 0; j < strMaze[0].length; j++) {
                if (i == 0 || j == 0 || i == strMaze.length - 1 || j == strMaze[0].length - 1) {
                    strMaze[i][j] = "██";
                } else {
                    strMaze[i][j] = "  ";
                }
            }
        }
    }

    public String[][] generateStrMaze() {
        initializeStrMaze();
        int strI = 1;
        int strJ;
        for (int i = 0; i < maze.getHeight(); i++) {
            strI += i * 3;
            strJ = 1;
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell cell = maze.getCell(i, j);
                strJ += j * 3;
                if (cell.hasWall(Cell.Wall.BOTTOM)) {
                    strMaze[strI + 1][strJ - 1] = "██";
                    strMaze[strI + 1][strJ] = "██";
                    strMaze[strI + 1][strJ + 1] = "██";
                }
                if (cell.hasWall(Cell.Wall.LEFT)) {
                    strMaze[strI + 1][strJ - 1] = "██";
                    strMaze[strI][strJ - 1] = "██";
                    strMaze[strI - 1][strJ - 1] = "██";
                }
                if (cell.hasWall(Cell.Wall.TOP)) {
                    strMaze[strI - 1][strJ - 1] = "██";
                    strMaze[strI - 1][strJ] = "██";
                    strMaze[strI - 1][strJ + 1] = "██";
                }
                if (cell.hasWall(Cell.Wall.RIGHT)) {
                    strMaze[strI + 1][strJ + 1] = "██";
                    strMaze[strI][strJ + 1] = "██";
                    strMaze[strI - 1][strJ + 1] = "██";
                }
                strJ -= j * 3;
            }
            strI -= i * 3;
        }
        return strMaze;
    }

    public void addSolution(int[][] solution) {
        for (int[] cords : solution) {
            strMaze[cords[0] * CELL_SIZE + 1][cords[1] * CELL_SIZE + 1] = "**";
        }
        strMaze[solution[0][0] * CELL_SIZE + 1][solution[0][1] * CELL_SIZE + 1] = "EN";

        strMaze[solution[solution.length - 1][0] * CELL_SIZE + 1][solution[solution.length - 1][1] * CELL_SIZE + 1] = "EX";
    }

    public void printMaze() {
        StringBuilder CoordinatesY = new StringBuilder();
        CoordinatesY.append("      ");
        for (int i = 0; i < maze.getWidth(); i++) {
            CoordinatesY.append(i).append("     ");
        }
        logger.info(CoordinatesY);
        for (int i = 0; i < strMaze.length; i++) {
            StringBuilder row = new StringBuilder();
            if (i % 3 == 1) {
                if (i / 3 < 10) {
                    row.append(i / 3).append("   ");
                } else {
                    row.append(i / 3).append("  ");
                }
            } else {
                row.append("    ");
            }

            for (String cell : strMaze[i]) {
                row.append(cell);
            }
            logger.info(row);
        }
    }
}
