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

        train.fit(dataDict);

        train.getResults();

    }
}
