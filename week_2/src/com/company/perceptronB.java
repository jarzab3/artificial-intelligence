package com.company;

public class perceptronB{

    public static void execute_test()
    {
        perceptron1 als = new perceptron1();

        System.out.println("pairs");

        als.initWeights();
        als.printClassifier();
        als.printWeights();

        als.train();
        als.test();
    }
}