package com.company;

public class Main {

    public static boolean debug = false;


    public static void main(String[] args) {

        missionaries_problem m = new missionaries_problem();

        if (debug) {

            m.transer(2, 0);
            m.CurrentStates();

            m.transer(1, 0);
            m.CurrentStates();

            m.transer(2, 0);
            m.CurrentStates();

            m.transer(1, 0);
            m.CurrentStates();

            m.transer(0, 2);
            m.CurrentStates();


            m.transer(1, 1);
            m.CurrentStates();

            m.transer(0, 2);
            m.CurrentStates();


            m.transer(1, 0);
            m.CurrentStates();


            m.transer(2, 0);
            m.CurrentStates();

            m.transer(1, 0);
            m.CurrentStates();

            m.transer(2, 0);
            m.CurrentStates();

        }

        while (m.is_final() == false) {
//            System.out.println("Hi");



        }










    }
}
