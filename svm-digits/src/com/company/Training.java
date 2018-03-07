package com.company;

import java.text.DecimalFormat;
import java.util.*;

public class Training {

    private double[] t = {0, 0, 7, 16, 16, 16, 2, 0, 0, 0, 14, 14, 9, 16, 2, 0, 0, 2, 16, 6, 0, 9, 7, 0, 0, 4, 16, 1, 0, 8, 8, 0, 0, 6, 16, 0, 0, 12, 8, 0, 0, 5, 16, 4, 2, 16, 5, 0, 0, 0, 15, 16, 16, 13, 0, 0, 0, 0, 8, 13, 10, 2, 0, 0}; //0
    private double[] t1 = {0, 0, 13, 16, 7, 0, 0, 0, 0, 2, 16, 16, 15, 0, 0, 0, 0, 0, 4, 14, 16, 0, 0, 0, 0, 0, 0, 12, 13, 0, 0, 0, 0, 0, 0, 15, 12, 0, 0, 0, 0, 0, 11, 16, 1, 1, 1, 0, 0, 0, 16, 16, 16, 16, 6, 0, 0, 0, 13, 16, 16, 12, 0, 0};//2
    private double[] t2 = {0, 0, 3, 14, 16, 16, 6, 0, 0, 0, 8, 16, 16, 16, 9, 0, 0, 0, 0, 0, 7, 16, 6, 0, 0, 0, 0, 0, 12, 15, 1, 0, 0, 0, 2, 13, 16, 16, 7, 0, 0, 0, 2, 16, 16, 14, 2, 0, 0, 0, 6, 16, 9, 0, 0, 0, 0, 0, 7, 14, 2, 0, 0, 0}; //7

    private double[][] data = {t, t1, t2};

    private double[] w;

    private double b;

    public double[] getT() {
        return t;
    }

    public double[] getT1() {
        return t1;
    }

    public double[] getT2() {
        return t2;
    }

    public Training() {

    }

    //  Class  Variables
    double maxFeatureValue;
    double minFeatureValue;

    int yi = 0;

    /**
     * {||w|| : [w, b]}
     *
     * @param dataDict opt_dict = {}
     */
//    Train
    public void fit(Dictionary<Integer, double[][]> dataDict) {

        Hashtable<Double, double[][]> optDict = new Hashtable<>();

        double[][] transforms = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};

        List<Double> all_data;

        all_data = MyUtils.dictToList(dataDict);

        this.maxFeatureValue = Collections.max(all_data);

        this.minFeatureValue = Collections.min(all_data);

        double[] stepSizes = {this.maxFeatureValue * 0.1,
                this.maxFeatureValue * 0.01,
                this.maxFeatureValue * 0.001};

        int bRangeMultiple = 2;

        int bMultiple = 5;

        double latestOptimum = this.maxFeatureValue * 10;

