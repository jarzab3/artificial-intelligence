import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.lang.reflect.Array;
import java.util.*;

public class LexicOrder {

    public static void order() {
        boolean runWhile = true;

        int vals[] = {0, 1, 2, 4, 5, 6, 7, 8};

        System.out.println("Values at the start");
        System.out.println();

        while (runWhile) {

//            Print array to the console
            for (int p = 0; p < vals.length; p++) {
                System.out.print(vals[p] + " ");
            }
            System.out.println();

//            Start math computation below
//            Step 1
            int largestI = -1;

            for (int i = 0; i < vals.length - 1; i++) {
                if (vals[i] < vals[i + 1]) {
                    largestI = i;
                }
            }

            if (largestI == -1) {
                runWhile = false;
                System.out.println("Finished");
                break;
            }

//Exit while loop when runWhile becomes true
            if (runWhile == false)
                break;

//          Step 2
            int largestJ = -1;

            for (int j = 0; j < vals.length; j++) {
                if (vals[largestI] < vals[j]) {
                    largestJ = j;
                }
            }

            Utils.swap(vals, largestI, largestJ);

//            System.out.println("largest is:"+ largestI + "length " + vals.length);

            int[] begArray = Arrays.copyOfRange(vals, 0, largestI +1);

            int[] endArray = Arrays.copyOfRange(vals, largestI + 1, vals.length );

            Utils.revereseArray(endArray, 0, endArray.length - 1);

            vals = Utils.appendArray(begArray, endArray);
        }
    }

    public void test() {

        int[] va = {1, 2, 3};

        int[] v1 = {9, 9, 9};


        va = Utils.appendArray(va, v1);

        Utils.revereseArray(va, 0, va.length - 1);

        for (int p = 0; p < va.length; p++) {
            System.out.print(va[p] + " ");
        }

    }
}