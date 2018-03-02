import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static Random rand = new Random();

    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to AI coursework 1\n");

//       READ data from file in first order
//       Provide a file name from which you want to read data. For example "dataInput.txt" or "dataInput.txt"

        int[][] data = Utils.readToArray("finalTest1.txt");

        int numberOfCities = data.length;

        int possibleRoutes = Utils.calcFactorial(numberOfCities);

//        System.out.println("Possible routes: " + possibleRoutes + "\n");


//      Below code guarantee to check all possible combinations however it is very time consuming, it uses lexicographic order with combination of adjacency array
        double[][] matrixDistances = new double[numberOfCities][numberOfCities];

//      Create matrix of all possible routes between cities
        matrixDistances = Utils.makeMatrixOfCities(data, numberOfCities);

//      Create lexicographic obejct and pass data and number of cities to constructor
        LexicOrder lex = new LexicOrder(matrixDistances, numberOfCities);

//      Start measure the time before, running the algorithm
        long timestart = System.currentTimeMillis();

//      Run function that search for the optimal path
        lex.order();

//      Get the results back, then they will be printed to the console
        double bestDistance = lex.getBestDistance();
        int[] bestPath = lex.getBestPath();

//      Stop time and calculate the time needed to run this search
        long timeend = System.currentTimeMillis();
        long timediff = timeend - timestart;
        String elapsedTime = (new SimpleDateFormat("mm:ss:SSS")).format(new Date(timediff));

//      Print all results to the console
        System.out.println("Time needed to find out the best path: " + elapsedTime);

        System.out.println("The best distance is: " + bestDistance + "\n");

        System.out.println("The best path is: ");

        Utils.printArray2(bestPath);
        
        

     // Set task to run for specific time in seconds and runCode just is for debugging purposes
     int timeToRun = 10;
     boolean runCode = true;

     long endTime = System.currentTimeMillis() + timeToRun * 1000;

     double theBestDistance = Utils.calculateTotalDistance(data);

     if (runCode) {

         System.out.println("Initial total distance: " + theBestDistance + "\n");
     }


     while (System.currentTimeMillis() < endTime && runCode == true) {
     //This algorithm uses simple swapping function in order to try different combinations of cities hence, along the time possibly will find better path


         int leng = Utils.getLengthFromFile("finalTest1.txt") / 3;

         int order[] = new int[leng];



         int city1 = rand.nextInt(data.length);
         int city2 = rand.nextInt(data.length);

         Utils.swapCities(data, city1, city2);

         double distance1 = Utils.calculateTotalDistance(data);

         if (distance1 < theBestDistance) {
             theBestDistance = distance1;

             System.out.println("Found better path with distance: " + theBestDistance);

         }
     }

     if (runCode) {
         System.out.println("\nThe best found path has distance: " + theBestDistance + "\n");
     }


    }
}





