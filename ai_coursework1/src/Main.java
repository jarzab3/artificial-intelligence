import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static int data[][] = {{1, 1, 1}, {2, 5, 5}, {3, 10, 3}, {4, 2, 7}, {5, 1, 5}, {6, 4, 4}, {7, 4, 4}, {8, -3, 30}, {9, 1, 0}};

    static Random rand = new Random();

    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to AI coursework 1\n");

//        Utils.printArray1(data);

//        Utils.calculateTotalDistance(data);

        int possibleRoutes = 0;

        int numberOfCities = data.length;

        possibleRoutes = Utils.calcFactorial(numberOfCities - 1);

//        System.out.println("Possible routes to travel: " + possibleRoutes);


//        int vals[] = {0, 1, 2, 4, 5, 6, 7, 8};



//        LexicOrder lex = new LexicOrder();

//        long timestart = System.currentTimeMillis();
//
//        lex.order();
//
//        long timeend = System.currentTimeMillis();
//        long timediff = timeend - timestart;
//
//        System.out.println(timediff);
//
//        String elapsedTime = (new SimpleDateFormat("mm:ss:SSS")).format(new Date(timediff));
//
//        System.out.println(elapsedTime);



//      Provide a file name from which you want to read data. For example "data2.txt" or "data.txt"

        int[][] valuesFromFile = Utils.readToArray("data3.txt");

//        Utils.printArray1(valuesFromFile);

        System.out.println();
        System.out.println();


//
//        depthFirst g = new depthFirst(4);
//
//        g.addEdge(0, 1);
//        g.addEdge(0, 2);
//        g.addEdge(1, 2);
//        g.addEdge(2, 0);
//        g.addEdge(2, 3);
//        g.addEdge(3, 3);
//        g.addEdge(0, 2);
//
//
//        System.out.println("Following is Depth First Traversal " +
//                "(starting from vertex 2)");
//
//
//
//        g.DFS(0);


//Adjacency matrix creation
//        int[][] adjMatrix = Utils.createAdjacencyMatrix();

//        Utils.printAdjacencyMatrix(adjMatrix);


//        TSP nearest neighbour

//            int number_of_nodes;
//            Scanner scanner = null;
//            try
//            {
//                System.out.println("Enter the number of nodes in the graph");
//                scanner = new Scanner(System.in);
//                number_of_nodes = scanner.nextInt();
//                int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];
//                System.out.println("Enter the adjacency matrix");
//                for (int i = 1; i <= number_of_nodes; i++)
//                {
//                    for (int j = 1; j <= number_of_nodes; j++)
//                    {
//                        adjacency_matrix[i][j] = scanner.nextInt();
//                    }
//                }
//                for (int i = 1; i <= number_of_nodes; i++)
//                {
//                    for (int j = 1; j <= number_of_nodes; j++)
//                    {
//                        if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)
//                        {
//                            adjacency_matrix[j][i] = 1;
//                        }
//                    }
//                }
//
//                System.out.println("the citys are visited as follows");
//                tspNearNei tspNearestNeighbour = new tspNearNei();
//
//                tspNearNei.tsp(adjacency_matrix);
//
//            } catch (InputMismatchException inputMismatch)
//            {
//                System.out.println("Wrong Input format");
//            }
//            scanner.close();



        int order[];

//        BigInteger bi1 = new BigInteger("999999");

//        Integer.toString(Utils.calcFactorial(31)
//        long ll =
//        System.out.println(bi1);

        // Set task to run for specific time in seconds
        int timeToRun = 30;

        long endTime = System.currentTimeMillis() + timeToRun * 1000;

        data = valuesFromFile;

        double theBestDistance = Utils.calculateTotalDistance(data);
        System.out.println("Initial total distance: " + theBestDistance + "\n");

        while (System.currentTimeMillis() < endTime) {

            int city1 = rand.nextInt(data.length);
            int city2 = rand.nextInt(data.length);

            Utils.swapCities(data, city1, city2);

            double distance1 = Utils.calculateTotalDistance(data);

            if (distance1 < theBestDistance) {
                theBestDistance = distance1;

                System.out.println("Found better path with distance: " + theBestDistance);

            }
        }

        System.out.println("\nThe best found path has distance: " + theBestDistance + "\n");



//        for (int i = 0; i < 9999999; i++) {
//
//            int city1 = rand.nextInt(data.length);
//            int city2 = rand.nextInt(data.length);
//
//            Utils.swapCities(data, city1, city2);
//
//            double distance1 = Utils.calculateTotalDistance(data);
//
//            if (distance1 < distance) {
//                distance = distance1;
//
//                System.out.println(distance);
//            }
//        }



    }
}

