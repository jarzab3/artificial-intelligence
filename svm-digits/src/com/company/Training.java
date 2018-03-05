package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Training {

    private double[] t = {0, 0, 7, 16, 16, 16, 2, 0, 0, 0, 14, 14, 9, 16, 2, 0, 0, 2, 16, 6, 0, 9, 7, 0, 0, 4, 16, 1, 0, 8, 8, 0, 0, 6, 16, 0, 0, 12, 8, 0, 0, 5, 16, 4, 2, 16, 5, 0, 0, 0, 15, 16, 16, 13, 0, 0, 0, 0, 8, 13, 10, 2, 0, 0}; //0
    private double[] t1 =  {0, 0, 13, 16, 7, 0, 0, 0, 0, 2, 16, 16, 15, 0, 0, 0, 0, 0, 4, 14, 16, 0, 0, 0, 0, 0, 0, 12, 13, 0, 0, 0, 0, 0, 0, 15, 12, 0, 0, 0, 0, 0, 11, 16, 1, 1, 1, 0, 0, 0, 16, 16, 16, 16, 6, 0, 0, 0, 13, 16, 16, 12, 0, 0};//2
    private double[] t2 = {0, 0, 3, 14, 16, 16, 6, 0, 0, 0, 8, 16, 16, 16, 9, 0, 0, 0, 0, 0, 7, 16, 6, 0, 0, 0, 0, 0, 12, 15, 1, 0, 0, 0, 2, 13, 16, 16, 7, 0, 0, 0, 2, 16, 16, 14, 2, 0, 0, 0, 6, 16, 9, 0, 0, 0, 0, 0, 7, 14, 2, 0, 0, 0}; //7

    double[][] data = {t, t1, t2};

    public double[] getT() {
        return t;
    }

    public double[] getT1(){
        return t1;
    }

    public double[] getT2() {
        return t2;
    }

    public Training(){

    }

    /**
     * {||w|| : [w, b]}
     * @param data
     * opt_dict = {}
     */
//    Train
    public void fit(double[][] data){
        double[] opt_dict;

        int[][] transforms = {{1,1}, {-1, 1}, {-1, -1}, {1, -1}};

//        double[][] all_data;

        List<Double> all_data = new ArrayList<Double>();


        for(int i = 0; i < data.length; i++){
            double[] featureSet = data[i];

            for(int j = 0; j < featureSet.length; j++){
                all_data.add(featureSet[j]);
            }
        }

        double maxFeatureValue = Collections.max(all_data);
        double minFeatureValue = Collections.min(all_data);

        double[] stepSizes = {maxFeatureValue * 0.1,
                maxFeatureValue * 0.01,
                maxFeatureValue * 0.001,
                maxFeatureValue * 0.0001};

        int bRangeMultiple = 5;

        int bMultiple = 5;

        double latestOptimum = maxFeatureValue * 10;

        for(int s = 0; s < stepSizes.length; s++){
            double step = stepSizes[s];

            double[] w = {latestOptimum, latestOptimum};

            boolean optimized = false;

            while (!optimized) {

                double from = -1 * maxFeatureValue * bRangeMultiple;
                double limit = maxFeatureValue * bRangeMultiple;
                double rangeStep = step * bMultiple;
                while (from <= limit) {
                    double b = from / 10.0;
                    from = from + rangeStep;

                    for(int ii = 0; ii < transforms.length; ii++){
                        int[] transformation = transforms[ii];

                        //TODO add w_t
//                        double wt = w * transformation

                        boolean foundOption = true;



//                        for (int[] i : data)
//                        {
//
//                            for (double xi : data[i])
//
//                            System.out.println(i);
//                        }


                    }


                    }
                
//                MyUtils.iterateStream((-1 * (maxFeatureValue * bRangeMultiple)), step * bMultiple, maxFeatureValue * bRangeMultiple);
//                for (int s = 0; s < stepSizes.length; s++) {



            }

        }


        }

    /**
     * Sign (x * w + b)
     * @param features
     */
    public double predict(double[] features){
        double classfication = MyUtils.sign(MyUtils.dotProduct(features, this.t) + 2);

        return classfication;
    }




}
