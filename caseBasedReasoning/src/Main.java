import java.util.Scanner;

public class Main {


    private static int[][] garden = {{5, 5}, {10, 10}, {20, 20}, {30, 30}};

    private static String[] plants = {"Holly", "Oak", "Maple", "Palm"};


    private static String[] plantsData = {"Mango", "Teak", "Neem", "Pongam", "Rose"};

    private static int[] plantsSizes = {1, 2, 3, 4, 5};

//    TODO change to square feet subtrack the plant size and see which is the best

//  Add largest existing plant in the garden. If nothing then value is 0, if Mango then 1, Teak 2, Neem 3, Pongam 4

    public static void main(String[] args) {

        System.out.println("Welcome in Lab 12");

        Scanner scanner = new Scanner(System.in);

//      Read width from a user
        System.out.print("Please type width: ");
        String widthInput = scanner.nextLine();
        int width = Integer.parseInt(widthInput);

//      Read height from a user
        System.out.print("Please type height: ");
        String heightInput = scanner.nextLine();
        int height = Integer.parseInt(heightInput);

//      Read the largest tree in the garden from a user
        System.out.print("Please type largest existing plant in your garden: ");
        String largestPlant = scanner.nextLine();

//        Find matching plant in the database and retrieve its size value

        int largestPlantValue = 999;

        for (int i = 0; i < plantsData.length; i++) {

//            Debug
//            System.out.println(largestPlant.equals(plantsData[i]));

            if(largestPlant.equals(plantsData[i])){

                largestPlantValue = i;
            }
        }


        int largestPlantValueData;

        if (largestPlantValue == 999) {
            largestPlantValueData = 0;
        }else{
            largestPlantValueData = plantsSizes[largestPlantValue];
        }

//        Debug
//        System.out.println("debug" + largestPlantValueData);

//        Next steps

        int userQuery[] = {width, height};

//        System.out.println("Your input: " + width + " " + height);

//      Calculate distance for the first data and keep it as a shortest distance.

        double shortestDistance = Utils.calcDistance(userQuery[0], userQuery[1], garden[0][0], garden[0][1]);

        int plant = 0;

        int shortestInt = (int) shortestDistance;


        double bestValue = shortestDistance;
//        largestPlantValueData;

        double shortestDistances[] = new double[garden.length];

        for (int i = 0; i < garden.length; i++) {

            double shortSearch = Utils.calcDistance(userQuery[0], userQuery[1], garden[i][0], garden[i][1]);

//            System.out.println("Debug distance " + shortSearch);
//            System.out.println("Debug distance - plant value " + (shortSearch - largestPlantValueData));

            double bestValueSearch = shortSearch - largestPlantValueData;


            if (bestValueSearch < bestValue) {
//                System.out.println("Found best plant for you!" + i);
                bestValue = bestValueSearch;

                plant = i;
            }

            shortestDistances[i] = shortSearch;

        }

        System.out.println("\n " );
        System.out.println("debug " + plant + "\n"  );

        for (int i = 0; i < shortestDistances.length; i++) {

            System.out.println("Shortests distnaces " +  shortestDistances[i]);

        }

        System.out.println("\n");



        System.out.println("\nThe best plant for you is: " + plants[plant]);


    }
}
