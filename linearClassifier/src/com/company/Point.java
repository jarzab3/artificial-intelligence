package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Point {

    //TODO change variables names so they will be more explicit and meaningful.
    public static double mapRange(double s, double a1, double a2, double b1, double b2) {
        return b1 + ((s - a1) * (b2 - b1)) / (a2 - a1);
    }

    int width = Main.width;
    int height = Main.height;

    public double x;
    public double y;

    double px;
    double py;

    int bias = 1;

    public int category;


    double getX() {
        return mapRange(x, -1, 1, 0, width);
    }

    double getY() {
        return mapRange(y, -1, 1, height, 0);
    }

    //TODO need to implement only one constructor and reduce the code. Get rid of the repetition in code.
    //TODO Perhaps split in another function.
    //This is used for a line
    public Point(double x_, double y_) {

        x = x_;
        y = y_;

    }

    public Point() {

        x = ThreadLocalRandom.current().nextDouble(-1, 1);
        y = ThreadLocalRandom.current().nextDouble(-1, 1);

        double lineY = drawing.f(x);

        px = getX();
        py = getY();

        if (y > lineY) {
            category = 1;
        } else {
            category = -1;
        }
    }
}
