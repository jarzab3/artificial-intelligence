package com.company;

public class Main {

    public static void main(String[] args) {

        String path1 = "/Users/adamj/Documents/programming/artificial-intelligence/coursework-2/src/com/company/cw2DataSet1.csv";
        String path2 = "/Users/adamj/Documents/programming/artificial-intelligence/coursework-2/src/com/company/cw2DataSet2.csv";

        int[][] trainingSet = MyUtils.readFile(path1);
        int[][] testingSet = MyUtils.readFile(path2);


        Classification classification = new Classification(false);

//        System.out.println(Arrays.toString(trainingSet[0]));

//        System.out.println(MyUtils.calcDistance(n, n1, 64));

//      First fold test
        System.out.println("\n----------->>>Start first fold test<<<-----------\n");
        classification.findMathForAllData(trainingSet, testingSet);

//      Second fold test
        System.out.println("\n\n----------->>>Start second fold test<<<-----------\n\n");
        classification.findMathForAllData(testingSet, trainingSet);


    }
}
