import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    private final char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("-----");
        }
    }

    public void playerMove() {
        Scanner sc = new Scanner(System.in);
        int row, col;
        while (true) {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            row = sc.nextInt();
            col = sc.nextInt();
            if (isMoveValid(row, col)) {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("This move is not valid");
            }
        }
    }

    public void computerMove() {
        Random rand = new Random();
        int row, col;
        while (true) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
            if (isMoveValid(row, col)) {
                board[row][col] = currentPlayer;
                System.out.println("Computer placed " + currentPlayer + " in position (" + row + ", " + col + ")");
                break;
            }
        }
    }

    private boolean isMoveValid(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    public boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
}

public class Tic_Tac_Toe {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        while (true) {
            game.printBoard();
            if (game.getCurrentPlayer() == 'X') {
                game.playerMove();
            } else {
                game.computerMove();
            }
            if (game.checkWinner()) {
                game.printBoard();
                System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                break;
            }
            if (game.isBoardFull()) {
                game.printBoard();
                System.out.println("The game is a tie!");
                break;
            }
            game.switchPlayer();
        }
    }
}
