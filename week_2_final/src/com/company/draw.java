package com.company;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

public class draw extends Application {

    public int[][] arr0;
    public int[][] arr1;
    public double[] centerLine;


    public draw(){
        this.arr0 = Main.arr0;
        this.arr1 = Main.arr1;
        this.centerLine = Main.centerLine;
    }

    Pane pane = new Pane();

    int width = 800;
    int height = 600;
    int w = width / 2;
    int h = height / 2;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

//        Pane pane = new Pane();
        Scene scene = new Scene(pane, width, height);

        primaryStage.setTitle("Line");
        primaryStage.setScene(scene);

        Line lineY = new Line();
        Line lineX = new Line();

        Line linear_classifier = new Line();

        lineY.setEndX(w);
        lineY.setEndY(h - 250);
        lineY.setStartX(w);
        lineY.setStartY(h + 250);

        lineX.setEndX(w + 300);
        lineX.setEndY(h);
        lineX.setStartX(w - 300);
        lineX.setStartY(h);

        pane.getChildren().add(lineY);
        pane.getChildren().add(lineX);

        drawPoints(arr0, true);
        drawPoints(arr1, false);

        linear_classifier.setStartX(w + (this.centerLine[0] * 10));
        linear_classifier.setStartY(h - (this.centerLine[1] * 10));
        linear_classifier.setEndX(w + (this.centerLine[2] * 10));
        linear_classifier.setEndY(h - (this.centerLine[3] * 10));

        pane.getChildren().add(linear_classifier);

        primaryStage.show();
    }

    public void drawPoints(int[][] arr0, boolean fill){

        for (int row = 0; row < arr0.length; row++) {
            //Draw circles for all x and y in array0

            Circle circle = new Circle();

            double min = 0.1;
            double max = 1.5;

            double randomNum = min + (int)(Math.random() * max);

            circle.setCenterX(w + arr0[row][0] * 10);
            circle.setCenterY(h - arr0[row][1] * 10);

            circle.setRadius(2+randomNum);

            circle.setStroke(Color.BLUE);

            if (fill == true) {
                circle.setFill(Color.BLACK);
            }else{
                circle.setFill(Color.WHITE);
            }

            pane.getChildren().add(circle);

        }
    }




    public void launch(){
        Application.launch();
    }

}
