/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab01;

/**
 *
 * @author adam
 */
public class sorting {

	public sorting() {
		System.out.println("Sorting class");
	}

	/**
	 * This method takes an array and sort it.
	 *
	 * @author Adam J
	 * @return Returns a sorted array.
	 *
	 */
	public int[][] sortArray2(int[][] arrayIn, int b_row, int b_column) {
		int[][] sortedArray = new int[arrayIn.length][arrayIn[0].length];

		for (int row = 0; row < arrayIn.length; row++) {
			for (int column = 0; column < arrayIn[row].length; column++) {

				System.out.println(
						"The row is: " + row + " and column is: " + column + "   input " + b_column + "  " + b_row);

				// System.out.printf("%d | %d || column %s | row %s", b_column,
				// b_row, column, row);
			}
		}
		return sortedArray;
	}

	public void sortnew(int[][] multiArray) {

		System.out.println("This is a multi array printig function.");

		for (int row = 0; row < multiArray.length; row++) {
			for (int column = 0; column < multiArray[row].length; column++) {
				// System.out.println("The row is: " + row + " and column is: "
				// + column + ". The value: " + multiArray[row][column]);

			}
		}

	}
	
	public void sort_teraz(int[][] array2) {
		
	
    for (int row = 0; row < array2.length; row++){
    	
    	System.out.println("sorting "+ array2.length * array2.length);

        for(int column = 0; column < array2[0].length; column++) {
             
        	for (int columnTwo = column + 1; columnTwo< array2[0].length; columnTwo++){

               if(array2[row][columnTwo]>array2[row][column])

                {

                int temp=array2[row][column];

                array2[row][column] = array2[row][columnTwo];
                array2[row][columnTwo]=temp;

                }
               
            }

        }
        
     }
    
	}
//
//	public void sort(int[][] arr) {
//		
//		for (int col = 0; i < arr[0].length)
//
//		for (int i = 0; i < arr.length - 1; i++) {
//
//			int currentMin = arr[i];
//			int currentMinIndex = i;
//			
//			for (int j = i + 1; j < arr.length; j++) {
//				if (currentMin > arr[j]) {
//					currentMin = arr[j];
//					currentMinIndex = j;
//
//				}
//			}
//		}
//		
//	}

}
