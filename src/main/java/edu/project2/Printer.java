package edu.project2;


public class Printer {
    private final Maze maze;
    private final String[][] strMaze;
    private static final int CELL_SIZE = 3;

    private static final int COORDINATE_PADDING = 10;


    public Printer(Maze maze) {
        this.maze = maze;
        this.strMaze = new String[maze.getHeight() * CELL_SIZE][maze.getWidth() * CELL_SIZE];
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
            strI += i * CELL_SIZE;
            strJ = 1;
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell cell = maze.getCell(i, j);
                strJ += j * CELL_SIZE;
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
                strJ -= j * CELL_SIZE;
            }
            strI -= i * CELL_SIZE;
        }
        return strMaze;
    }

    public void addSolution(int[][] solution) {
        for (int[] cords : solution) {
            strMaze[cords[0] * CELL_SIZE + 1][cords[1] * CELL_SIZE + 1] = "**";
        }
        strMaze[solution[0][0] * CELL_SIZE + 1][solution[0][1] * CELL_SIZE + 1] = "EN";

        strMaze[solution[solution.length - 1][0] * CELL_SIZE + 1]
            [solution[solution.length - 1][1] * CELL_SIZE + 1] = "EX";
    }

    public void printMaze() {

        StringBuilder yCoordinates = new StringBuilder();
        yCoordinates.append("      ");
        for (int i = 0; i < maze.getWidth(); i++) {
            yCoordinates.append(i).append("     ");
        }

        System.out.println(yCoordinates);

        for (int i = 0; i < strMaze.length; i++) {
            StringBuilder row = new StringBuilder();
            if (i % CELL_SIZE == 1) {
                if (i / CELL_SIZE < COORDINATE_PADDING) {
                    row.append(i / CELL_SIZE).append("   ");
                } else {
                    row.append(i / CELL_SIZE).append("  ");
                }
            } else {
                row.append("    ");
            }

            for (String cell : strMaze[i]) {
                row.append(cell);
            }
            System.out.println(row);
        }
    }
}
