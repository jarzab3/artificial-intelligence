import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class Utils {

    public static int calcFactorial(int input) {

        int i, fact = 1;

        for (i = 1; i <= input; i++) {
            fact = fact * i;
        }
//        System.out.println("Utils of " + input + " is: " + fact);
        return fact;
    }

    public static double calcDistance(double x1, double y1, double x2, double y2) {

        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

        return distance;
    }

    public static void printArray(int[][] arrIn) {

        for (int i = 0; i < arrIn.length; i++) {
            for (int j = 0; j < arrIn[0].length; j++) {
                System.out.print(arrIn[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double calculateTotalDistance(int[][] arrIn) {

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

        int[] tempCity = cities[city1];
        cities[city1] = cities[city2];
        cities[city2] = tempCity;

    }

    public static int[] appendArray(int[] array, int[] x) {

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

    public static void revereseArray(int arr[], int start, int end)
    {
        int temp;
        while (start < end)
        {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void swap(int[] input, int val1, int val2) {

        int temp = input[val1];
        input[val1] = input[val2];
        input[val2] = temp;

    }

    public static void readFile() throws FileNotFoundException {

        String filename = "array.txt";


        try (Scanner sc = new Scanner(new File(filename))) {

            int rows = 0;
            int columns = 0;
            int[][] a = new int[rows][columns];

            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    if (sc.hasNextInt()) {
                        a[i][j] = sc.nextInt();
                    }
                }
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.err.println("File was not found. Make sure the file exist in specified directory.");
            System.err.println("Message: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("File could not be opened.");
            System.err.println("Message: " + e.getMessage());
        }

        //End of read file program
    }
}
