package com.company;

import java.lang.reflect.Array;

public class multiLayer {

    private double[][] arrayClass0;
    private double[][] arrayClass1;

    //Accessor for an array with class 0
    public double[][] getArrayClass0() {
        return arrayClass0;
    }

    //Accessor for an array with class 1
    public double[][] getArrayClass1() {
        return arrayClass1;
    }

    public void sort(double[][] multiArray) {
        // This method takes an arrays as a input then it sort according to class provided

        int i0 = 0;
        int i1 = 0;

        //This variables are used to set a length for an array. They are dynamically allocated.
        int len0 = 0;
        int len1 = 0;

        //Iterate through all elements in an array in order to determine a length for arrays
        for (int row = 0; row < multiArray.length; row++) {
            //Checks if 3rd element in all arrays is equal 1 or 0
            if (multiArray[row][2] == 0) {
                len0++;
            }
            if (multiArray[row][2] == 1) {
                len1++;
            }
        }

        //This arrays are needed for new set of data which will be categorised according to their classes.
        arrayClass0 = new double[len0][3];
        arrayClass1 = new double[len1][3];


        //Iterate through all elements in an array and assign to a correct class
        for (int row = 0; row < multiArray.length; row++) {
            //Checks if 3rd element in all arrays is equal 1 or 0
            if (multiArray[row][2] == 0) {

                //Allocating a new data
                arrayClass0[i0][0] = multiArray[row][0];
                arrayClass0[i0][1] = multiArray[row][1];
                arrayClass0[i0][2] = multiArray[row][2];

                i0++;

            }
            if (multiArray[row][2] == 1) {

                arrayClass1[i1][0] = multiArray[row][0];
                arrayClass1[i1][1] = multiArray[row][1];
                arrayClass1[i1][2] = multiArray[row][2];

                i1++;

            }
        }
    }

    public void calcDistance(double[][] arr0, double[][] arr1) {


        double smallestPQ = Math.sqrt(Math.pow((arr1[0][0] - arr0[0][0]), 2) + Math.pow((arr1[0][1] - arr0[0][1]), 2));

        double points[] = new double[4];

        for (int j = 0; j < arr1.length; j++) {

            double PQ = Math.sqrt(Math.pow((arr1[j][0] - arr0[0][0]), 2) + Math.pow((arr1[j][1] - arr0[0][1]), 2));

            if (PQ < smallestPQ) {
                smallestPQ = PQ;

                points[0] = arr1[j][0];
                points[1] = arr1[0][0];
                points[2] = arr1[j][1];
                points[3] = arr1[0][1];

            }
        }


        System.out.println("this is a smallest distance from point " + smallestPQ);

        double t1 = 9.00;
        double t2 = 2.00;

    }

    public void sortByCol(double[][] arr) {

        int colNo = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j][colNo] < arr[i][colNo]) {
                    double[] tt = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tt;
                }
            }
        }
    }

//      w0 * Tx + b0 = 1 or
//      w0 * Tx + b0 = -1


    public double selectValue(double Y0, double Y1, boolean top, boolean low) {

        if (Y0 > Y1 && (top == true))
            return Y0;
        else if (Y0 < Y1 && (top == true))
            return Y1;
        else if (Y0 > Y1 && (low == true))
            return Y1;
        else
            return Y0;
    }

    public double[] find() {

        boolean debug = false;

        sortByCol(arrayClass0);
        sortByCol(arrayClass1);


        /////////////////  TEST

        calcDistance(arrayClass0, arrayClass1);

        /////////////////


        if (debug) {
            System.out.println("\nThis is sorted within a class.\n");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(arrayClass0[i][j] + " ");
                }
                System.out.println();
            }
        }

        if (debug) {
            System.out.println("\nThis is sorted within a class.\n");
            for (int i = 0; i < arrayClass1.length; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(arrayClass1[i][j] + " ");
                }
                System.out.println();
            }
        }

        double getMaxXClass0 = arrayClass0[arrayClass0.length - 1][0];
        double getMaxYClass0 = arrayClass0[arrayClass0.length - 1][1];
        double getMinXClass0 = arrayClass0[0][0];
        double getMinYClass0 = arrayClass0[0][1];

        double getMaxXClass1 = arrayClass1[arrayClass1.length - 1][0];
        double getMaxYClass1 = arrayClass1[arrayClass1.length - 1][1];
        double getMinXClass1 = arrayClass1[0][0];
        double getMinYClass1 = arrayClass1[0][1];

        double centerLine[] = {(getMaxXClass0 + (getMaxXClass1 - getMaxXClass0) / 2), selectValue(getMaxYClass0, getMaxYClass1, true, false),
                (getMinXClass0 + (getMinXClass1 - getMinXClass0) / 2), selectValue(getMinYClass0, getMinYClass1, false, true)};


        if (debug) {

            System.out.println("\n" + "Values for class 0: " + getMinXClass0 + " " + getMinYClass0 + " " + getMaxXClass0 + " " + getMaxYClass0 + "\n");

            System.out.println("\n" + "Values for class 1: " + getMinXClass1 + " " + getMinYClass1 + " " + getMaxXClass1 + " " + getMaxYClass1 + "\n");

            System.out.println("\n" + centerLine[0] + " " + centerLine[1] + " " + centerLine[2] + " " + centerLine[3] + "\n");

        }

        return centerLine;

    }


}