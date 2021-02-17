
package tictactoe;
import java.util.Scanner;
public class Main {
    public static void printMatrix(char[][] matrix) {
        System.out.println("---------");
        for (char[] row : matrix) {
            System.out.print("| ");
            for (char column : row) System.out.print(column + " ");
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] matrix = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = ' ';
            }
        }
        printMatrix(matrix);
        char player = 'X';
        int x, y;
        boolean gameFinished = false;
        boolean coordinates = false;
        while (!gameFinished) {
            do {
                System.out.print("Enter the coordinates: ");
                try {
                    x = sc.nextInt();
                    y = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                    coordinates = false;
                    sc.nextLine();
                    continue;
                }
                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    coordinates = false;
                    continue;
                }
                if (matrix[x - 1][y - 1] == 'X' || matrix[x - 1][y - 1] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                    coordinates = false;
                    continue;
                }
                coordinates = true;
                matrix[x - 1][y - 1] = player;
                if (player == 'X') player = 'O';
                else if (player == 'O') player = 'X';
                printMatrix(matrix);
                gameFinished = checkStatus(matrix);
            } while (!coordinates);
        }
    }

    public static boolean checkStatus(char[][] matrix) {
        boolean xwins = false;
        boolean owins = false;
        for (int i = 0; i < 3; i++) {
            if (matrix[i][0] == matrix[i][1] && matrix[i][0] == matrix[i][2]) {
                switch (matrix[i][0]) {
                    case 'X':
                        xwins = true;
                        break;
                    case 'O':
                        owins = true;
                        break;
                }
            }
            if (matrix[0][i] == matrix[1][i] && matrix[0][i] == matrix[2][i]) {
                switch (matrix[0][i]) {
                    case 'X':
                        xwins = true;
                        break;
                    case 'O':
                        owins = true;
                        break;
                }
            }
        }
        if (matrix[0][0] == matrix[1][1] && matrix[1][1] == matrix[2][2]) {
            switch (matrix[0][0]) {
                case 'X':
                    xwins = true;
                    break;
                case 'O':
                    owins = true;
                    break;
            }
        }
        if (matrix[0][2] == matrix[1][1] && matrix[1][1] == matrix[2][0]) {
            switch (matrix[2][0]) {
                case 'X':
                    xwins = true;
                    break;
                case 'O':
                    owins = true;
                    break;
            }
        }
        int xs = 0, os = 0;
        boolean empty = false;
        for (char[] row : matrix) {
            for (char column : row) {
                if (column == 'X') xs++;
                else if (column == 'O') os++;
                else empty = true;
            }

        }
        if (xwins && !owins) {
            System.out.println("X wins");
            return true;
        } else if (!xwins && owins) {
            System.out.println("O wins");
            return true;
        } else if (!empty && !xwins && !owins) {
            System.out.println("Draw");
            return true;
        }else return false;
    }
}
