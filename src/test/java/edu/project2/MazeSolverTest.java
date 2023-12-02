package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MazeSolverTest {

    @Test
    void testMazeSolver() {
        MazeGenerator generator = new MazeGenerator(5, 5);
        Maze maze = generator.generate();

        MazeSolver solver = new MazeSolver(maze);

        Coordinates start = new Coordinates(0, 0);
        Coordinates end = new Coordinates(4, 4);

        int[][] solution = solver.solve(start, end);

        Assertions.assertNotNull(solution);

        Assertions.assertEquals(start.getX(), solution[0][0]);
        Assertions.assertEquals(start.getY(), solution[0][1]);

        Assertions.assertEquals(end.getX(), solution[solution.length - 1][0]);
        Assertions.assertEquals(end.getY(), solution[solution.length - 1][1]);

        // Check that the solution passes only through neighboring cells without walls
        for (int i = 1; i < solution.length; i++) {
            int row1 = solution[i - 1][0];
            int col1 = solution[i - 1][1];
            int row2 = solution[i][0];
            int col2 = solution[i][1];

            // Check that the cells are adjacent
            boolean isNeighbour = Math.abs(row2 - row1) + Math.abs(col2 - col1) == 1;
            Assertions.assertTrue(isNeighbour);

            // Check that there is no wall between the cells
            Cell cell1 = maze.getCell(row1, col1);
            Cell cell2 = maze.getCell(row2, col2);
            boolean hasNoWall = !cell1.hasWall(getWallDirection(cell1, cell2));
            Assertions.assertTrue(hasNoWall);
        }
    }

    private Cell.Wall getWallDirection(Cell cell1, Cell cell2) {
        int rowDiff = cell2.getRow() - cell1.getRow();
        int colDiff = cell2.getCol() - cell1.getCol();

        if (rowDiff == -1) {
            return Cell.Wall.TOP;
        } else if (rowDiff == 1) {
            return Cell.Wall.BOTTOM;
        } else if (colDiff == -1) {
            return Cell.Wall.LEFT;
        } else {
            return Cell.Wall.RIGHT;
        }
    }

    @Test
    void testMazeSolverWithWrongExitCoordinates() {
        MazeGenerator generator = new MazeGenerator(5, 5);
        Maze maze = generator.generate();

        MazeSolver solver = new MazeSolver(maze);

        Coordinates start = new Coordinates(0, 0);
        Coordinates end = new Coordinates(-1, 4);

        Assertions.assertThrows(SolverException.class, () -> solver.solve(start, end));
    }

    @Test
    void executeWithAlreadyDoneMazeTest() {
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

        Coordinates start = new Coordinates(0, 0);
        Coordinates end = new Coordinates(4, 4);

        MazeSolver solver = new MazeSolver(maze);

        int[][] result = solver.solve(start, end);
        int[][] expected = {
            {0, 0}, {0, 1}, {1, 1}, {1, 2}, {1, 3}, {0, 3}, {0, 4}, {1, 4},
            {2, 4}, {2, 3}, {3, 3}, {4, 3}, {4, 4}
        };

        Assertions.assertArrayEquals(expected, result);

    }

    @Test
    void executeWithAlreadyDoneMazeTestWrongCoordinates() {
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

        Coordinates start = new Coordinates(0, 0);
        Coordinates end = new Coordinates(-4, 4);

        MazeSolver solver = new MazeSolver(maze);
        Assertions.assertThrows(SolverException.class, () -> solver.solve(start, end));

    }
}
