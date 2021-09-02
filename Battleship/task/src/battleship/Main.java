package battleship;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Board board1 = new Board();
        Ships ships = new Ships();
        char[][] temp = board.getBoard();
        char[][] fogOfWar = board1.getBoard();

        board.printBoard(temp);

        System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):\n");
        ships.addAircraftCarrier(temp);
        board.printBoard(temp);

        System.out.println("\nEnter the coordinates of the Battleship (4 cells):\n");
        ships.addBattleship(temp);
        board.printBoard(temp);

        System.out.println("\nEnter the coordinates of the Submarine (3 cells):\n");
        ships.addSubmarine(temp);
        board.printBoard(temp);

        System.out.println("\nEnter the coordinates of the Cruiser (3 cells):\n");
        ships.addCruiser(temp);
        board.printBoard(temp);

        System.out.println("\nEnter the coordinates of the Destroyer (2 cells):\n");
        ships.addDestroyer(temp);
        board.printBoard(temp);

        System.out.println("\nThe game starts!\n");
        board.printBoard(fogOfWar);
        System.out.println("\nTake a shot!\n");
        ships.shot(temp, fogOfWar);
    }
}
