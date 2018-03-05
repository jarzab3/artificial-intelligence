package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Perceptron {

    private double[] weights;
    private double learningRate = 0.01;


    public Perceptron(int n) {
        weights = new double[n];

        for (int i = 0; i < weights.length; i++) {
            double random = ThreadLocalRandom.current().nextDouble(-1, 2);
            weights[i] = random;
//            System.out.println(weights[i]);
        }
    }




    public int predict(double[] inputs) {

        double sum = 0;

        for (int i = 0; i < weights.length; i++) {
//            System.out.println("Input is: " + inputs[i] + "   the weight is: " + weights[i]);
            sum += inputs[i] * weights[i];
        }

        int output = MyUtils.sign(sum);

//        System.out.println("Sum is:  " + sum + " sign is:  " +  output);

        return output;
    }

    public void train(double[] inputs, double goal) {
        double predicted = predict(inputs);
        double error = goal - predicted;

        //Adjust the weight accordingly to error rate
        for (int i = 0; i < weights.length; i++) {
            weights[i] += error * inputs[i] * learningRate;
        }
    }


    public double predictY(double x) {

        double w0 = weights[0];
        double w1 = weights[1];
        double w2 = weights[2];

        return -(w2 / w1) - (w0 / w1) * x;
    }











}
