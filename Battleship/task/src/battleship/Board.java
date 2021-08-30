package battleship;

public class Board {

    private char[][] board;

    public Board() {
        this.board = initEmptyBoard();
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    private char[][] initEmptyBoard() {
        char[][] board = new char[10][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '~';
            }
        }
        return board;
    }

    public void printBoard(char[][] board) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (j == 0) {
                    System.out.print((char) (64 + i) + " ");
                } else {
                    System.out.print(board[i - 1][j - 1] + " ");
                }
            }
            System.out.println();
        }
    }
}
