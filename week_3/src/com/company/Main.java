package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;


public class Main{

    public static double data[][] = {{1, 1, 0}, {1, 2, 0}, {3, 3, 0}, {4, 1, 1}, {5, 2, 1},
            {4, 1, 1}, {5, 2, 1}, {4, 1, 1}, {6, 6, 1}};

    public static int arr0[][];
    public static int arr1[][];
    public static double centerLine[];

    public static void main(String[] args) {

        multiLayer ml = new multiLayer();

        ml.sort(data);

        ml.find();


//        s.sort(data);

//        s.find();

//        centerLine = s.find();

//        arr0 = s.getArrayClass0();
//        arr1 = s.getArrayClass1();

        draw d = new draw();

//        d.launch();

    }
}
