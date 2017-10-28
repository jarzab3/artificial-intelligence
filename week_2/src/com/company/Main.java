package com.company;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;


public class Main extends JPanel {

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawLine(30, 40, 80, 100);
    }

    public static int data[][] = {{1, 1, 0}, {1, 2, 0}, {3, 3, 0}, {4, 1, 1}, {5, 2, 1}, {4, 1, 1}, {5, 2, 1}, {4, 1, 1}, {6, 6, 1}};

    public static void findMinMax(int[] array) {
        if (array == null || array.length < 1)
            return;
        int min = array[0];
        int max = array[0];

        for (int i = 1; i <= array.length - 1; i++) {
            if (max < array[i]) {
                max = array[i];
            }

            if (min > array[i]) {
                min = array[i];
            }
        }
        System.out.println("min: " + min + "\nmax: " + max);
    }


    public static void sortByClass(int[][] multiArray) {

        System.out.println("This is an array sorting method.");

        int i0 = 0;
        int i1 = 0;

        //This variables are used to set a length for an array. They are dynamically allocated.
        int len1 = 0;
        int len2 = 0;

        //This arrays are needed for new set of data which will be categorised according to their classes.
        int[][] arrayClass0 = new int[len1][3];
        int[][] arrayClass1 = new int[len2][3];


        //Iterate through all elements in an array
        for (int row = 0; row < multiArray.length; row++) {
            //Checks if 3rd element in all arrays is equal 1 or 0
            if (multiArray[row][2] == 0) {
                len1++;

                //Allocating a new data
                arrayClass0[i0][0] = multiArray[row][0];
                arrayClass0[i0][1] = multiArray[row][1];
                arrayClass0[i0][2] = multiArray[row][2];

                i0++;

            }
            if (multiArray[row][2] == 1) {
                len2++;

                arrayClass1[i1][0] = multiArray[row][0];
                arrayClass1[i1][1] = multiArray[row][1];
                arrayClass1[i1][2] = multiArray[row][2];

            }
        }

//        int[][] arrayClass0 = new int[len1][3];
//        int[][] arrayClass1 = new int[len2][3];

//
//        for (int row = 0; row < multiArray.length; row++) {
//
//            if (multiArray[row][2] == 0) {
//
//                arrayClass0[i0][0] = multiArray[row][0];
//                arrayClass0[i0][1] = multiArray[row][1];
//                arrayClass0[i0][2] = multiArray[row][2];
//
//                i0++;
//            }
//
//            if (multiArray[row][2] == 1) {
//
//                arrayClass1[i1][0] = multiArray[row][0];
//                arrayClass1[i1][1] = multiArray[row][1];
//                arrayClass1[i1][2] = multiArray[row][2];
//
//                i1++;
//
//            }
//        }

        //For loops below are printing out values from new arrays
        for (int row = 0; row < arrayClass0.length; row++) {
                System.out.println(arrayClass0[row][0] + "" + arrayClass0[row][1]+ " "+ arrayClass0[row][2]);
        }

        System.out.println("\n");

        for (int row = 0; row < arrayClass1.length; row++) {
            System.out.println(arrayClass1[row][0] + "" + arrayClass1[row][1]+ " "+ arrayClass1[row][2]);
        }

        for (int row = 0; row < arrayClass0.length; row++){


        }

//
//        int smallest = arrayClass0[0][0];
//
//        for (int row = 0; row < arrayClass0.length; row++){
//            if (arrayClass0[row][0] <  smallest){
//                smallest = arrayClass0[row][0];
//            }
//        }
//
//        System.out.println("The smallest number is: " + smallest);

//        findMinMax(arrayClass0[0]);
//        System.out.println(arrayClass1[1][0]);

    }

    public void print2DArray(int[][] multiArray) {

        System.out.println("This is a multi array printig function.");

        for (int row = 0; row < multiArray.length; row++) {
            for (int column = 0; column < multiArray[row].length; column++) {
                System.out.println("The row is: " + row + " and column is: " + column + ". The value: " + multiArray[row][column]);
            }
        }

    }

//    public static int data1[][] = {{},}
//
//    3,3,A
//3,5,A
//3,18,A
//7,15,A
//18,18,A
//0,0,A
//43,18,A
//-18,-1,A
//3,3,A
//4,-1,B
//4,-5,B
//2,-5,B
//10,-16,B
//-20,-40,B
//4,4,B

    public static void test_linear_equationx() {

        int x, y;

        double w1 = -0.010659720491454439;
        double w2 = -0.03610575789321302;

        x = 0;
        y = 4;

        double wynikx;
        double wyniky;

        wynikx = -(w1 - w2 * y) / w1;

        wyniky = -(w1 - w1 * x) / w2;

        System.out.println(wynikx + "  " + wyniky);


    }

    public static void drawCircle() {
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.fillOval(10, 10, 50, 50);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(400, 420);

        // Adds Game panel into JFrame
        frame.add(new Main());

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);

        perceptronB exec = new perceptronB();

//        perceptronB.execute_test();


        test_linear_equationx();

        sortByClass(data);


    }
}
