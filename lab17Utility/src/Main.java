import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static int totalWin = 0;
    static int totalLoose = 0;

    /**
     * @param perc
     * @param bet
     * @return
     */
    public static double calcUtility(double perc, double bet) {

        double utylity = (perc * bet) / 100;


        return utylity;
    }

    //        Write a program that calculates the utility of a bet that paid off 22% of the time 75£.

    /**
     *
     */
    public static double takeUserInput() {


        System.out.print("\nPlease type percentage: ");

        String userInput = scanner.nextLine();

        int userInputPerc = Integer.parseInt(userInput);

        return userInputPerc;
    }

    public static boolean decideWin(double winningRate){

        boolean win = false;

        int totalAttempts = totalWin + totalLoose;

        System.out.println("debug:  " + winningRate);

        if(winningRate * (totalWin + totalLoose) > totalWin){
            win = true;
            totalWin ++;

        }else{
            win = false;
            totalLoose++;
        }

        System.out.printf("Winnig now: %s. Total wins: %s. Total looses: %s \n", win, totalWin, totalLoose);

        return win;
    }


    public static boolean playGame() {

        String play = "p";
        String stop = "s";

//        Scanner keyboard = new Scanner(System.in);

        System.out.print("\n\nType 'S' to stop or 'P' to play: ");
        String input = scanner.nextLine();
//        String input = "Asd";

        boolean playReturn = false;

        if (input != null) {
//                System.out.println("Your input is : " + input);
            if (play.equals(input)) {

                playReturn = true;

            } else if (stop.equals(input)) {

                playReturn = false;

            } else {
//TODO need to fix this function
                System.out.println("Error in play function.");
                playReturn = false;

            }
        }

        return playReturn;
    }

    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }


    public static void main(String[] args) {
        System.out.println("Hello in utility calculator.\n");

        int bet = 75;

        calcUtility(22, bet);

        double userInputPerc = takeUserInput();

        calcUtility(userInputPerc, bet);

// 3. Now write a system that predicts the value of a 2-armed bandit problem.

        double winningRate = 45;

        winningRate = winningRate / 100;

        System.out.println(winningRate);


        decideWin(winningRate);
        decideWin(winningRate);
        decideWin(winningRate);
        decideWin(winningRate);
        decideWin(winningRate);

//        boolean win = false;
//        boolean loose = true;


//        boolean a = playGame();
//        System.out.println(a);






        scanner.close();
    }
}
