import java.util.Random;

public class Main {

    public static int data[][] = {{1, 1, 1}, {2, 5, 5}, {3, 10, 3}, {4, 2, 7},{5, 1, 5}, {6,4,4 }, {7,4,4 }, {8, -3, 30}, {9, 1, 0}};

    static Random rand = new Random();

    public static void main(String[] args) {

        System.out.println("Welcome to AI coursework 1\n");

//        Utils.printArray(data);
        Utils.calculateTotalDistance(data);

        int possibleRoutes = 0;

        int numberOfCities = data.length;

        possibleRoutes = Utils.calcFactorial(numberOfCities - 1);

//        System.out.println("Possible routes to travel: " + possibleRoutes);
//        double distance = Utils.calculateTotalDistance(data);
//        System.out.println(distance);



        LexicOrder lex = new LexicOrder();

        lex.order();
//        lex.test();













    }
}






//        for (int i = 0; i < 5; i++) {
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
//
