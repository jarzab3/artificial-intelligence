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
 * @author Adam Jarzebak
 * @version 1.0
 * @since 2018-02-15
 */
public class Utlis {
    private boolean debug;

    private double[][] learnedData = {{0, 300}, {1, 300}, {2, 300}, {3, 300}, {4, 300}, {5, 300}, {6, 300}, {7, 300}, {8, 300}, {9, 300}};

    public Utlis(boolean debugMode) {
        debug = debugMode;
    }

    /**
     * Read the data from the file
     *
     * @param filename
     * @return data
     * @throws IOException On input error.
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
    public static void printArray2(double[][] arrIn, boolean printLength) {
//        System.out.println("Array length is: " + arrIn.length);

        for (double[] anArrIn : arrIn) {
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
     * @return double: Total Euclidean distance for n dimensions
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
     * This function run training for a single point.
     *
     * @param point
     * @param trainingSet
     * @return boolean: If correctly recognized return true otherwise false
     */
    public double[] train(int[] point, int[][] trainingSet) {

        double currentDistance;

        int currentDigit = point[64];

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

        int foundDigit = trainingSet[indexFound][64];

//        Printing for debugging purposes
        if (debug) {
            System.out.println("Digit: " + currentDigit);

            System.out.println("The shortest distance is: " + shortestDistance + " Digit is: " + foundDigit + "\n");
        }

//        If found correct match return true otherwise return false
//        if (currentDigit == foundDigit){
//            return true;
//        } else {
//            return false;
//        }

        double[] results = {foundDigit, shortestDistance};

        return results;
    }


    /**
     * This function run a training for all points from a training data.
     * Training is run on another set of data which is provided
     *
     * @param trainingSet
     * @param testingSet
     */
    public void trainForAll(int[][] trainingSet, int[][] testingSet) {

        ProgressBar bar = new ProgressBar();
        System.out.println("Process Starts Now!");
        int totalPrograss = trainingSet.length;
        bar.update(0, totalPrograss);

        int correctGuess = 0;
        int incorrectGuess = 0;

        for (int i = 0; i < trainingSet.length; i++) {

            double results[] = train(trainingSet[i], testingSet);

            int digit = (int) results[0];

            double distance = results[1];

            if (debug) {
                System.out.println(digit + " | " + distance);
            }

//          Search through digit and check if has larger distance then replace
            double storedDistance = learnedData[digit][1];

            if (storedDistance > distance) {
                learnedData[digit][1] = distance;
            }
            bar.update(i, totalPrograss);


        }

        System.out.println("Process Completed!");

//        double results = (double) incorrectGuess / (double) correctGuess;

//        System.out.println("\nFor this training found: " + correctGuess + " correctly recognized digits and " + incorrectGuess + " incorrectly recognized digits.");

//        System.out.println("\nCorrectly recognized: " + (float) (100 - (results * 100)) + "%");

        printArray2(learnedData, false);
    }


    public double findTheBestMatch(double number, double[][] learnedData){

        double distance = Math.abs(learnedData[0][1] - number);

        int idx = 0;

        for(int c = 1; c < learnedData.length; c++){

            double cdistance = Math.abs(learnedData[c][1] - number);
            if(cdistance < distance){
                idx = c;
                distance = cdistance;
            }
        }

        return learnedData[idx][0];
    }


    public void classify(int[][] testingSet){
        int correctGuess = 0;
        int incorrectGuess = 0;

        double currentDistance = 0;

        for (int i = 0; i < testingSet.length; i++) {
//            currentDistance = calcDistance(point, trainingSet[i], 64);

        }

        }


}


//            if (train(trainingSet[i], testingSet)){
//                    correctGuess ++;
//            } else {
//                incorrectGuess ++;
//            }