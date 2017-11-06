package com.company;

import sun.font.TrueTypeFont;

import java.util.Stack;

public class missionaries_problem {

    // Initial values
    static int cannibalsLeft = 0;
    static int missionariesLeft = 0;

    static int cannibalsRight = 3;
    static int missionariesRight = 3;

    static boolean boatLeft = false;
    static boolean boatRight = true;

    public boolean is_final() {

        if (cannibalsLeft == 3 && missionariesLeft == 3) {
            return true;
        } else {
            return false;
        }
    }


    public void is_correct_move() {
        if (cannibalsLeft < 0 || missionariesLeft < 0) {
            System.out.println("\n---> Illegal move <---");
            System.exit(1);
        } else if (cannibalsRight < 0 || missionariesRight < 0) {
            System.out.println("\n---> Illegal move <---");
            System.exit(1);
        } else if (cannibalsLeft > 3 || missionariesLeft > 3) {
            System.out.println("\n---> Illegal move <---");
            System.exit(1);
        } else if (cannibalsRight > 3 || missionariesRight > 3) {
            System.out.println("\n---> Illegal move <---");
            System.exit(1);
        }
    }

    public void transer(int cannibals, int missionaries) {

        if (cannibals == 0 && missionaries == 0) {
            System.out.println("\n--- >Boat canot travel without any passanger <---");
            System.exit(1);
        }

        if (boatRight) {
            System.out.println("\nThe boat is on right");
        } else if (boatLeft) {
            System.out.println("\nThe boat is on left");
        }

        if (boatRight) {

            missionariesRight = missionariesRight - missionaries;
            cannibalsRight = cannibalsRight - cannibals;

            missionariesLeft = missionariesLeft + missionaries;
            cannibalsLeft = cannibalsLeft + cannibals;

            boatLeft = true;
            boatRight = false;

            System.out.println("This journey takes ->  " + missionaries + " missionaries and " + cannibals + " cannibals");

            System.out.println("Boat travels on left");

            is_correct_move();

        } else if (boatLeft) {

            missionariesRight = missionariesRight + missionaries;
            cannibalsRight = cannibalsRight + cannibals;

            missionariesLeft = missionariesLeft - missionaries;
            cannibalsLeft = cannibalsLeft - cannibals;

            boatLeft = false;
            boatRight = true;

            System.out.println("This journey takes ->  " + missionaries + " missionaries and " + cannibals + " cannibals");

            System.out.println("Boat travels on right");

            is_correct_move();

        } else {
            System.out.println("Error");
        }
    }

    public static void CurrentStates() {

        System.out.println("\nMissionaries on right ->  " + missionariesRight + " cannibals on right -> " + cannibalsRight);
        System.out.println("Missionaries on left ->  " + missionariesLeft + " cannibals on left -> " + cannibalsLeft);

    }


//    public void lear() {
//
//        Stack<Integer> stack = new Stack<>();
//
//        stack.push();
//        while (is_final()) {
//
//
//        }
//    }


}
