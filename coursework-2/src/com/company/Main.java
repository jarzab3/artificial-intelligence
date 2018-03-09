package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String path1 = "/Users/adamj/Documents/programming/artificial-intelligence/coursework-2/src/com/company/cw2DataSet1.csv";
        String path2 = "/Users/adamj/Documents/programming/artificial-intelligence/coursework-2/src/com/company/cw2DataSet2.csv";

        Utlis util = new Utlis(false);

        int[][] trainingSet = util.readFile(path1);
        int[][] testingSet = util.readFile(path2);

        int[] n  = {0, 1, 6, 15, 12, 1, 0, 0, 0, 7, 16, 6, 6, 10, 0, 0, 0, 8, 16, 2, 0, 11, 2, 0, 0, 5, 16, 3, 0, 5, 7, 0, 0, 7, 13, 3, 0, 8, 7, 0, 0, 4, 12, 0, 1, 13, 5, 0, 0, 0, 14, 9, 15, 9, 0, 0, 0, 0, 6, 14, 7, 1, 0, 0, 0};

        int[] n1 = {0, 0, 7, 16, 16, 16, 2, 0, 0, 0, 14, 14, 9, 16, 2, 0, 0, 2, 16, 6, 0, 9, 7, 0, 0, 4, 16, 1, 0, 8, 8, 0, 0, 6, 16, 0, 0, 12, 8, 0, 0, 5, 16, 4, 2, 16, 5, 0, 0, 0, 15, 16, 16, 13, 0, 0, 0, 0, 8, 13, 10, 2, 0, 0, 0};


//        System.out.println(Arrays.toString(trainingSet[0]));

//        System.out.println(Utlis.calcDistance(n, n1, 64));


        util.trainForAll(trainingSet, testingSet);

    }
}
