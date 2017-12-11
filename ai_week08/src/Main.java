import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;
import java.util.Random;

public class Main {

    static int nodes = 4;
    static int data = 20;

    static double[][] arrayNodes = new double[nodes][3];
    static double[][] arrayData = new double[data][2];

    static Random rand = new Random();


    public static double calcDistance(double x2, double y2, double x1, double y1  ){

        double distance = Math.sqrt( Math.pow((x2 - x1), 2) +  Math.pow((y2 - y1), 2));

        return distance;
    }

    public static void readArray(double inArray[][]){

        for (int i = 0; i < inArray.length; i++)
        {
            for (int j = 0; j < inArray[0].length; j++) {

                System.out.print(inArray[i][j] + "\n" );

            }

            System.out.println();
        }
    }



    public static void main(String[] args) {
        System.out.println("Hello World!\n");

        for (int i = 0; i < arrayNodes.length; i++)
        {
            for (int j = 0; j < arrayNodes[0].length; j++) {

                int randomValue = rand.nextInt(10);

                arrayNodes[i][j] = randomValue;

            }
        }


        for (int i = 0; i < arrayData.length; i++)
        {
            for (int j = 0; j < arrayData[0].length; j++) {

                int randomValue = rand.nextInt(15);

                arrayData[i][0] = 5;
                arrayData[i][1] = randomValue;

            }
        }


        for (int i = 0; i < arrayNodes.length-1; i++)
        {
            for (int j = 0; j < arrayNodes[0].length-1; j++) {

            }

            double x1 = arrayNodes[i][0];
            double y1 = arrayNodes[i][1];
            double x2 = arrayNodes[i+1][0];
            double y2 = arrayNodes[i+1][1];

            double dist =  calcDistance(x2, y2, x1, y1);

//            System.out.println(x1 + " " + y1 + " " +  x2 + "  " + y2 + " " + dist);

            arrayNodes[i][2] = dist;

        }


        double smallest;

        double x11 = arrayNodes[1][0];
        double y11 = arrayNodes[1][1];

        double x22 = arrayData[0][0];
        double y22 = arrayData[0][1];

        smallest = calcDistance(x22, y22, x11, y11);

        for (int i = 0; i < arrayData.length; i++)
        {
            for (int j = 0; j < arrayData[0].length; j++) {

            }

            double x1 = arrayNodes[1][0];
            double y1 = arrayNodes[1][1];

            double x2 = arrayData[i][0];
            double y2 = arrayData[i][1];

            double dist = calcDistance(x2, y2, x1, y1);

            if (dist < smallest){
                smallest = dist;
            }

            System.out.println(x2 + " " + y2 + " " +  x1 + "  " + y1 + " " + smallest);


        }


        System.out.println("This is the smallest distance: "+ smallest);




        System.out.println("Nodes:\n");
//
//        readArray(arrayNodes);
//
//        System.out.println("Data:\n");
//
//        readArray(arrayData);










    }

}
