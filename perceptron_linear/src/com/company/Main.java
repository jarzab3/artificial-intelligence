package com.company;

public class Main {

    public static double data[][] = {{1, 1, 0}, {1, 2, 0}, {3, 3, 0},
            {4, 1, 1}, {5, 2, 1}, {4, 1, 1}, {5, 2, 1}, {4, 1, 1}, {6, 6, 1}};

    public static double datatest[] = {-1, 0.5};

    public static void main(String[] args) {

        perceptron p = new perceptron();

//    p.guess(datatest);

//    System.out.println(p.guess(datatest));

//        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
//                System.out.println(data[j][0] + " ");
                double dataIn[] = {data[j][0], data[j][1]} ;
                p.train(dataIn, data[j][2]);
                System.out.println("The class is :" + data[j][2]);

            }
            System.out.println();
//        }

//    p.generateRandom();

//        p.printWeights();


    }
}
