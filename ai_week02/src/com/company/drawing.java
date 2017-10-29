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

import java.util.concurrent.ThreadLocalRandom;

public class drawing extends Application {

    public drawing() {

    }

    Pane panel = new Pane();

    int width = Main.width;
    int height = Main.height;

    //This a linear equation
    static double f(double x) {

        return 0.3 * x + 0.2;
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws InterruptedException {

        Scene scene1 = new Scene(panel, width, height);

        perceptron percept = new perceptron(3);

        primaryStage.setTitle("Line");
        primaryStage.setScene(scene1);

        //Drawing line
        Point p1 = new Point(-1, f(-1));
        Point p2 = new Point(1, f(1));

        Line classifierLine = new Line();

        classifierLine.setStartX(p1.getX());
        classifierLine.setStartY(p1.getY());
        classifierLine.setEndX(p2.getX());
        classifierLine.setEndY(p2.getY());

        panel.getChildren().add(classifierLine);

        // Second line
        Point p3 = new Point(-1, percept.predictY(-1));
        Point p4 = new Point(1, percept.predictY(1));

        Line guessLine = new Line();

        guessLine.setStartX(p3.px);
        guessLine.setStartY(p4.py);
        guessLine.setEndX(p3.px);
        guessLine.setEndY(p4.py);


        Point[] points = new Point[100];

        for (int i = 0; i < points.length; i++) {

            Point pt = new Point();

            points[i] = pt;

            Circle c = new Circle();

            c.setRadius(8);

            c.setCenterX(pt.getX());
            c.setCenterY(pt.getY());

            c.setStroke(Color.BLACK);

            if (points[i].category == 1) {
                c.setFill(Color.BLACK);
            } else {
                c.setFill(Color.WHITE);
            }

            panel.getChildren().add(c);

        }


        //Training
        for (Point pt : points) {

            double[] inputs = {pt.x, pt.y, pt.bias};

            int category = pt.category;

            percept.train(inputs, category);

            int predicted = percept.predict(inputs);

//            System.out.println("Predicte value is: " + predicted + " category is: " + category);

            Circle c = new Circle();
            c.setRadius(4);

            double x = pt.x;
            double y = pt.y;

            c.setCenterY(pt.py);
            c.setCenterX(pt.px);

            c.setStroke(Color.TRANSPARENT);

            if (predicted == category) {

                c.setFill(Color.GREEN);

            } else {
                c.setFill(Color.RED);
            }
            panel.getChildren().add(c);

        }

        primaryStage.show();

    }

    public void launch() {
        Application.launch();
    }

}

//TODO make a settings file with a common width and height for all classes
//TODO make a package with utils for all cllases
