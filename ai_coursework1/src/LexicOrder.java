import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.reflect.Array;
import java.util.*;


public class LexicOrder {

    private double[][] matrixDistances;
    private double bestDistance = Double.MAX_VALUE;
    private int numberOfCities;
    private int[] bestPath;

    public LexicOrder(double[][] matrixDistancesIn, int numberOfCitiesIn) {
        matrixDistances = matrixDistancesIn;
        numberOfCities = numberOfCitiesIn;
    }

    public double getBestDistance() {
        return bestDistance;
    }

    public int[] getBestPath() {
        return bestPath;
    }



    public int[] optimalSwap(int[] currentPath, int largestI, int largestJ) {

        Utils.swap(currentPath, largestI, largestJ);

        largestI = largestI + 1;

        int[] begArray = Arrays.copyOfRange(currentPath, 0, largestI);

        int[] endArray = Arrays.copyOfRange(currentPath, largestI, currentPath.length);

        Utils.revereseArray(endArray);

        currentPath = Utils.appendArray(begArray, endArray);

        return currentPath;
    }

    public void order() {

        boolean runWhile = true;

        int currentPath[] = Utils.initPath(numberOfCities);

        while (runWhile) {

//          Calculate the current path with city order specified in currentPath
            double currentDistance = Utils.calculateDistanceforPath(matrixDistances, currentPath);

//          Check if new found distance is better than the current
            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
                bestPath = currentPath;
            }

//             In mathematics, the lexicographic or lexicographical order (also known as lexical order, dictionary order, alphabetical order or lexicographic(al) product)
//             is a generalization of the way words are alphabetically ordered based on the alphabetical order of their component letters.
//             This generalization consists primarily in defining a total order over the sequences (often called words in computer science) of elements of a finite totally ordered set,
//             often called alphabet. There are several variants and generalizations of the lexicographical ordering. One variant widely used in combinatorics orders subsets of a given
//             finite set by assigning a total order to the finite set, and converting subsets into increasing sequences, to which the lexicographical order is applied. Another generalization
//             defines an order on a Cartesian product of partially ordered sets; this order is a total order if and only if the factors of the Cartesian product are totally ordered.

//            Start math computation below
//            Step 1
            int largestI = -1;

            for (int i = 0; i < currentPath.length - 1; i++) {
                if (currentPath[i] < currentPath[i + 1]) {
                    largestI = i;
                }
            }

            if (largestI == -1) {
                runWhile = false;
                System.out.println("Finished\n");
                break;
            }

//          Exit while loop when runWhile becomes true
            if (runWhile == false)
                break;

//          Step 2
            int largestJ = -1;

            for (int j = 0; j < currentPath.length; j++) {
                if (currentPath[largestI] < currentPath[j]) {
                    largestJ = j;
                }
            }

            currentPath = optimalSwap(currentPath, largestI, largestJ);

        }

    }

}