package com.company;

public class Main {

    public static int data[][] = {{1, 1, 0}, {1, 2, 0}, {3, 3, 0}, {4, 1, 1}, {5, 2, 1},
            {4, 1, 1}, {5, 2, 1}, {4, 1, 1}, {6, 6, 1}};

    public static int width = 600;
    public static int height = 600;

    public static void main(String[] args) {

        drawing test = new drawing();

        test.launch();

    }
}
