package HW4;


import java.util.Random;
import java.util.Scanner;

public class HW4 {
    static final int SIZE = 3; //константы принято заглавными буквами
//    static final int DOT_TO_WIN = 3;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWinColsRows(DOT_X)) {
                System.out.println("Победа!");
                break;
            }
            if (checkWinDiagonal(DOT_X)) {
                System.out.println("Победа!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (checkWinColsRows(DOT_O)) {
                System.out.println("Победа за компьютером :(");
                break;
            }
            if (checkWinDiagonal(DOT_O)) {
                System.out.println("Победа за компьютером :(!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }


    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("input X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));


        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int y, int x) {
        if (y < 0 || x < 0 || y >= SIZE || x >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x;
        int y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;
    }


    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }

            }

        }
        return true;
    }

    //1 задание
    public static boolean checkWinColsRows(char c) {
        boolean cols, rows;
        for (int i = 0; i < SIZE; i++) {
            cols = true;
            rows = true;
            for (int j = 0; j < SIZE; j++) {
                cols &= (map[i][j] == c);
                rows &= (map[j][i] == c);
            }
            if (cols || rows) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkWinDiagonal(char c) {
        boolean toright, toleft;

        toright = true;
        toleft = true;
        for (int i = 0; i < SIZE; i++) {
            toright &= (map[i][i] == c);
            toleft &= (map[SIZE - i - 1][i] == c);
        }
        if (toright || toleft) {
            return true;
        }
        return false;
    }


    ////        if (map[0][0] == c && map[0][1] == c && map[0][2] == c) {return true;}
////        if (map[1][0] == c && map[1][1] == c && map[1][2] == c) {return true;}
////        if (map[2][0] == c && map[2][1] == c && map[2][2] == c) {return true;}
////
////        if (map[0][0] == c && map[1][0] == c && map[2][0] == c) {return true;}
////        if (map[0][1] == c && map[1][1] == c && map[2][1] == c) {return true;}
////        if (map[0][2] == c && map[1][2] == c && map[2][2] == c) {return true;}
////
////        if (map[0][0] == c && map[1][1] == c && map[2][2] == c) {return true;}
////        if (map[0][2] == c && map[1][1] == c && map[2][0] == c) {return true;}
//
//        return false;
//    }
}
