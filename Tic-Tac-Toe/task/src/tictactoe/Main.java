package tictactoe;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static char[][] field = new char[][]{
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}};
    static char player = 'X';
    static char previousMove = 'O';
    static int[] nums = new int[2];

    public static void main(String[] args) {
        // write your code here
        printField();
        while (true) {
            player = previousMove == 'O' ? 'X' : 'O';
            turn();
            printField();

            if (checkWin(player)) {
                System.out.println(player + " wins");
                break;
            }
            if (isTableFull()) {
                System.out.println("Draw");
                break;
            }

            previousMove = player;
        }
    }

    /**
     * Field
     **/

    private static void printField() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static void turn() {
        while (true) {
            if (updateField(inputCoordinates())) break;
        }
    }

    private static boolean updateField(String coordinates) {

        String[] temp = coordinates.split(" ");

        for (String str : temp) {
            if (!isNumeric(str)) {
                System.out.println("You should enter numbers!");
                return false;
            }
        }

        for (int k = 0; k < 2; k++) {
            int parsed = Integer.parseInt(temp[k]) - 1;
            if (isInBounds(parsed)) {
                nums[k] = parsed;
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
        }

        if (isCellEmpty(field[nums[0]][nums[1]])) {
            field[nums[0]][nums[1]] = player;

        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    /**
     * Input
     **/

    private static String inputCoordinates() {
        System.out.print("Enter the coordinates: ");
        return inputData();
    }

    private static String inputCells() {
        System.out.print("Enter cells: ");
        return inputData();
    }

    private static String inputData() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Checks
     **/


    private static boolean isInBounds(int a) {
        return a >= 0 && a < 3;
    }

    private static boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    private static boolean isCellEmpty(char cell) {
        return (cell == '_');
    }

    private static boolean checkWin(char dot) {
        for (int i = 0; i < 3; i++)
            if ((field[i][0] == dot && field[i][1] == dot &&
                    field[i][2] == dot) ||
                    (field[0][i] == dot && field[1][i] == dot &&
                            field[2][i] == dot)) {
                return true;
            }

        if ((field[0][0] == dot && field[1][1] == dot &&
                field[2][2] == dot) ||
                (field[2][0] == dot && field[1][1] == dot &&
                        field[0][2] == dot)) {
            return true;
        }
        return false;
    }

    private static boolean isTableFull() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (field[row][col] == '_')
                    return false;
        return true;
    }
}
