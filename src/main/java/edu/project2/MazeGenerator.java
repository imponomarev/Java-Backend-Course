package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class MazeGenerator implements Generator {

    private final Maze maze;

    private final boolean[][] visited;

    private final Deque<Cell> deque;

    Random random;

    public MazeGenerator(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new GeneratorException("height and width of maze have to be positive numbers!");
        }

        this.maze = new Maze(height, width);
        this.visited = new boolean[height][width];
        this.deque = new ArrayDeque<>();
        this.random = new Random();
    }

    private void initMazeWithWalls() {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                maze.getCell(i, j).setWalls(15);
            }
        }
    }

    @Override
    public Maze generate() {
        initMazeWithWalls();
        Cell initCell = maze.getCell(0, 0);
        markVisited(initCell);
        deque.push(initCell);

        while (!deque.isEmpty()) {
            Cell curr = deque.pop();
            List<Cell> unvisitedNeighbours = getUnvisitedNeighboursOfCell(curr);

            if (!unvisitedNeighbours.isEmpty()) {
                deque.push(curr);
                Cell choice = chooseRandomlyNeighbour(unvisitedNeighbours);

                removeWall(curr, choice);
                markVisited(choice);
                deque.push(choice);
            }
        }
        return maze;
    }


    private void markVisited(Cell cell) {
        visited[cell.getRow()][cell.getCol()] = true;
    }

    private List<Cell> getUnvisitedNeighboursOfCell(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();

        int row = cell.getRow();
        int col = cell.getCol();

        if (row > 0 && !visited[row - 1][col]) {
            neighbours.add(maze.getCell(row - 1, col));
        }
        if (row < maze.getHeight() - 1 && !visited[row + 1][col]) {
            neighbours.add(maze.getCell(row + 1, col));
        }
        if (col > 0 && !visited[row][col - 1]) {
            neighbours.add(maze.getCell(row, col - 1));
        }
        if (col < maze.getWidth() - 1 && !visited[row][col + 1]) {
            neighbours.add(maze.getCell(row, col + 1));
        }

        return neighbours;
    }

    private Cell chooseRandomlyNeighbour(List<Cell> neighbours) {
        int index = random.nextInt(neighbours.size());
        return neighbours.get(index);
    }

    private void removeWall(Cell cell1, Cell cell2) {
        int rowDiff = cell2.getRow() - cell1.getRow();
        int colDiff = cell2.getCol() - cell1.getCol();

        if (rowDiff == -1) {
            cell1.removeWall(Cell.Wall.TOP);
            cell2.removeWall(Cell.Wall.BOTTOM);
        } else if (rowDiff == 1) {
            cell1.removeWall(Cell.Wall.BOTTOM);
            cell2.removeWall(Cell.Wall.TOP);
        } else if (colDiff == -1) {
            cell1.removeWall(Cell.Wall.LEFT);
            cell2.removeWall(Cell.Wall.RIGHT);
        } else if (colDiff == 1) {
            cell1.removeWall(Cell.Wall.RIGHT);
            cell2.removeWall(Cell.Wall.LEFT);
        }
    }
}
