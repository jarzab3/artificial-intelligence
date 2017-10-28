package com.company;

public class Point {

    int width = 800;
    int height = 600;

    public double x;
    public double y;

    public int category;

    public Point(){

        x = (float) (Math.random() * width);
        y = (float) (Math.random() * height);

//        System.out.println("x: " + x + " y: " + y);

        if (x > y){
            category = 1;
        } else {
            category = -1;
        }
    }

//    public double[] pointVal(){
//
//        double values[] = {x, y};
//
//        return values;
//    }


}
