package ru.geekbrains.DZ;

import java.util.Random;
import java.util.Scanner;

public class Cross {

    static final int SIZE_X = 5;
    static final int SIZE_Y = 5;

    static char[][] field = new char[SIZE_X][SIZE_Y];
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static final char PLAYER_DOT = 'X';
    static final char AI_DOT = 'O';
    static final char EMPTY_DOT = '.';

    static void initField() {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    static void printField() {
        for (int i = 0; i < SIZE_X; i++) {
            if (i == 0) {
                System.out.print("   " + (i + 1) + " ");
            } else {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();
        for (int j = 0; j < SIZE_Y; j++) {
            System.out.print((j + 1) + " |");
            for (int i = 0; i < SIZE_X; i++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
    }


    private static boolean valid(int x, int y) {
        if (x < 0 || y < 0 || x > SIZE_X - 1 || y > SIZE_Y - 1) {
            return false;
        }
        return field[x][y] == EMPTY_DOT;
    }

    static void playerStep() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты X и Y:");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!valid(x, y));
        setSymbol(x, y, PLAYER_DOT);
    }

    static void aiStep() {
        int x;
        int y;
        do {
            x = random.nextInt(SIZE_X);
            y = random.nextInt(SIZE_Y);
        }
        while (!valid(x, y));
        setSymbol(x, y, AI_DOT);
    }

    private static void setSymbol(int x, int y, char symbol) {
        field[x][y] = symbol;
    }

    static boolean isFieldsFull() {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    static boolean checkWin(char symbol) {
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                if (x < 2 && field[x][y] == symbol && field[x+1][y] == symbol && field[x+2][y] == symbol && field[x+3][y] == symbol)
                    return true;
                if (y < 2 && field[x][y] == symbol && field[x][y+1] == symbol && field[x][y+2] == symbol && field[x][y+3] == symbol)
                    return true;
               if (x < 2 && y < 2 && field[x][y] == symbol && field[x+1][y+1] == symbol && field[x+2][y+2] == symbol && field[x+3][y+3] == symbol)
                  return true;
               if (x < 2 && y < 2 && field[x][y+3] == symbol && field[x+1][y+2] == symbol && field[x+2][y+1] == symbol && field[x+3][y] == symbol)
                   return true;
               if (x < 2 && y < 1 && field[x][y+4] == symbol && field[x+1][y+3] == symbol && field[x+2][y+2] == symbol && field[x+3][y+1] == symbol)
                   return true;
               if (x < 1 && y < 2 && field[x+1][y+3] == symbol && field[x+2][y+2] == symbol && field[x+3][y+1] == symbol && field[x+4][y] == symbol)
                   return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        initField();
        printField();

        while (true) {
            playerStep();
            printField();
            if (checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN");
                break;
            }
            if (isFieldsFull()) {
                System.out.println("DRAW!");
                break;
            }
            aiStep();
            printField();
            if (checkWin(AI_DOT)) {
                System.out.println("SkyNet WIN");
                break;
            }
            if (isFieldsFull()) {
                System.out.println("DRAW!");
                break;
            }
        }
    }
}
