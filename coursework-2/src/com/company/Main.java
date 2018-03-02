package com.company;

public class Main {

    public static void main(String[] args) {

        String path1 = "/Users/adamj/Documents/programming/artificial-intelligence/coursework-2/src/com/company/cw2DataSet1.csv";
        String path2 = "/Users/adamj/Documents/programming/artificial-intelligence/coursework-2/src/com/company/cw2DataSet2.csv";

        Utlis util = new Utlis(false);


        int[][] trainingSet = util.readFile(path1);
        int[][] testingSet = util.readFile(path2);


//        util.printArray(data1[0]);

//        util.printArray2(data1, false);
//        System.out.println(trainingSet.length);
//        System.out.println(testingSet.length);


//        System.out.println(traindata[0].length);

//        util.calcDistance(data1[2], data2[0], 64);

//        util.train(data1[6], data2);
        util.trainForAll(trainingSet, testingSet);

    }
}
