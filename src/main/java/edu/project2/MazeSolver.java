package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class MazeSolver implements Solver {

    private final Maze maze;

    private final boolean[][] visited;

    private final Deque<Cell> deque;

    private final List<Cell> solution;

    Random random;

    private static final String NO_SOLUTION = "No solution found!";

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.visited = new boolean[maze.getHeight()][maze.getWidth()];
        this.deque = new ArrayDeque<>();
        this.solution = new ArrayList<>();
        this.random = new Random();
    }

    @Override
    public int[][] solve(Coordinates start, Coordinates end) {

        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();

        if (startX < 0 || startX >= maze.getHeight() || startY < 0 || startY >= maze.getWidth()
            || endX < 0 || endX >= maze.getHeight() || endY < 0 || endY >= maze.getWidth()) {
            throw new GeneratorException("Coordinates have to be positive numbers!");
        }

        Cell initCell = maze.getCell(startX, startY);
        markVisited(initCell);
        deque.push(initCell);

        while (!deque.isEmpty()) {
            Cell curr = deque.pop();

            if (curr.getRow() == endX && curr.getCol() == endY) {
                solution.add(curr);
                return convertSolutionToArray();
            }

            List<Cell> unvisitedNeighbours = getUnvisitedNeighboursOfCell(curr);

            if (!unvisitedNeighbours.isEmpty()) {
                solution.add(curr);
                deque.push(curr);
                Cell choice = chooseRandomlyNeighbour(unvisitedNeighbours);
                markVisited(choice);
                deque.push(choice);
            } else {
                if (solution.isEmpty()) {
                    throw new SolverException(NO_SOLUTION);
                }
                solution.remove(solution.size() - 1);
            }
        }

        throw new SolverException(NO_SOLUTION);
    }


    private void markVisited(Cell cell) {
        visited[cell.getRow()][cell.getCol()] = true;
    }

    private List<Cell> getUnvisitedNeighboursOfCell(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();

        if (cell.getRow() > 0
            && !visited[cell.getRow() - 1][cell.getCol()] && !cell.hasWall(Cell.Wall.TOP)) {

            neighbours.add(maze.getCell(cell.getRow() - 1, cell.getCol()));

        }
        if (cell.getRow() < maze.getHeight() - 1
            && !visited[cell.getRow() + 1][cell.getCol()] && !cell.hasWall(Cell.Wall.BOTTOM)) {

            neighbours.add(maze.getCell(cell.getRow() + 1, cell.getCol()));

        }
        if (cell.getCol() > 0
            && !visited[cell.getRow()][cell.getCol() - 1] && !cell.hasWall(Cell.Wall.LEFT)) {

            neighbours.add(maze.getCell(cell.getRow(), cell.getCol() - 1));

        }
        if (cell.getCol() < maze.getWidth() - 1
            && !visited[cell.getRow()][cell.getCol() + 1] && !cell.hasWall(Cell.Wall.RIGHT)) {

            neighbours.add(maze.getCell(cell.getRow(), cell.getCol() + 1));
            
        }

        return neighbours;
    }

    private Cell chooseRandomlyNeighbour(List<Cell> neighbours) {
        int index = random.nextInt(neighbours.size());
        return neighbours.get(index);
    }

    private int[][] convertSolutionToArray() {
        int[][] solutionArray = new int[solution.size()][2];
        for (int i = 0; i < solution.size(); i++) {
            solutionArray[i][0] = solution.get(i).getRow();
            solutionArray[i][1] = solution.get(i).getCol();
        }
        return solutionArray;
    }
}
