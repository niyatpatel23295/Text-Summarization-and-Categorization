/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saransh;

import java.util.Scanner;

/**
 *
 * @author Shubham
 */
public class Saaransh {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[] val = new int[5];
        System.out.println("Enter 5 values to sort: ");
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {

            val[i] = in.nextInt();
            System.out.print(val[i] + " ");
        }
        int temp, k, flag;
        for (int i = 0; i < 5; i++) {
            temp = val[i];
            k = i;
            for (int j = i; j < 5; j++) {
                if (temp < val[j]) {
                    temp = val[j];
                    k = j;
                }
            }
            flag = temp;
            val[k] = val[i];
            val[i] = temp;

        }
        System.out.println();
        for (int i = 0; i < 5; i++) {

            System.out.print(val[i] + " ");
        }
    }
}
