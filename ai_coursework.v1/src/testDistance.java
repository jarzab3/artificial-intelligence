public class testDistance {

    public static double calcDistance(double x1, double y1, double x2, double y2) {
//        Function that returns Euclidean distance for given coordinates of any two points

        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

        return distance;
    }

}
