package com.company;

public class fillArray {

    /**
     *
     * The method does not take any argument, just return an array with 10
     * elements
     *
     * @return An array with 10 elements
     *
     */
    public static int[] fill1() {

        int[] array1 = new int[10];

        int i;

        int num = 1;

        for (i = 0; i < array1.length; i++) {

            array1[i] = num;

            num += 2;
        }

        System.out.println("Array with first 10 odd numbers filled");

        return array1;
    }

    /**
     *
     * The method does not take any argument, just return an two-dimensional
     * array 10 and 2
     *
     * @author Adam J
     * @return Two-dimensional array
     *
     */
    public int[][] fill2() {

        int[][] array2 = new int[2][4];

        for (int row = 0; row < array2.length; row++) {
            for (int column = 0; column < array2[row].length; column++) {
                array2[row][column] = (int) (Math.random() * 20);
            }
        }

        return array2;

    }

}
