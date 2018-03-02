import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Utils {

    public static int calcFactorial(int input) {
//  This function calculates factorial of given integer and return it
        int i, fact = 1;

        for (i = 1; i <= input; i++) {
            fact = fact * i;
        }
//        System.out.println("Utils of " + input + " is: " + fact);
        return fact;
    }

    public static double calcDistance(double x1, double y1, double x2, double y2) {
//        Function that returns Euclidean distance for given coordinates of any two points

        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

        return distance;
    }

    public static void printArray1(int[][] arrIn) {
// Method prints two dimensional array

        for (int i = 0; i < arrIn.length; i++) {
            for (int j = 0; j < arrIn[0].length; j++) {
                System.out.print(arrIn[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public static void printArray2(int[] arrIn) {
    // Method prints one dimensional array
        for (int i = 0; i < arrIn.length; i++) {
            System.out.print(arrIn[i] + " ");
        }
        System.out.println("\n");
    }

    public static double calculateTotalDistance(int[][] arrIn) {
//        Function that returns total Euclidean distance for given coordinates in the array
        double distance = 0;

        for (int i = 0; i < arrIn.length - 1; i++) {

            double x1 = arrIn[i][1];
            double y1 = arrIn[i][2];
            double x2 = arrIn[i + 1][1];
            double y2 = arrIn[i + 1][2];

            distance += calcDistance(x1, y1, x2, y2);

//            System.out.println(x1 + ", " + y1 + " | " +  x2 + ", " + y2 + " | " +  distance );

        }

        return distance;
    }


    public static void swapCities(int[][] cities, int city1, int city2) {
//  This method coded specifically for swapping two cities in the array of cities
        int[] tempCity = cities[city1];
        cities[city1] = cities[city2];
        cities[city2] = tempCity;

    }

    public static int[] appendArray(int[] array, int[] x) {
//    Method that takes an array and append another array to it.
//    This method returns new array with appended new array to it

        int[] result = new int[array.length + x.length];

        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }

        int m = x.length;

        for (int l = 0; l < x.length; l++) {

            int toAppend = x[l];

            result[result.length - m] = toAppend;
            m--;
        }

        return result;

    }

//    public static void (int arr[], int start, int end) {
//        //Method that takes an array and reverse order of this array for specified range
//
//        int temp;
//        while (start < end) {
//            temp = arr[start];
//            arr[start] = arr[end];
//            arr[end] = temp;
//            start++;
//            end--;
//        }
//    }

    public static void revereseArray(int[] array) {

        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    public static void swap(int[] input, int val1, int val2) {
//    Method that takes an array and two indexes and swap them in this array

        int temp = input[val1];
        input[val1] = input[val2];
        input[val2] = temp;
    }

    public static int getLengthFromFile(String fileName) throws FileNotFoundException {
        // This function read data from file and checks the length

        String absPath = System.getProperty("user.dir") + "/src/" + fileName;

        int arrayLength = 0;

        Scanner scanner = new Scanner(new File(absPath));

        while (scanner.hasNextInt()) {
            arrayLength++;
            scanner.nextInt();
        }

        return arrayLength;
    }

    public static int[][] readToArray(String fileName) throws FileNotFoundException {
// This function read data from file and put values in two dimensional array
// Before assign the values to new array, checks the length required for new array

        String absPath = System.getProperty("user.dir") + "/src/" + fileName;

        int arrayLength = 0;

        arrayLength = getLengthFromFile(fileName);

        Scanner scanner = new Scanner(new File(absPath));

        int[][] valsFromfile = new int[arrayLength / 3][3];

        while (scanner.hasNextInt()) {

            for (int i = 0; i < valsFromfile.length; i++) {
                for (int j = 0; j < valsFromfile[0].length; j++) {
                    int valueIn = scanner.nextInt();
                    valsFromfile[i][j] = valueIn;
                }
            }

        }
        return valsFromfile;
    }


    public static void printAdjacencyMatrix(int[][] matrix) {
        System.out.println("Adjacency matrix values:");

        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[0].length; k++) {
                System.out.print(matrix[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printAdjacencyMatrixDouble(double[][] matrix) {
        System.out.println("Adjacency matrix values:");

        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[0].length; k++) {
                System.out.print(matrix[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static double[][] makeMatrixOfCities(int[][] data, int numberOfCities) {

        double[][] matrixDistances = new double[numberOfCities][numberOfCities];

        for (int i = 0; i < matrixDistances.length; i++) {
            for (int j = 0; j < matrixDistances[0].length; j++) {

                double x1 = data[i][1];
                double y1 = data[i][2];
                double x2 = data[j][1];
                double y2 = data[j][2];

                double distance = Utils.calcDistance(x1, y1, x2, y2);

                matrixDistances[i][j] = distance;

            }
        }

        return matrixDistances;

    }


    public static double calculateDistanceforPath(double[][] dataIn, int[] path) {

        double totalDistance = 0;

        for (int i = 0; i < path.length - 1; i++) {
            totalDistance += dataIn[path[i]][path[i + 1]];
        }

        totalDistance += dataIn[path[0]][path[path.length - 1]];

        return totalDistance;
    }

    public static int[] initPath(int numberOfCities) {

        int[] path = new int[numberOfCities];

        for (int i = 0; i < numberOfCities; i++) {
            path[i] = i;
        }

        return path;

    }


}
