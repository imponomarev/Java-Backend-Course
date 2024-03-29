package edu.hw9.task3;

public class Cell {

    private int row;

    private int col;

    private int walls;

    private static final int FULL = 15;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.walls = FULL;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setWalls(int walls) {
        this.walls = walls;
    }

    public void removeWall(Wall wall) {
        walls &= ~wall.getValue();
    }

    public boolean hasWall(Wall wall) {
        return (walls & wall.getValue()) != 0;
    }

    public enum Wall {
        TOP(1),
        BOTTOM(2),
        LEFT(4),
        RIGHT(8);

        private final int value;

        Wall(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
