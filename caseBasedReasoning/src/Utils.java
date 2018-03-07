public class Utils {

    public static double calcDistance(int x1, int y1, int x2, int y2) {
//        Function that returns  distance for given coordinates of any two points

        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

        return distance;
    }

//    public static double calculateTotalDistance(int[][] arrIn) {
////        Function that returns total Euclidean distance for given coordinates in the array
//        double distance = 0;
//
//        for (int i = 0; i < arrIn.length - 1; i++) {
//
//            double x1 = arrIn[i][1];
//            double y1 = arrIn[i][2];
//            double x2 = arrIn[i + 1][1];
//            double y2 = arrIn[i + 1][2];
//
//            distance += calcDistance(x1, y1, x2, y2);
//
////            System.out.println(x1 + ", " + y1 + " | " +  x2 + ", " + y2 + " | " +  distance );
//
//        }
//
//        return distance;
//    }


}
