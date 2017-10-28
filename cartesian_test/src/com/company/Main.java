/*
* Linear Classifiers and the Perceptron Algorithm
*
*               Author: Adam Jarzebak
*               adam@jarzebak.eu
*
*
* Third Party License Acknowledgements
* ------------------------------------------
* Michael Thomas Flanagan's Java Scientific Library
* Author Dr Michael Thomas Flanagan
* m.flanagan@ucl.ac.uk
*
* */

package com.company;

import flanagan.io.*;
import flanagan.plot.*;
import flanagan.math.Point;

import java.time.Year;

public class Main {

    private static double data[][] = {{1, 1, 0}, {1, 2, 0}, {3, 3, 0},
            {4, 1, 1}, {5, 2, 1}, {4, 1, 1}, {5, 2, 1}, {4, 1, 1}, {6, 6, 1}};

    static double x = (float) (Math.random() * -1 + 1);

    static double y = (float) (Math.random() * -1 + 1);

    static double f(double x){

        return -0.3 * x - 0.2;
    }

    public static double mapRange(double a1, double a2, double b1, double b2, double s){
        return b1 + ((s - a1)*(b2 - b1))/(a2 - a1);
    }


    static double pixelX(){
        return mapRange(x, -1, 1,0, 400 );
    }

  static double pixelY(){
      return mapRange(y, -1, 1,300, 0 );

    }

    public static void main(String[] argv){

        // The perceptron instance
        perceptron p = new perceptron();

        for (int j = 0; j < data.length; j++) {
            double dataIn[] = {data[j][0], data[j][1]} ;
            p.train(dataIn, data[j][2]);

            //            System.out.println("The class is :" + data[j][2]);

        }

        //Plotting data
        int nCurves = 0;        // number of curves
        int[] nPoints = null;   // number of points per curve
        int nMax = 0;           // maximum no of points on a curves
        int ii = 0;             // counter

        double[][] data = null;	// data points
        double[][] cdata = null;

        String title ="Perceptron";      // plot title
        String xLeg=" ";        // x axis legend
        String xUnits=" ";      // x axis units
        String yLeg=" ";        // y axis legend
        String yUnits=" ";      // y axis units
        String fileName=" "; 	// name of file containing input data


        // Select data file
        // A text file, named TestPlotter.txt, was been prepared for this example
        //  and stored in a directory C:\Java6\TestData"
        // Replace data file name and directory path by your own relevant ones
//        FileChooser fin = new FileChooser("C:\\Java6\\TestData");
        FileChooser fin = new FileChooser("/Users/adam/Documents/programming/artificial_inteligence/cartesian_test/src/com/company/data.txt");
        fileName = fin.selectFile("Choose Data File for Plotting");

        // Read in graph title
        title = fin.readLine();

        // Read in the x-axis legend
        xLeg = fin.readLine();

        // Read in the x-axis units
        xUnits = fin.readLine();

        // Read in the y-axis legend
        yLeg = fin.readLine();

        // Read in the y-axis units
        yUnits = fin.readLine();

        // Read in the number of curves
        nCurves = fin.readInt();

        // Read in the number of points on the curve with the largest number of points
        nMax = fin.readInt();

        // Create a 2-dimensional array of doubles, for storing the data, using the PltGraph initialiszation method
        data = PlotGraph.data(nCurves,nMax);

        System.out.println("test " + nCurves+ "  " +nMax);


        cdata = PlotGraph.data(1, 9);


//        double[ ][ ] cdata = {{ 1.0, 5.0},{1.0, 0.2}, {0.4, 4.0}};


        double x = -1;
        double y = f(x);
        double x2 = 1;
        double y2 = f(x2);

        int label = 0;

        double lineY = f(x);

        if (y > lineY){
            label = 1;

        } else {
            label = -1;
        }

        cdata = new double[][]{{pixelX(), pixelX() + 0.01, x2}, {pixelY(), pixelY() + 0.01, y2}};

        // Read in the data
        nPoints = new int[nCurves];

        ii=0;
        for(int i=0; i<nCurves; i++){
            nPoints[i]=fin.readInt();
            for(int j=0; j<nPoints[i]; j++){
                data[ii][j]=fin.readDouble();       // x-axis data
                data[ii+1][j]=fin.readDouble();     // y-axis data

//                System.out.println("data is: " +  data[ii][j] + "  " + data[ii+1][j]);

            }
            ii+=2;
        }

        // Create an instance of PlotGraph
        PlotGraph pg = new PlotGraph(data);


        PlotGraph pg1 = new PlotGraph(cdata);

//
//        for (int i = 0; i < data.length; i++) {
//            for (int j = 0; j < data[0].length; j++) {
//                System.out.println(data[i][j]);
//            }
//        }
//
//        System.out.println("new data\n");
//
//        for (int i = 0; i < cdata.length; i++) {
//            for (int j = 0; j < cdata[0].length; j++) {
//                System.out.println(cdata[i][j]);
//            }
//        }


        pg.setGraphTitle(title);            // Enter graph title
        pg.setXaxisLegend(xLeg);            // Enter x-axis legend
        pg.setXaxisUnitsName(xUnits);       // Enter x-axis units
        pg.setYaxisLegend(yLeg);            // Enter y-axis legend
        pg.setYaxisUnitsName(yUnits);       // Enter y-axis units

        int[] pointOptions = {2, 4};        // Set point option to open circles on the first graph line and filled circles on the second graph line
        pg.setPoint(pointOptions);


//        pg1.setPoint(pointOptions);


        pg.setLine(0);                      // Set line option to a continuous lines and a 200 point cubic spline interpolation
        pg1.setLine(3);                      // Set line option to a continuous lines and a 200 point cubic spline interpolation


        pg.plot();
        pg1.plot();

//        fin.endProgram();

    }
}



