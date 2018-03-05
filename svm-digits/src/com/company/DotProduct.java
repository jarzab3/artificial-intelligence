package com.company;

public class DotProduct {

    double[] a;
    double[] b;

    public DotProduct(double[] a_, double[] b_){
        a = a_;
        b = b_;
    }

    public double dotProduct() {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

}