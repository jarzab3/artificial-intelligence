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


public class drawing extends Application {

    public int[][] arr0;
    public int[][] arr1;
    public double[] centerLine;


    public drawing(){
        this.arr0 = Main.arr0;
        this.arr1 = Main.arr1;
        this.centerLine = Main.centerLine;
    }

    Pane panel = new Pane();

    int width = 600;
    int height = 600;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        Scene scene1 = new Scene(panel, width, height);

        perceptron percept = new perceptron();

        primaryStage.setTitle("Line");
        primaryStage.setScene(scene1);

        Line lineY = new Line();

        lineY.setEndX(width);
        lineY.setEndY(height);
        lineY.setStartX(0);
        lineY.setStartY(0);

        panel.getChildren().add(lineY);

        Point[] points = new Point[100];

        for (int i = 0; i < points.length; i++){

            points[i] = new Point();

            Circle c = new Circle();

            c.setRadius(8);

            double x = points[i].x;
            double y = points[i].y;

            c.setCenterY(y);
            c.setCenterX(x);

            c.setStroke(Color.BLACK);

            if (points[i].category ==1){
                c.setFill(Color.BLACK);
            } else {
                c.setFill(Color.WHITE);
            }

            panel.getChildren().add(c);

        }

        for (Point pt : points){

            double[] inputs = {pt.x, pt.y};

            int category = pt.category;

            percept.train(inputs, category);

            int predicted = percept.predict(inputs);

//            System.out.println("Predicte value is: " + predicted + " category is: " + category);

            Circle c = new Circle();
            c.setRadius(4);

            double x = pt.x;
            double y = pt.y;

            c.setCenterY(y);
            c.setCenterX(x);

            c.setStroke(Color.TRANSPARENT);

            if (predicted == category){

                c.setFill(Color.GREEN);

            } else {
                c.setFill(Color.RED);
            }

            panel.getChildren().add(c);


        }

        primaryStage.show();

    }



    public void launch(){
        Application.launch();
    }

}