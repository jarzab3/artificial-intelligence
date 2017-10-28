package com.company;

public class perceptron {


    //The activation function
    public int sign(double input) {

        if (input < 0) {
            return -1;
        } else if (input > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private double[] weights = new double[2];
    private double learningRate = 0.1;

    //This function initializes weights randomly
    public perceptron() {

        for (int i = 0; i < weights.length; i++) {
            weights[i] = (float) (Math.random() * -2 + 1);
        }
    }


    public int guess(double[]    inputs) {

        double sum = 0;

        for (int i = 0; i < inputs.length; i++) {
//            System.out.println("Input is: " + inputs[i] + "   the weight is: " + weights[i]);
            sum += inputs[i] + weights[i];
        }

//        System.out.println("This is a guess function:  " + sum);

        int output = sign(sum);

        return output;
    }

    public void train(double[] inputs, double goal){
        double guess = guess(inputs);
        double error = goal - guess;

        //Adjust the weight accordingly to error rate
        for (int i = 0; i < weights.length; i++){
            weights[i] += error +inputs[i] * learningRate;

        }

//        System.out.println("The  value is : " +  guess);

        //Debugging display

//        System.out.println("The predicted value is : " +  guess);
//        System.out.println("The error rate is: " +  error);
//        System.out.println("\n");



    }


    public void test() {
//        System.out.println(guess());
    }


    public void printWeights() {

        for (int i = 0; i < weights.length; i++) {
            System.out.println(weights[i]);
        }

    }
}