        for (int s = 0; s < stepSizes.length; s++) {
            double step = stepSizes[s];

            this.w = new double[]{latestOptimum, latestOptimum};

            System.out.println("---===> " + this.w[0] + "  " + this.w[1]);

            boolean optimized = false;

            while (!optimized) {

                double from = -1 * this.maxFeatureValue * bRangeMultiple;
                double limit = this.maxFeatureValue * bRangeMultiple;
                double rangeStep = step * bMultiple;

                while (from <= limit) {
                    this.b = from;
                    from = from + rangeStep;

                    for (int ii = 0; ii < transforms.length; ii++) {
                        double[] transformation = transforms[ii];

                        //TODO Data type for transforms need to be changed
                        //Trans w: [ 63.2  63.2] t: [-1, 1] w_t: [-63.2  63.2]
                        //Trans w: [ 63.2  63.2] t: [-1, -1] w_t: [-63.2 -63.2]
                        //Example above

                        double[] wt = MyUtils.multiplyArray(this.w, transformation);

                        boolean foundOption = true;

//                        Iterate through all data
                        Enumeration<Integer> key = dataDict.keys();
                        while (key.hasMoreElements()) {
                            int i = key.nextElement();

                            double[][] value = dataDict.get(i);

                            for (double[] xi : value) {

                                this.yi = i;

                                if (!((this.yi * MyUtils.dotProduct(wt, xi)) + this.b >= 1)) {
                                    foundOption = false;
                                }

                            }

                        }

//                      Then check if found option
                        if (foundOption) {

                            double[][] tempArray = {wt, {this.b}};

                            optDict.put(MyUtils.vectorMagnitude(wt, false), tempArray);

                        }
                    }
                }


                if (this.w[0] < 0) {
                    optimized = true;
                    System.out.println("Optimized one step");
                } else {

                    for (int i = 0; i < this.w.length; i++)
                        this.w[i] = this.w[i] - step;
                }
            }

            ArrayList<Float> norms = new ArrayList<Float>();

            Enumeration<Double> key = optDict.keys();

            while (key.hasMoreElements()) {

                double keyToAdd = key.nextElement();

                DecimalFormat df = new DecimalFormat("#.00000");

//                double -df.format(keyToAdd);
//                keyToAdd = Math.round(keyToAdd*100)/100.0d;

                norms.add((float)keyToAdd);
            }

            Collections.sort(norms);





//                        # ||w|| : [w,b]

            double[][] optChoice = optDict.get(norms.get(0));

            this.w = optChoice[0];
            this.b = optChoice[1][0];

            latestOptimum = optChoice[0][0] + step * 2;


            //            # ||w|| : [w,b]


//            Iterate through all data
            Enumeration<Integer> key1 = dataDict.keys();
            while (key.hasMoreElements()) {
                int i = key1.nextElement();

                double[][] value = dataDict.get(i);

                for (double[] xi : value) {

                    this.yi = i;

                }
            }

        }
    }


    public double hyperPlane(double x, double[] wl, double b, double v) {
        double result;

        result = (-wl[0] * x - b + v) / wl[1];
        System.out.println("Hyperplane debug: " + result + " x " + x + " wl 1 " + wl[0] + " 2 " + wl[1] + " b " + b + " v " + v);
//        Hyperplane: -4.693103448279819. INPUTS: x: -0.9 w: [ 0.232 -0.232] b: 0.11999999999965638 v: 1

        return result;
    }


    public void getResults() {

        double[] dataRange = {this.minFeatureValue * 0.9, this.maxFeatureValue * 1.1};

        double hypXmin = dataRange[0];
        double hypXmax = dataRange[1];

//      (w.x+b) = 1
//      Positive support vector hyperplane
        double psv1 = hyperPlane(hypXmin, this.w, this.b, 1);
        double psv2 = hyperPlane(hypXmax, this.w, this.b, 1);

        System.out.println("Positive hyperplane: " + hypXmin + " " + hypXmax + " " + psv1 + " " + psv2);


//      (w.x + b) = -1
//      Negative support vector hyperplane
        double nsv1 = hyperPlane(hypXmin, this.w, this.b, -1);
        double nsv2 = hyperPlane(hypXmax, this.w, this.b, -1);

        System.out.println("Negative hyperplane: " + hypXmin + " " + hypXmax + " " + nsv1 + " " + nsv2);

//      (w.x+b) = 0
//      Boundary support vector hyperplane
        double db1 = hyperPlane(hypXmin, this.w, this.b, 0);
        double db2 = hyperPlane(hypXmax, this.w, this.b, 0);

        System.out.println("Boundary hyperplane: " + hypXmin + " " + hypXmax + " " + db1 + " " + db2);
    }


    /**
     * Sign (x * w + b)
     *
     * @param features
     */
    public double predict(double[] features) {

        double classfication = MyUtils.sign(MyUtils.dotProduct(features, this.w) + this.b);

        return classfication;
    }

}


//
//        for (int i = 0; i < data.length; i++) {
//            double[] featureSet = data[i];
//
//            for (int j = 0; j < featureSet.length; j++) {
//                all_data.add(featureSet[j]);
//            }
//        }
