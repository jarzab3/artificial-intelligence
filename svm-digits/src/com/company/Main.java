package com.company;

import java.util.*;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Test main training
        // Construct sample arrays
        Dictionary<Integer, double[][]> dataDict = new Hashtable<Integer, double[][]>();

        double[][] d = {{1, 7}, {2, 8}, {3, 8}};

        double[][] d1 = {{5, 1}, {6, -1}, {7, 3}};

        dataDict.put(1, d1);

        dataDict.put(-1, d);

//      Objects
        Training train = new Training();

//        train.fit(dataDict);

//        train.getResults();

        Cosine co = new Cosine();

        double[] n  = {0, 1, 6, 15, 12, 1, 0, 0, 0, 7, 16, 6, 6, 10, 0, 0, 0, 8, 16, 2, 0, 11, 2, 0, 0, 5, 16, 3, 0, 5, 7, 0, 0, 7, 13, 3, 0, 8, 7, 0, 0, 4, 12, 0, 1, 13, 5, 0, 0, 0, 14, 9, 15, 9, 0, 0, 0, 0, 6, 14, 7, 1, 0, 0}; //0

        double[] n1 = {0, 0, 7, 16, 16, 16, 2, 0, 0, 0, 14, 14, 9, 16, 2, 0, 0, 2, 16, 6, 0, 9, 7, 0, 0, 4, 16, 1, 0, 8, 8, 0, 0, 6, 16, 0, 0, 12, 8, 0, 0, 5, 16, 4, 2, 16, 5, 0, 0, 0, 15, 16, 16, 13, 0, 0, 0, 0, 8, 13, 10, 2, 0, 0}; //0


        System.out.println(co.cosineSimilarity(n , n1));



    }
}
