package com.company;

public class xor {

    public static int size = 4;

    public static int x[] = {0, 0, 1, 1};
    public static int y[] = {0, 1, 0, 1};

    public static int result[] = new int[size];
    public static int result1[] = new int[size];

    public void sample() {


        for (int i = 0; i < x.length; i++) {

            if (x[i] == 1 || y[i] == 1) {
                result[i] = 1;
            }
            else
                result[i] = 0;

        }

    }

    public void sample1(){

        for (int i = 0; i < x.length; i++) {

            if (x[i] == y[i]) {
                result1[i] = 0;
            }
            else
                result1[i] = 1;

        }

    }

//    public void great() {
//        int tempBool[] = new int[size];
//
//        for (int i = 0; i < x.length; i++) {
//            if (x[i] == 0){
//                tempBool[i] =
//            }
//
//        }
//
//
//        boolean[] all = { false, true };
//        for (boolean a : all) {
//            for (boolean b: all) {
//                boolean c = a ^ b;
//                System.out.println(a + " ^ " + b + " = " + c);
//            }
//        }
//    }




    public void printor() {
        for (int i = 0; i < result1.length; i++) {
            System.out.println(result[i]);
        }
        System.out.println("\n");
    }

    public void printxor() {
        for (int i = 0; i < result1.length; i++) {
            System.out.println(result1[i]);
        }
    }

}