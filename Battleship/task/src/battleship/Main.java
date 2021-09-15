package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board playerOneBoard1 = new Board();
        Board playerOneBoard2 = new Board();
        Board playerTwoBoard1 = new Board();
        Board playerTwoBoard2 = new Board();
        Ships playerOneShips = new Ships();
        Ships playerTwoShips = new Ships();
        char[][] playerOneRealBoard = playerOneBoard1.getBoard();
        char[][] playerOneFogOfWar = playerOneBoard2.getBoard();
        char[][] playerTwoRealBoard = playerTwoBoard1.getBoard();
        char[][] playerTwoFogOfWar = playerTwoBoard2.getBoard();
        boolean isGameFinished;

        System.out.println("Player 1, place your ships on the game field\n");
        playerOneBoard1.printBoard(playerOneRealBoard);

        System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):\n");
        playerOneShips.addAircraftCarrier(playerOneRealBoard);
        playerOneBoard1.printBoard(playerOneRealBoard);

        System.out.println("\nEnter the coordinates of the Battleship (4 cells):\n");
        playerOneShips.addBattleship(playerOneRealBoard);
        playerOneBoard1.printBoard(playerOneRealBoard);

        System.out.println("\nEnter the coordinates of the Submarine (3 cells):\n");
        playerOneShips.addSubmarine(playerOneRealBoard);
        playerOneBoard1.printBoard(playerOneRealBoard);

        System.out.println("\nEnter the coordinates of the Cruiser (3 cells):\n");
        playerOneShips.addCruiser(playerOneRealBoard);
        playerOneBoard1.printBoard(playerOneRealBoard);

        System.out.println("\nEnter the coordinates of the Destroyer (2 cells):\n");
        playerOneShips.addDestroyer(playerOneRealBoard);
        playerOneBoard1.printBoard(playerOneRealBoard);
        System.out.println();

        promptEnterKey();

        System.out.println("Player 2, place your ships to the game field\n");
        playerTwoBoard1.printBoard(playerTwoRealBoard);

        System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):\n");
        playerTwoShips.addAircraftCarrier(playerTwoRealBoard);
        playerTwoBoard1.printBoard(playerTwoRealBoard);

        System.out.println("\nEnter the coordinates of the Battleship (4 cells):\n");
        playerTwoShips.addBattleship(playerTwoRealBoard);
        playerTwoBoard1.printBoard(playerTwoRealBoard);

        System.out.println("\nEnter the coordinates of the Submarine (3 cells):\n");
        playerTwoShips.addSubmarine(playerTwoRealBoard);
        playerTwoBoard1.printBoard(playerTwoRealBoard);

        System.out.println("\nEnter the coordinates of the Cruiser (3 cells):\n");
        playerTwoShips.addCruiser(playerTwoRealBoard);
        playerTwoBoard1.printBoard(playerTwoRealBoard);

        System.out.println("\nEnter the coordinates of the Destroyer (2 cells):\n");
        playerTwoShips.addDestroyer(playerTwoRealBoard);
        playerTwoBoard1.printBoard(playerTwoRealBoard);
        System.out.println();

        while (true) {
            promptEnterKey();
            playerTwoBoard2.printBoard(playerTwoFogOfWar);
            System.out.println("---------------------");
            playerOneBoard1.printBoard(playerOneRealBoard);
            System.out.println("\nPlayer 1, it's your turn:\n");
            playerTwoShips.shot(playerTwoRealBoard, playerTwoFogOfWar);
            isGameFinished = playerTwoShips.isGameFinished();
            if (isGameFinished) {
                break;
            }
            promptEnterKey();
            playerOneBoard2.printBoard(playerOneFogOfWar);
            System.out.println("---------------------");
            playerTwoBoard1.printBoard(playerTwoRealBoard);
            System.out.println("\nPlayer 2, it's your turn:\n");
            playerOneShips.shot(playerOneRealBoard, playerOneFogOfWar);
            isGameFinished = playerOneShips.isGameFinished();
            if (isGameFinished) {
                break;
            }
        }
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
