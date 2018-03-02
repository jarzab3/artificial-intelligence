import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class nearestNeighbour {
    private static int numberOfCities;
    private static Stack<Integer> stackOfCities;

    public nearestNeighbour() {

        stackOfCities = new Stack<Integer>();

    }

    public static void tsp(int adjacencyMatrixInput[][]) {
        
        numberOfCities = adjacencyMatrixInput[1].length - 1;
        int[] visited = new int[numberOfCities + 1];
        visited[1] = 1;
      
        boolean indicator = false;
        
        stackOfCities.push(1);

        int Citiy, dst = 0, i;

        int min = Integer.MAX_VALUE;
        
        System.out.print(1 + "\t");

        while (!stackOfCities.isEmpty()) {
            
            Citiy = stackOfCities.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            
            while (i <= numberOfCities) {
                if (adjacencyMatrixInput[Citiy][i] > 1 && visited[i] == 0) {
                    if (min > adjacencyMatrixInput[Citiy][i]) {
                        min = adjacencyMatrixInput[Citiy][i];
                        dst = i;
                        indicator = true;
                    }
                }
                i++;
            }
            if (indicator) {
                visited[dst] = 1;
                stackOfCities.push(dst);
                System.out.print(dst + "\t");
                indicator = false;
                continue;
            }
            
            stackOfCities.pop();
        
        }
    }
}
