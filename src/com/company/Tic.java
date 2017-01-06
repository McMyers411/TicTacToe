package com.company;

/**
 * Created by kids on 12/27/2016.
 */

import java.util.Scanner;

public class Tic
{
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {

        final int size = 3;
        char[][] board = new char[size][size];

        resetBoard(board);
        System.out.println("Howdy, welcome to TicTacToe v. Comp!\n");
        showBoard(board);


        System.out.print("  Which symbol do you want to play, \"x\" or \"o\"? ");
        char uSymbol = input.next().toLowerCase().charAt(0);
        char cSymbol = (uSymbol == 'x') ? 'o' : 'x';

        System.out.println();
        System.out.print("  Do you want to go first (y/n)? ");
        char ans = input.next().toLowerCase().charAt(0);

        int turn;
        int remainCount = size * size;

        if (ans == 'y') {
            turn = 0;
            userPlay(board, uSymbol);
        }
        else {
            turn = 1;
            compPlay(board, cSymbol);
        }
        showBoard(board);
        remainCount--;


        boolean done = false;
        int winner = -1;

        while (!done && remainCount > 0) {
            done = didWin(board, turn, uSymbol, cSymbol);

            if (done)
                winner = turn;
            else {
                turn = (turn + 1 ) % 2;

                if (turn == 0)
                    userPlay(board, uSymbol);
                else
                    compPlay(board, cSymbol);

                showBoard(board);
                remainCount--;
            }
        }


        if (winner == 0)
            System.out.println("\n You have won, great job ");
        else if (winner == 1)
            System.out.println("\n You have been bested ");
        else
            System.out.println("\n Draw ");

    }

    public static void resetBoard(char[][] brd)
    {
        for (int i = 0; i < brd.length; i++)
            for (int j = 0; j < brd[0].length; j++)
                brd[i][j] = ' ';
    }

    public static void showBoard(char[][] brd)
    {
        int numR = brd.length;
        int numC = brd[0].length;

        System.out.println();

        System.out.print("    ");
        for (int i = 0; i < numC; i++)
            System.out.print(i + "   ");
        System.out.print('\n');

        System.out.println();


        for (int i = 0; i < numR; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < numC; j++) {
                if (j != 0)
                    System.out.print("|");
                System.out.print(" " + brd[i][j] + " ");
            }

            System.out.println();

            if (i != (numR - 1)) {

                System.out.print("   ");
                for (int j = 0; j < numC; j++) {
                    if (j != 0)
                        System.out.print("+");
                    System.out.print("---");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void userPlay(char[][] brd, char usym)
    {
        System.out.print("\nEnter the row then the column number ");
        int rowIndex = input.nextInt();
        int colIndex = input.nextInt();

        while (brd[rowIndex][colIndex] != ' ') {
            System.out.print("\nThis space has been filled\nEnter the row then the column number ");
            rowIndex = input.nextInt();
            colIndex = input.nextInt();
        }

        brd[rowIndex][colIndex] = usym;
    }

    public static void compPlay(char[][] brd, char csym)
    {
        // Find the first empty cell and put a tic there.
        for (int i = 0; i < brd.length; i++) {
            for (int j = 0; j < brd[0].length; j++) {
                if (brd[i][j] == ' ') { // empty cell
                    brd[i][j] = csym;
                    return;
                }
            }
        }
    }

    public static boolean didWin(char[][] brd, int turn, char usym, char csym)
    {
        char sym;
        if (turn == 0)
            sym = usym;
        else
            sym = csym;

        int i, j;
        boolean win = false;
        for (i = 0; i < brd.length && !win; i++) {
            for (j = 0; j < brd[0].length; j++) {
                if (brd[i][j] != sym)
                    break;
            }
            if (j == brd[0].length)
                win = true;
        }


        for (j = 0; j < brd[0].length && !win; j++) {
            for (i = 0; i < brd.length; i++) {
                if (brd[i][j] != sym)
                    break;
            }
            if (i == brd.length)
                win = true;
        }

        if (!win) {
            for (i = 0; i < brd.length; i++) {
                if (brd[i][i] != sym)
                    break;
            }
            if (i == brd.length)
                win = true;
        }


        if (!win) {
            for (i = 0; i < brd.length; i++) {
                if (brd[i][brd.length - 1 - i] != sym)
                    break;
            }
            if (i == brd.length)
                win = true;
        }
        return win;
    }
}