package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


import java.io.*;

/**
 * <h1>Utilities functions for Euclidean algorithm!</h1>
 * The Utlis methods implements an application that
 * simply allows user to read files, calculate distances and train data.
 * <b>TODO:</b>
 * 1) Improve comments
 * 2) Add analytics
 *
 * @author  Adam Jarzebak
 * @version 1.0
 * @since   2018-02-15
 */
public class Utlis {
    private boolean debug;

    public Utlis(boolean debugMode) {
        debug = debugMode;
    }

    /**
     * Read the data from the file
     *
     * @param filename
     * @return data
     * @exception IOException On input error.
     * @see IOException
     */
    public int[][] readFile(String filename) {

        ArrayList<String> strs = new ArrayList<String>();

        try {
            FileInputStream instream = new FileInputStream(filename);
            DataInputStream datain = new DataInputStream(instream);
            BufferedReader br = new BufferedReader(new InputStreamReader(datain));

            String strline = null;
            while ((strline = br.readLine()) != null) {
                strs.add(strline);
            }

            datain.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        int[][] data = new int[strs.size()][65];
        for (int i = 0; i < strs.size(); i++) {
            String str = strs.get(i);
            // System.out.println(str);
            String[] el = str.split(",");
            for (int j = 0; j < el.length; j++) {
                data[i][j] = Integer.valueOf(el[j]);
            }
        }
        return data;
    }

    /**
     * This function prints one dimensional array
     *
     * @param arrIn
     */
    public void printArray(int[] arrIn) {
        //  for (int i = 0; i < arrIn.length; i++) {
        for (int anArrIn : arrIn) {
            System.out.print(anArrIn + " ");
        }
        System.out.println("\n");
    }

    /**
     * This function prints a two dimensional array
     *
     * @param arrIn
     * @param printLength
     */
    public static void printArray2(int[][] arrIn, boolean printLength) {
        System.out.println("Array length is: " + arrIn.length);

        for (int[] anArrIn : arrIn) {
            if (printLength) {
                System.out.println("\n" + "Length is: " + anArrIn.length);
            } else {
                System.out.println("\n");
            }

            for (int j = 0; j < arrIn[0].length; j++) {
                System.out.print(anArrIn[j] + " ");
            }
        }
    }


    /**
     * This function calculates Euclidean distance for n number of dimensions.
     *
     * @param point1
     * @param point2
     * @param dimensions
     * @return Double: Total Euclidean distance for n dimensions
     */
    public static double calcDistance(int[] point1, int[] point2, int dimensions) {
        double distance;
        double tempResult = 0;

        int digit1 = point1[dimensions];
        int digit2 = point2[dimensions];

        for (int i = 0; i < dimensions; i++) {
            double q = (double) point1[i];
            double p = (double) point2[i];

            tempResult = tempResult + Math.pow((q - p), 2);

        }

        distance = Math.sqrt(tempResult);


        return distance;
    }

    /**
     * @param point
     * @param trainingSet
     */
    public void train(int[] point, int[][] trainingSet) {

        double currentDistance;

        System.out.println("Digit: " + point[64]);

        int indexFound = 0;
        double shortestDistance = calcDistance(point, trainingSet[0], 64);

        for (int i = 1; i < trainingSet.length; i++) {
            currentDistance = calcDistance(point, trainingSet[i], 64);

            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                indexFound = i;
                if (debug) {
                    System.out.println("Found shorter distance: " + shortestDistance + " digit is value:" + trainingSet[i][64]);
                }
            }

        }

        System.out.println("The shortest distance is: " + shortestDistance + " Digit is: " + trainingSet[indexFound][64] + "\n");
    }


    public void trainForAll(int[][] trainingSet, int[][] testingSet) {

        for (int i = 1; i < trainingSet.length; i++) {
            train(trainingSet[i], testingSet);
        }

    }




}

