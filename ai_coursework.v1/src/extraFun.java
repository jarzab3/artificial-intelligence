//
//        testDistance td = new testDistance();
//
//        double dist = td.calcDistance(5, 4, 5, 3);
//
//        double dist1 = td.calcDistance(5, 3, 4, 5);
//
//        double dist2 = td.calcDistance(4, 5, 6, 1);
//
//        double dist3 = td.calcDistance(6, 1, 5, 4);
//
//
////        System.out.println(dist + " " + dist1+ " " + dist2 +" " + dist3);
//
//
////        TSP nearest neighbour algorithm
////        Below example is not totally completed, required little bit debugging
//
//        int nodes;
//
//        nodes = Utils.getLengthFromFile("data2.txt") / 3;
//
//
//        int adjacencyMatrixCity[][] = new int[nodes + 1][nodes + 1];
//
////        for (int i = 1; i <= nodes; i++) {
////            for (int j = 1; j <= nodes; j++) {
////
////                double x1 = data[i][1];
////                double y1 = data[i][2];
////                double x2 = data[i + 1][1];
////                double y2 = data[i + 1][2];
////
////                int distance = (int) Math.round(Utils.calcDistance(x1, y1, x2, y2));
////
////                adjacencyMatrixCity[i][j] = distance;
////
////            }
////        }
////
////        for (int i = 1; i <= nodes; i++)
////        {
////            for (int j = 1; j <= nodes; j++)
////            {
////                if (adjacencyMatrixCity[i][j] == 1 && adjacencyMatrixCity[j][i] == 0)
////                {
////                    adjacencyMatrixCity[j][i] = 1;
////                }
////            }
////        }
//
////        Utils.printAdjacencyMatrix(adjacencyMatrixCity);
//
////        Uncomment to run above tspNearestNeighbour code
////        nearestNeighbour tspNearestNeighbour = new nearestNeighbour();
////        nearestNeighbour.tsp(adjacencyMatrixCity);
//
//
//
//
//
//
//
//
//
//// ------------------ Main function
//
//
//
//
//
//
//
////        Uncomment below line if want to make sure taht reading is correct
////        Utils.printArray1(data);
//
//
//
