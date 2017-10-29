package com.company;

public class print_array {

    public print_array() {
        System.out.println("Print array function instantiation completed.");
    }

    /**
     *
     * The method prints all arguments from received array on the console
     *
     * @param numbers array to be printed
     *
     */
    public void printa(int[] numbers) {

        int i = 0;
        while (i < numbers.length) {
            System.out.println("Item(" + i + ")=" + numbers[i]);
            i++;
        }
    }

    public void printb(int[][] multiArray) {

        System.out.println("This is a multi array printig function.");

        for (int row = 0; row < multiArray.length; row++) {
            for (int column = 0; column < multiArray[row].length; column++) {
                System.out.println("The row is: " + row + " and column is: " + column + ". The value: " + multiArray[row][column]);
            }
        }

    }
}
