package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class MyUtils {

    //The activation function
    public static int sign(double input) {

        if (input >= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static double dotProduct(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }


    List<Integer> iterateStream(int from,int step, int limit) {
        return IntStream.iterate(from, i -> i + step) // next int
                .limit(limit / step) // only numbers in range
                .boxed()
                .collect(toList());
    }


    public static void iterateStream() {

        int i = 0;
        while (i <= 100) {
            double x = i / 100.0;
            System.out.println(x);
            i++;
        }

    }

    public static String gln() {
        int lineNo = Thread.currentThread().getStackTrace()[2].getLineNumber();

        String toReturn = "\nLine: " + lineNo + " ";

        return toReturn;
    }


    public static void duplicateList(int times, ArrayList<Double> list) {

        int listSize = list.size();

        for (int i = 0; i < listSize; i++) {

            for (int j = 0; j < times; j++) {
                Double temp = list.get(i);
                list.add(temp);

            }
        }
    }







}
