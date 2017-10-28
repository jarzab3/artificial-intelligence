/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab01;
/**
 *
 * @author Adam
 */
public class Lab01 {
    private static int [] arrs;
	private static sorting s;

	/**
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {

        print_array p1 = new print_array();

        System.out.println("Hello World!");

        int[] numbers = new int[3];
        
        arrs = new int[] {3,4,2};

        numbers[0] = 3;
        numbers[1] = 4;
        numbers[2] = 2;

        p1.printa(numbers); 
        
        fillArray f1 = new fillArray();
        
        int[] listOddNumber = fillArray.fill1();
        
        p1.printa(listOddNumber);
          
        int[][] multiArray = f1.fill2();
        
        p1.printb(multiArray);
        
        s = new sorting();
      
//        s.sort(multiArray);
               
        int[][] sortedarr  = new int[5][5]; 
        
        s.sort_teraz(multiArray);
        
        p1.printb(multiArray);
        
        
    }
}