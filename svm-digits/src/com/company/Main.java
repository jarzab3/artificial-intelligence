package com.company;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        // write your code here

        double[] a1 = {0, 0, 3, 14, 16, 16, 6, 0, 0, 0, 8, 16, 16, 16, 9, 0, 0, 0, 0, 0, 7, 16, 6, 0, 0, 0, 0, 0, 12, 15, 1, 0, 0, 0, 2, 13, 16, 16, 7, 0, 0, 0, 2, 16, 16, 14, 2, 0, 0, 0, 6, 16, 9, 0, 0, 0, 0, 0, 7, 14, 2, 0, 0, 0}; //7
        double[] a2 = {0, 0, 3, 14, 16, 16, 6, 0, 0, 0, 8, 16, 16, 16, 9, 0, 0, 0, 0, 0, 7, 16, 6, 0, 0, 0, 0, 0, 12, 15, 1, 0, 0, 0, 2, 13, 16, 16, 7, 0, 0, 0, 2, 16, 16, 14, 2, 0, 0, 0, 6, 16, 9, 0, 0, 0, 0, 0, 7, 14, 2, 0, 0, 0}; //7

        Perceptron p = new Perceptron(3);

        Training tt = new Training();

        DotProduct dp = new DotProduct(a1, a2);


        System.out.println(MyUtils.gln() + "Dot product test: " + dp.dotProduct());


        //        MyUtils.iterateStream();


        ArrayList<Double> listDup = new ArrayList<>();

        listDup.add(1.1);
        listDup.add(2.2);
        listDup.add(3.3);


        MyUtils.duplicateList(2, listDup);


        Map<Integer, int[][]> dataDict1 = new HashMap<Integer, int[][]>();

        Dictionary<Integer, int[][]> dataDict = new Hashtable<Integer, int[][]>();


        int[][] d = {{1, 7},{2, 8}, {3, 8}};
        int[][] d1 = {{5, 1},{6, -1}, {7, 3}};

        dataDict.put(-1, d);
        dataDict.put(1, d1);

        Enumeration<Integer> key = dataDict.keys();
        while(key.hasMoreElements()){
            System.out.println(key.nextElement());
        }


        int[][] value = dataDict.get(-1);

//        System.out.println(value);




//
//        int[][] transforms = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
//
//        double[] w = {20, 10.0};
//
//        for(int ii = 0; ii < transforms.length; ii++) {
//            int[] transformation = transforms[ii];
//
//
//            System.out.println(transformation);
//            System.out.println(w);
//
////            double wt = w *
//
//        }


//        for (Double i : listDup)
//        {
//            System.out.println(i);
//        }


    }
}
