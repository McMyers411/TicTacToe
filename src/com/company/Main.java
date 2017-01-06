package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Howdy, welcome to TicTacToe!\n Which mode would you like to play?");
        System.out.println("Human v Human: (0)\n Human v Comp: (1)\n Comp v Comp: (2)");
        String answer = input.nextLine();
        if(answer.equals("1")) {
            Tic.main(args);

            
            //This should connect to the others
        }
    }
}
