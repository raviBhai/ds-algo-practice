package com.dsalgo.arrays;
/*
Given input array which has zero and non-zero elements.
Move the zeros to the end of the array

Algorithm :
Take a pointer "j" and point it to the first zero from the start.
Take another pointer "i" and move it from j+1 to end of the array.

If "i" is non-zero, swap i and j, and move j to the next position.
"i" is in a for loop and will always move to the next position

 */

public class _3_MoveZerosToLast {


    private static void solve(int[] input) {
        int j = -1;
        for (int k = 0; k < input.length; k++) {
            if (input[k] == 0) {
                j = k;
                break;
            }
        }
        if (j == -1) {
            System.out.println("No zero elements in the array");
            return;
        }

        for (int i = j + 1; i < input.length; i++) {
            if (input[i] != 0) {
                int temp = input[i];
                input[i] = input[j];
                input[j] = temp;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 0, 0, 0, 0, 5, 6, 0, 7, 8, 0, 9, 10};
        solve(input);
        print(input);
    }

    private static void print(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
    }

}
