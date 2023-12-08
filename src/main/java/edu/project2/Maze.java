package edu.project2;

public class Maze {
    private int height;

    private int width;

    private Cell[][] cells;


    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[height][width];
        initializeCells();
    }

    private void initializeCells() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
