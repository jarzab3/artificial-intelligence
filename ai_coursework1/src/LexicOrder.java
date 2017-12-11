import java.util.*;

public class LexicOrder {

    public static void order(){
        int vals[] = {0, 1, 2};
        boolean stopWhile = true;

        System.out.println("Values at the start");

        for(int p = 0; p <vals.length; p++){
            System.out.print(vals[p] + " ");
        }

        System.out.println();

        int largestI = -1;

        while(stopWhile){

            for (int i = 0; i < vals.length -1; i++){
                if (vals[i] < vals[i+ 1]){
                    largestI = i;
                }
            }

            if (largestI == -1){
                stopWhile = false;
                System.out.println("Finished");
            }

            int largestJ = -1;

            for (int j = 0; j < vals.length -1; j++){
                if(vals[j] <vals[largestI]){
                    largestJ = j;
                }
            }

            Utils.swap(vals, largestI, largestJ);

            int[] newArray = Arrays.copyOfRange(vals, 0, largestI);

            Collections.reverse(Arrays.asList(newArray));

            Utils.appendArray(vals, newArray);

        }



    }
    public void test() {

        int[] va = {1,2,3};

        int[] v1 = {9,9,9};



        va = Utils.appendArray(va, v1);

        Utils.revereseArray(va, 0, va.length -1);

        for(int p = 0; p <va.length; p++){
            System.out.print(va[p] + " ");
        }


    }



}

