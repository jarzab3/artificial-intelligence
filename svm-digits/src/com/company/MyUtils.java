package com.company;

import java.util.*;
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


    List<Integer> iterateStream(int from, int step, int limit) {
        return IntStream.iterate(from, i -> i + step) // next int
                .limit(limit / step) // only numbers in range
                .boxed()
                .collect(toList());
    }


    public static void rangeStep(double from, double to, double step) {

        while (from <= to) {
            System.out.println(from);
            from = from + step;
        }

    }

    public static List<Double> dictToList(Dictionary<Integer, double[][]> dataDict) {

        List<Double> all_data = new ArrayList<Double>();

        Enumeration<double[][]> element = dataDict.elements();

        while (element.hasMoreElements()) {
            double[][] a = element.nextElement();

            for (double[] i : a) {
                for (double j : i) {
                    all_data.add(j);

                }
            }
        }

        return all_data;
    }


    public static double vectorMagnitude(double[] vector, boolean round) {

        double magnitude;

        magnitude = Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[1], 2));

        if (round){
            magnitude = Math.round(magnitude);
        }

//        System.out.println("Length / Magnitude of Vector: " + );

        return magnitude;
    }


    public static String gln() {
        int lineNo = Thread.currentThread().getStackTrace()[2].getLineNumber();

        String toReturn = "\nLine: " + lineNo + " ";

        return toReturn;
    }

    public static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }

        return sortedHashMap;
    }


    public static double[] multiplyArray(double[] array1, double[] array2) {

        double[] newArray = new double[array1.length];

        for (int i = 0; i < array1.length; i++) {
            newArray[i] = array1[i] * array2[i];
        }

        return newArray;
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
