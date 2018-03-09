package com.company;

public class Classification {
    private boolean debug;
    private int secondIteration = 0;

    public Classification(boolean debugMode) {
        debug = debugMode;
    }

    /**
     * This function run training for a single point.
     *
     * @param point
     * @param trainingSet
     * @return boolean: If correctly recognized return true otherwise false
     */
    public boolean findMatch(int[] point, int[][] trainingSet, boolean debug1) {

        double currentDistance;

        int currentDigit = point[64];

        int indexFound = 0;

        double shortestDistance = MyUtils.calcDistance(point, trainingSet[0], 64);

        for (int i = 1; i < trainingSet.length; i++) {
            currentDistance = MyUtils.calcDistance(point, trainingSet[i], 64);

            if (debug1) {
                System.out.println("Current Distance is: " + currentDistance + " Digit is: " + currentDigit + " searched point: " + trainingSet[i][64]);
            }

            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                indexFound = i;
            }
        }

        int foundDigit = trainingSet[indexFound][64];

        boolean matched = (currentDigit == foundDigit);

        if (!(matched)) {

            secondIteration++;

            indexFound = 0;

            shortestDistance = MyUtils.cosineSimilarity(point, trainingSet[0], 64);

            for (int ii = 1; ii < trainingSet.length; ii++) {
                currentDistance = MyUtils.cosineSimilarity(point, trainingSet[ii], 64);


                if (debug1) {
                    System.out.println("Current distance is: " + currentDistance + " Digit is: " + currentDigit + " searched point: " + trainingSet[ii][64]);
                }

                if (currentDistance < shortestDistance) {
                    shortestDistance = currentDistance;
                    indexFound = ii;
                }
            }

            foundDigit = trainingSet[indexFound][64];

            matched = (currentDigit == foundDigit);
        }

//      Printing for debugging purposes
        if (debug) {

            System.out.println("\nSearched digit: " + currentDigit + " found digit: " + foundDigit + ". The shortest distance is: " + shortestDistance + " Matched:" + matched + "\n");

        }

        return matched;
    }


    /**
     * This function run a training for all points from a training data.
     * Training is run on another set of data which is provided
     *
     * @param trainingSet
     * @param testingSet
     */
    public void findMathForAllData(int[][] trainingSet, int[][] testingSet) {

        ProgressBar bar = new ProgressBar();
        System.out.println("Process Starts Now!");
        int totalPrograss = trainingSet.length;
        bar.update(0, totalPrograss);

        int correctGuess = 0;
        int incorrectGuess = 0;

        for (int i = 0; i < trainingSet.length; i++) {

            boolean matched = findMatch(trainingSet[i], testingSet, false);

            if (matched) {
                correctGuess++;
            } else {
                incorrectGuess++;
            }

            bar.update(i, totalPrograss);

        }

        System.out.println("Process Completed!");

        double errorRate = (double) incorrectGuess / (double) correctGuess;

        System.out.println("\nFor this classification found: " + correctGuess + " correctly recognized digits and " + incorrectGuess + " incorrectly recognized digits.");

        System.out.println("\nCorrectly recognized: " + (float) (100 - (errorRate * 10)) + "%");

        System.out.println("\nSecond iteration counter: " + secondIteration);

    }
}
