/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamehilo;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class GameHiLo {

    /**
     * @param args the command line arguments
     */
    private static Scanner sc=new Scanner(System.in); // the scanner shared by methods of the class.
    private static Random rand=new Random(); // the random object shared by the methods of the class.
    public static void main(String[] args) {
        // TODO code application logic here
        printHelp(); // print the help messages.
        startHiloGame(1000); // start the game
    }

    /**
     * Star the HiLo game
     * @param score The original points
     */
    private static void startHiloGame(int score) {
        while (score<10000 || score>0) { // the game ends when points is down to 0 or up to 10000
            score=playOneRound(score); // play one round
            System.out.print("Play again? ");
            if (!confirm()) {
                break; // the game ends if the user does not enter 'y'
            }
        }
        System.out.printf("Game ends. You have %d points.\r\n", score); // print the final score.
    }

    /**
     * Play one predict
     * @param score the current points that the user has.
     * @return the points after this round.
     */
    private static int playOneRound(int score) {
        if (score<0 || score>10000) { // validate the parameter
            System.out.printf("You have %d points. The game ends.",score<0?0:score);
            return score;
        }
        
        System.out.printf("You have %d points.\r\n",score);
        System.out.print("Enter the points to risk: ");
        int risk=getValidInteger(0,score); // get a valid input for risk points.
        System.out.print("Predict(1-High,0-Low): "); 
        int predict=getValidInteger(0,1); // get a valid input for the prediction.
        int n=rand.nextInt(13)+1; // a random data that computer generates.
        System.out.println("Number is "+n);
        if ((predict==0 && n>=1 && n<=6) || (predict==1 && n>=8 && n<=13)) { // the prediction matches the number.
            System.out.println("You Win.");
            return score+2*risk;
        }
        else {
            System.out.println("You Loose.");
            return score-risk;
        }
    }

    /**
     * Print the help message.
     */
    private static void printHelp() {
        System.out.println("High Low Game");
        System.out.println("Rules");
        System.out.println("Number 1 through 6 are low");
        System.out.println("Number 8 through 13 are high");
        System.out.println("Number 7 is neither high nor low");
    }

    /**
     * Get a valid integer input between min and max
     * @param min the lowest value of the valid input.
     * @param max the highest value of the valid input.
     * @return the valid input.
     */
    private static int getValidInteger(int min, int max) {
        boolean valid=false;
        int a=-1;
        while (!valid) {
            try {
                a=sc.nextInt();
                valid = (a>=min && a<=max);
            }
            catch (Exception e) {
                // capture error if the user input is not an integer.
                sc.next();
                valid=false;
            }
            if (!valid) {
                System.out.printf("Wrong input. You must input integer between %d and %d\r\n",min,max);
            }
        }
        return a;
    }

    /**
     * Ask user whether to continue. When user input "y" or "yes" the game will continue, otherwise the game ends.
     * @return 
     */
    private static boolean confirm() {
        String s=sc.next();
        return s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes");
    }
}
