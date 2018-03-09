package com.company;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


import java.io.*;

/**
 * <h1>Utilities functions for Euclidean algorithm!</h1>
 * The MyUtils methods implements an application that
 * simply allows user to read files, calculate distances and train data.
 * <b>TODO:</b>
 * 1) Improve comments
 * 2) Add analytics
 * 3) Add to page
 *
 * @author Adam Jarzebak
 * @version 1.1
 * @since 2018-02-15
 */
public class MyUtils {

    /**
     * Read the data from the file
     *
     * @param filename
     * @param webURL
     * @param webPath
     * @return data
     * @throws IOException On input error.
     * @see IOException
     *
     */
    public static int[][] readFile(String filename, boolean webURL, String webPath) throws IOException {

//      Initialize an empty array list where a data from file will be stored.
        ArrayList<String> strs = new ArrayList<String>();

//      If
        if (webURL) {
            URL connection = new URL(webPath);

            System.out.println("Successfully opened stream to: " + webPath);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.openStream()));

            String strline = null;
            while ((strline = in.readLine()) != null) {
                strs.add(strline);
            }

            System.out.println("Received data from url");


        } else {

            try {
                FileInputStream instream = new FileInputStream(filename);
                DataInputStream datain = new DataInputStream(instream);
                BufferedReader br = new BufferedReader(new InputStreamReader(datain));

                String strline = null;
                while ((strline = br.readLine()) != null) {
                    strs.add(strline);
                }

                datain.close();

//          Catch any IO error and throw exception if any occurs.
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

        int[][] data = new int[strs.size()][65];
        for (int i = 0; i < strs.size(); i++) {
            String str = strs.get(i);
//          Separate data by comma a separator.

            String[] el = str.split(",");
            for (int j = 0; j < el.length; j++) {
                data[i][j] = Integer.valueOf(el[j]);
            }
        }


//      Return data retried from a file
        return data;
    }

    /**
     * This function prints one dimensional array
     *
     * @param arrIn
     * @throws IllegalArgumentException
     */
    public void printArray(int[] arrIn) {
        try {
            for (int anArrIn : arrIn) {
                System.out.print(anArrIn + " ");
            }
            System.out.println("\n");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function prints a two dimensional array
     *
     * @param arrIn
     * @param printLength
     * @throws IllegalArgumentException
     * @since 2018-01-12
     */
    public static void printArray2(double[][] arrIn, boolean printLength) {
        try {
//          For all elements in the array print values
            for (double[] anArrIn : arrIn) {

//              If printLength value is true then it will print length of arrays in an array
                if (printLength) {
                    System.out.println("\n" + "Length is: " + anArrIn.length);
                } else {
                    System.out.println("\n");
                }

                for (int j = 0; j < arrIn[0].length; j++) {
                    System.out.print(anArrIn[j] + " ");
                }
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function calculates Euclidean distance for n number of dimensions.
     *
     * @param point1
     * @param point2
     * @param dimensions
     * @return double: Total Euclidean distance for n dimensional array
     * @implNote dist(( x, y), (a, b)) = √(x - a)² + (y - b)² ...
     * @since 2018-01-18
     */
    public static double calcDistance(int[] point1, int[] point2, int dimensions) {
//      Variable total distance
        double distance;
        double tempResult = 0;

        for (int index = 0; index < dimensions; index++) {
            double q = (double) point1[index];
            double p = (double) point2[index];

//         Stores temporary value. Adding cumulatively.
            tempResult = tempResult + Math.pow((q - p), 2);

        }

//      Take a square root of total n dimensional points
        distance = Math.sqrt(tempResult);

        return distance;
    }

    /**
     * Method to calculate cosine similarity between two digits.
     *
     * @param docVector1 : vector 1
     * @param docVector2 : vector 2
     * @return double cosine similarity value
     * @implNote Cosine similarity is a measure of similarity between two non-zero vectors of an inner product space that measures the cosine of the angle between them.
     * @since 2018-03-08
     */
    public static double cosineSimilarity(int[] docVector1, int[] docVector2, int dimensions) {
        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;

//      docVector1 and docVector2 must be of same length
        for (int i = 0; i < dimensions; i++) {
            dotProduct += docVector1[i] * docVector2[i]; //a.b
            magnitude1 += Math.pow(docVector1[i], 2);  //(a^2)
            magnitude2 += Math.pow(docVector2[i], 2); //(b^2)
        }

        magnitude1 = Math.sqrt(magnitude1);//sqrt(a^2)
        magnitude2 = Math.sqrt(magnitude2);//sqrt(b^2)

//      When magnitudes of two vectors are not equal to 0 then calculate the dot product of these vectors.
        if (magnitude1 != 0.0 | magnitude2 != 0.0) {
//          a · b = ax × bx + ay × by
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);

        } else {
//          Otherwise returns 0
            return 0.0;
        }

//      Returns cosine similarity value for n dimensional data.
        return cosineSimilarity;
    }

}

