package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class perceptron {

    private double[] weights;
    private double learningRate = 0.01;

    //The activation function
    public int sign(double input) {

        if (input >= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    //This function initializes weights randomly
    public perceptron(int n) {
         weights = new double[n];

        //TODO This need to be change to more generic contructor and initialize the weight randomly.
        // There was a problem with having random number for two weights

        weights[0] = ThreadLocalRandom.current().nextDouble(-1, 0);
        weights[1] = ThreadLocalRandom.current().nextDouble(0, 1);
        weights[2] = ThreadLocalRandom.current().nextDouble(-1, 1);

        for (int i = 0; i < weights.length; i++) {
//            double random = ThreadLocalRandom.current().nextDouble(-1, 1);
//            weights[i] = random;
            System.out.println(weights[i]);
        }
    }

    public int predict(double[] inputs) {

        double sum = 0;

        for (int i = 0; i < weights.length; i++) {
//            System.out.println("Input is: " + inputs[i] + "   the weight is: " + weights[i]);
            sum += inputs[i] * weights[i];
        }

        int output = sign(sum);

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

    public double predictY (double x){

        double w0 = weights[0];
        double w1 = weights[1];
        double w2 = weights[2];

        return - (w2/w1)  - (w0/w1) * x;
    }

}
