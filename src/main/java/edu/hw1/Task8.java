package edu.hw1;

public class Task8 {

    static final int TWO = 2;

    public boolean knightBoardCapture(int[][] board) {
        int[][] moves = {{-TWO, -1}, {-TWO, 1}, {-1, -TWO}, {-1, TWO}, {1, -TWO}, {1, TWO}, {TWO, -1}, {TWO, 1}};

        int size = board.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 1) {
                    for (int[] move : moves) {
                        int row = i + move[0];
                        int col = j + move[1];
                        if (isValidMove(row, col, size) && board[row][col] == 1) {
                            return false;

                        }
                    }
                }
            }
        }
        return true;

    }

    public boolean isValidMove(int row, int col, int size) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
}
