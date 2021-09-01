package battleship;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Ships {

    private final HashMap<Character, Integer> MAP = initMap();
    private final int AIRCRAFT_CARRIER_LENGTH = 5;
    private final int BATTLESHIP_LENGTH = 4;
    private final int SUBMARINE_LENGTH = 3;
    private final int CRUISER_LENGTH = 3;
    private final int DESTROYER_LENGTH = 2;

    private HashMap<Character, Integer> initMap() {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('B', 1);
        map.put('C', 2);
        map.put('D', 3);
        map.put('E', 4);
        map.put('F', 5);
        map.put('G', 6);
        map.put('H', 7);
        map.put('I', 8);
        map.put('J', 9);
        return map;
    }

    public void shot(char[][] board) {
        Board board1 = new Board();
        Scanner scanner = new Scanner(System.in);
        int row = 0;
        int column = 0;
        StringBuilder stringBuilder;
        boolean hit = false;
        boolean wrongCoordinate = true;


        while (wrongCoordinate) {
            stringBuilder = new StringBuilder();
            String coordinate = scanner.nextLine().toUpperCase();
            Set<Map.Entry<Character, Integer>> entrySet = MAP.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                if (coordinate.charAt(0) == entry.getKey()) {
                    row = entry.getValue();
                }
            }

            for (int i = 1; i < coordinate.length(); i++) {
                stringBuilder.append(coordinate.charAt(i));
            }
            column = Integer.parseInt(String.valueOf(stringBuilder)) - 1;

            if (coordinate.charAt(0) != 'A' &&
                    coordinate.charAt(0) != 'B' &&
                    coordinate.charAt(0) != 'C' &&
                    coordinate.charAt(0) != 'D' &&
                    coordinate.charAt(0) != 'E' &&
                    coordinate.charAt(0) != 'F' &&
                    coordinate.charAt(0) != 'G' &&
                    coordinate.charAt(0) != 'H' &&
                    coordinate.charAt(0) != 'I' &&
                    coordinate.charAt(0) != 'J') {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            } else if (column < 0 || column > 9) {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            } else {
                wrongCoordinate = false;
            }
        }

        if (board[row][column] == 'O') {
            board[row][column] = 'X';
            hit = true;
        } else if (board[row][column] == '~') {
            board[row][column] = 'M';
        }

        System.out.println();
        board1.printBoard(board);

        if (hit) {
            System.out.println("\nYou hit a ship!\n");
        } else {
            System.out.println("\nYou missed!\n");
        }
    }

    public void addAircraftCarrier(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        String coordinateX = "";
        String coordinateY;
        int rowX = 0;
        int columnX;
        int rowY = 0;
        int columnY;
        boolean horizontally;
        int count;
        StringBuilder stringBuilder;
        boolean tooClose = false;
        boolean shipAdded = false;

        while (!shipAdded) {
            count = 0;
            stringBuilder = new StringBuilder();
            String coordinates = scanner.nextLine().toUpperCase();

            // get coordinate X and Y as string

            for (int i = 0; i < coordinates.length(); i++) {
                if (!Character.isWhitespace(coordinates.charAt(i))) {
                    stringBuilder.append(coordinates.charAt(i));
                } else {
                    coordinateX = String.valueOf(stringBuilder);
                    stringBuilder = new StringBuilder();
                }
            }
            coordinateY = String.valueOf(stringBuilder);
            stringBuilder = new StringBuilder();

            // get rows as int

            Set<Map.Entry<Character, Integer>> entrySet = MAP.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                if (coordinateX.charAt(0) == entry.getKey()) {
                    rowX = entry.getValue();
                }
                if (coordinateY.charAt(0) == entry.getKey()) {
                    rowY = entry.getValue();
                }
            }
            horizontally = rowX == rowY;

            // get columns as int

            for (int i = 1; i < coordinateX.length(); i++) {
                stringBuilder.append(coordinateX.charAt(i));
            }
            columnX = Integer.parseInt(String.valueOf(stringBuilder)) - 1;
            stringBuilder = new StringBuilder();
            for (int i = 1; i < coordinateY.length(); i++) {
                stringBuilder.append(coordinateY.charAt(i));
            }
            columnY = Integer.parseInt(String.valueOf(stringBuilder)) - 1;

            // check length of ship is correct
            // and there is no another ship too close

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (horizontally) {
                        if (rowX == i) {
                            if (columnX < columnY) {
                                if (columnX == j) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        j++;
                                        if (j > columnY) {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (columnY == j) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        j++;
                                        if (j > columnX) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (columnX == j) {
                            if (rowX < rowY) {
                                if (rowX == i) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        i++;
                                        if (i > rowY) {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (rowY == i) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        i++;
                                        if (i > rowX) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // add ship to a board or return error

            if (rowX != rowY && columnX != columnY) {
                System.out.println("\nError! Wrong ship location! Try again:\n");
            } else if (tooClose) {
                System.out.println("\nError! You placed it too close to another one. Try again:\n");
            } else if (count == AIRCRAFT_CARRIER_LENGTH) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (horizontally) {
                            if (rowX == i) {
                                if (columnX < columnY) {
                                    if (columnX == j) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            j++;
                                            if (j > columnY) {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    if (columnY == j) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            j++;
                                            if (j > columnX) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (columnX == j) {
                                if (rowX < rowY) {
                                    if (rowX == i) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            i++;
                                            if (i > rowY) {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    if (rowY == i) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            i++;
                                            if (i > rowX) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                shipAdded = true;
            } else {
                System.out.println("\nError! Wrong length of the Aircraft Carrier! Try again:\n");
            }
        }
    }

    public void addBattleship(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        String coordinateX = "";
        String coordinateY;
        int rowX = 0;
        int columnX;
        int rowY = 0;
        int columnY;
        boolean horizontally;
        int count;
        StringBuilder stringBuilder;
        boolean tooClose;
        boolean shipAdded = false;

        while (!shipAdded) {
            count = 0;
            stringBuilder = new StringBuilder();
            tooClose = false;
            String coordinates = scanner.nextLine().toUpperCase();

            // get coordinate X and Y as string

            for (int i = 0; i < coordinates.length(); i++) {
                if (!Character.isWhitespace(coordinates.charAt(i))) {
                    stringBuilder.append(coordinates.charAt(i));
                } else {
                    coordinateX = String.valueOf(stringBuilder);
                    stringBuilder = new StringBuilder();
                }
            }
            coordinateY = String.valueOf(stringBuilder);
            stringBuilder = new StringBuilder();

            // get rows as int

            Set<Map.Entry<Character, Integer>> entrySet = MAP.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                if (coordinateX.charAt(0) == entry.getKey()) {
                    rowX = entry.getValue();
                }
                if (coordinateY.charAt(0) == entry.getKey()) {
                    rowY = entry.getValue();
                }
            }
            horizontally = rowX == rowY;

            // get columns as int

            for (int i = 1; i < coordinateX.length(); i++) {
                stringBuilder.append(coordinateX.charAt(i));
            }
            columnX = Integer.parseInt(String.valueOf(stringBuilder)) - 1;
            stringBuilder = new StringBuilder();
            for (int i = 1; i < coordinateY.length(); i++) {
                stringBuilder.append(coordinateY.charAt(i));
            }
            columnY = Integer.parseInt(String.valueOf(stringBuilder)) - 1;

            // check length of ship is correct
            // and there is no another ship too close

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (horizontally) {
                        if (rowX == i) {
                            if (columnX < columnY) {
                                if (columnX == j) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        j++;
                                        if (j > columnY) {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (columnY == j) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        j++;
                                        if (j > columnX) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (columnX == j) {
                            if (rowX < rowY) {
                                if (rowX == i) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        i++;
                                        if (i > rowY) {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (rowY == i) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        i++;
                                        if (i > rowX) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // add ship to a board or return error

            if (rowX != rowY && columnX != columnY) {
                System.out.println("\nError! Wrong ship location! Try again:\n");
            } else if (tooClose) {
                System.out.println("\nError! You placed it too close to another one. Try again:\n");
            } else if (count == BATTLESHIP_LENGTH) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (horizontally) {
                            if (rowX == i) {
                                if (columnX < columnY) {
                                    if (columnX == j) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            j++;
                                            if (j > columnY) {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    if (columnY == j) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            j++;
                                            if (j > columnX) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (columnX == j) {
                                if (rowX < rowY) {
                                    if (rowX == i) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            i++;
                                            if (i > rowY) {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    if (rowY == i) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            i++;
                                            if (i > rowX) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                shipAdded = true;
            } else {
                System.out.println("\nError! Wrong length of the Battleship! Try again:\n");
            }
        }
    }

    public void addSubmarine(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        String coordinateX = "";
        String coordinateY;
        int rowX = 0;
        int columnX;
        int rowY = 0;
        int columnY;
        boolean horizontally;
        int count;
        StringBuilder stringBuilder;
        boolean tooClose;
        boolean shipAdded = false;

        while (!shipAdded) {
            count = 0;
            stringBuilder = new StringBuilder();
            tooClose = false;
            String coordinates = scanner.nextLine().toUpperCase();

            // get coordinate X and Y as string

            for (int i = 0; i < coordinates.length(); i++) {
                if (!Character.isWhitespace(coordinates.charAt(i))) {
                    stringBuilder.append(coordinates.charAt(i));
                } else {
                    coordinateX = String.valueOf(stringBuilder);
                    stringBuilder = new StringBuilder();
                }
            }
            coordinateY = String.valueOf(stringBuilder);
            stringBuilder = new StringBuilder();

            // get rows as int

            Set<Map.Entry<Character, Integer>> entrySet = MAP.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                if (coordinateX.charAt(0) == entry.getKey()) {
                    rowX = entry.getValue();
                }
                if (coordinateY.charAt(0) == entry.getKey()) {
                    rowY = entry.getValue();
                }
            }
            horizontally = rowX == rowY;

            // get columns as int

            for (int i = 1; i < coordinateX.length(); i++) {
                stringBuilder.append(coordinateX.charAt(i));
            }
            columnX = Integer.parseInt(String.valueOf(stringBuilder)) - 1;
            stringBuilder = new StringBuilder();
            for (int i = 1; i < coordinateY.length(); i++) {
                stringBuilder.append(coordinateY.charAt(i));
            }
            columnY = Integer.parseInt(String.valueOf(stringBuilder)) - 1;

            // check length of ship is correct
            // and there is no another ship too close

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (horizontally) {
                        if (rowX == i) {
                            if (columnX < columnY) {
                                if (columnX == j) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        j++;
                                        if (j > columnY) {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (columnY == j) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        j++;
                                        if (j > columnX) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (columnX == j) {
                            if (rowX < rowY) {
                                if (rowX == i) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        i++;
                                        if (i > rowY) {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (rowY == i) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        i++;
                                        if (i > rowX) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // add ship to a board or return error

            if (rowX != rowY && columnX != columnY) {
                System.out.println("\nError! Wrong ship location! Try again:\n");
            } else if (tooClose) {
                System.out.println("\nError! You placed it too close to another one. Try again:\n");
            } else if (count == SUBMARINE_LENGTH) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (horizontally) {
                            if (rowX == i) {
                                if (columnX < columnY) {
                                    if (columnX == j) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            j++;
                                            if (j > columnY) {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    if (columnY == j) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            j++;
                                            if (j > columnX) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (columnX == j) {
                                if (rowX < rowY) {
                                    if (rowX == i) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            i++;
                                            if (i > rowY) {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    if (rowY == i) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            i++;
                                            if (i > rowX) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                shipAdded = true;
            } else {
                System.out.println("\nError! Wrong length of the Submarine! Try again:\n");
            }
        }
    }

    public void addCruiser(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        String coordinateX = "";
        String coordinateY;
        int rowX = 0;
        int columnX;
        int rowY = 0;
        int columnY;
        boolean horizontally;
        int count;
        StringBuilder stringBuilder;
        boolean tooClose;
        boolean shipAdded = false;

        while (!shipAdded) {
            count = 0;
            stringBuilder = new StringBuilder();
            tooClose = false;
            String coordinates = scanner.nextLine().toUpperCase();

            // get coordinate X and Y as string

            for (int i = 0; i < coordinates.length(); i++) {
                if (!Character.isWhitespace(coordinates.charAt(i))) {
                    stringBuilder.append(coordinates.charAt(i));
                } else {
                    coordinateX = String.valueOf(stringBuilder);
                    stringBuilder = new StringBuilder();
                }
            }
            coordinateY = String.valueOf(stringBuilder);
            stringBuilder = new StringBuilder();

            // get rows as int

            Set<Map.Entry<Character, Integer>> entrySet = MAP.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                if (coordinateX.charAt(0) == entry.getKey()) {
                    rowX = entry.getValue();
                }
                if (coordinateY.charAt(0) == entry.getKey()) {
                    rowY = entry.getValue();
                }
            }
            horizontally = rowX == rowY;

            // get columns as int

            for (int i = 1; i < coordinateX.length(); i++) {
                stringBuilder.append(coordinateX.charAt(i));
            }
            columnX = Integer.parseInt(String.valueOf(stringBuilder)) - 1;
            stringBuilder = new StringBuilder();
            for (int i = 1; i < coordinateY.length(); i++) {
                stringBuilder.append(coordinateY.charAt(i));
            }
            columnY = Integer.parseInt(String.valueOf(stringBuilder)) - 1;

            // check length of ship is correct
            // and there is no another ship too close

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (horizontally) {
                        if (rowX == i) {
                            if (columnX < columnY) {
                                if (columnX == j) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        j++;
                                        if (j > columnY) {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (columnY == j) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        j++;
                                        if (j > columnX) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (columnX == j) {
                            if (rowX < rowY) {
                                if (rowX == i) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        i++;
                                        if (i > rowY) {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (rowY == i) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        i++;
                                        if (i > rowX) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // add ship to a board or return error

            if (rowX != rowY && columnX != columnY) {
                System.out.println("\nError! Wrong ship location! Try again:\n");
            } else if (tooClose) {
                System.out.println("\nError! You placed it too close to another one. Try again:\n");
            } else if (count == CRUISER_LENGTH) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (horizontally) {
                            if (rowX == i) {
                                if (columnX < columnY) {
                                    if (columnX == j) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            j++;
                                            if (j > columnY) {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    if (columnY == j) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            j++;
                                            if (j > columnX) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (columnX == j) {
                                if (rowX < rowY) {
                                    if (rowX == i) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            i++;
                                            if (i > rowY) {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    if (rowY == i) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            i++;
                                            if (i > rowX) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                shipAdded = true;
            } else {
                System.out.println("\nError! Wrong length of the Cruiser! Try again:\n");
            }
        }
    }

    public void addDestroyer(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        String coordinateX = "";
        String coordinateY;
        int rowX = 0;
        int columnX;
        int rowY = 0;
        int columnY;
        boolean horizontally;
        int count;
        StringBuilder stringBuilder;
        boolean tooClose;
        boolean shipAdded = false;

        while (!shipAdded) {
            count = 0;
            stringBuilder = new StringBuilder();
            tooClose = false;
            String coordinates = scanner.nextLine().toUpperCase();

            // get coordinate X and Y as string

            for (int i = 0; i < coordinates.length(); i++) {
                if (!Character.isWhitespace(coordinates.charAt(i))) {
                    stringBuilder.append(coordinates.charAt(i));
                } else {
                    coordinateX = String.valueOf(stringBuilder);
                    stringBuilder = new StringBuilder();
                }
            }
            coordinateY = String.valueOf(stringBuilder);
            stringBuilder = new StringBuilder();

            // get rows as int

            Set<Map.Entry<Character, Integer>> entrySet = MAP.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                if (coordinateX.charAt(0) == entry.getKey()) {
                    rowX = entry.getValue();
                }
                if (coordinateY.charAt(0) == entry.getKey()) {
                    rowY = entry.getValue();
                }
            }
            horizontally = rowX == rowY;

            // get columns as int

            for (int i = 1; i < coordinateX.length(); i++) {
                stringBuilder.append(coordinateX.charAt(i));
            }
            columnX = Integer.parseInt(String.valueOf(stringBuilder)) - 1;
            stringBuilder = new StringBuilder();
            for (int i = 1; i < coordinateY.length(); i++) {
                stringBuilder.append(coordinateY.charAt(i));
            }
            columnY = Integer.parseInt(String.valueOf(stringBuilder)) - 1;

            // check length of ship is correct
            // and there is no another ship too close

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (horizontally) {
                        if (rowX == i) {
                            if (columnX < columnY) {
                                if (columnX == j) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        j++;
                                        if (j > columnY) {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (columnY == j) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        j++;
                                        if (j > columnX) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (columnX == j) {
                            if (rowX < rowY) {
                                if (rowX == i) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        i++;
                                        if (i > rowY) {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (rowY == i) {
                                    while (true) {
                                        count++;
                                        if (i == 0 && j == 0) {
                                            if (board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0 && j == 9) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9 && j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 0) {
                                            if (board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (i == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 0) {
                                            if (board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        } else if (j == 9) {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O') {
                                                tooClose = true;
                                            }
                                        } else {
                                            if (board[i - 1][j - 1] == 'O' ||
                                                    board[i - 1][j] == 'O' ||
                                                    board[i - 1][j + 1] == 'O' ||
                                                    board[i][j - 1] == 'O' ||
                                                    board[i][j] == 'O' ||
                                                    board[i][j + 1] == 'O' ||
                                                    board[i + 1][j - 1] == 'O' ||
                                                    board[i + 1][j] == 'O' ||
                                                    board[i + 1][j + 1] == 'O') {
                                                tooClose = true;
                                            }
                                        }
                                        i++;
                                        if (i > rowX) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // add ship to a board or return error

            if (rowX != rowY && columnX != columnY) {
                System.out.println("\nError! Wrong ship location! Try again:\n");
            } else if (tooClose) {
                System.out.println("\nError! You placed it too close to another one. Try again:\n");
            } else if (count == DESTROYER_LENGTH) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (horizontally) {
                            if (rowX == i) {
                                if (columnX < columnY) {
                                    if (columnX == j) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            j++;
                                            if (j > columnY) {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    if (columnY == j) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            j++;
                                            if (j > columnX) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (columnX == j) {
                                if (rowX < rowY) {
                                    if (rowX == i) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            i++;
                                            if (i > rowY) {
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    if (rowY == i) {
                                        while (true) {
                                            board[i][j] = 'O';
                                            i++;
                                            if (i > rowX) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                shipAdded = true;
            } else {
                System.out.println("\nError! Wrong length of the Destroyer! Try again:\n");
            }
        }
    }
}
