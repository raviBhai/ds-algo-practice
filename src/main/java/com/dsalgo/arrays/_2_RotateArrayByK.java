package com.dsalgo.arrays;

/*
Given input array. Rotate is left by K places.

Exmaple -
Input - 1,2,3,4,5,6,7
K - 3
Output - 4,5,6,7,1,2,3

Algorithm :



Taking from the example, array size is 7
K can be anything, like 3 or 7 or 11 or 18

If K is 7, and array is rotated by K elements, we get the same array as input array,
If K is 11, then it is same as 7+4, that is, rotate the array by 4 places.
If K os 18, then it is same as 7+7+4, that is, rotate the array by 4 places.

So, take modulo of K and then rotate.

K = K % array.length

Brute force :

Step_1 : Take first K elements in a temp array.
Step_2 : Left shift the remaining elements
Step_3 : Then copy the elements from temp array to the original input array

Time complexity -
Step_1 : iterate over K elements - O(K)
Step_2 : iterate over n-K elements - O(n-K)
Step_3 : iterate over K elements - O(K)

Overall time complexity : O( K + n-k + K ) = O(n + K)
Space complexity : Extra space - O(K)


Optimized :
Input - 1,2,3,4,5,6,7
K - 3
Output - 4,5,6,7,1,2,3

Step_1 : Reverse first K elements                    :       3,2,1,4,5,6,7
Step_2 : Then reverse the remaining n-K elements     :       3,2,1,7,6,5,4
Step_3 : Then reverse the entire array               :       4,5,6,7,1,2,3

Time complexity -
Step_1 : O(K)
Step_2 : O(n-K)
Step_3 : O(n)

Overall time complexity - O(K + n-K + n) = O(2n)
Space complexity - O(1)

We notice that time complexity has slightly increased from Brute force approach, but we saved extra space

 */

public class _2_RotateArrayByK {

    private static void solve(int[] input, int k) {
        k = k % input.length;

        // store first K elements in temp
        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = input[i];
        }

        // left shift remaining elements
        for (int i = k; i < input.length; i++) {
            input[i - k] = input[i];
        }

        // copy from temp to input array
        for (int i = input.length - k; i < input.length; i++) {
            input[i] = temp[i - (input.length - k)];
        }
    }

    private static void optimized(int[] input, int k) {
        reverse(input, 0, k - 1);
        reverse(input, k, input.length - 1);
        reverse(input, 0, input.length - 1);
    }

    private static void reverse(int[] array, int start, int end) {
        int temp = 0;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 7};
        solve(input, 3);
        print(input);

        System.out.println();

        int[] input2 = {1, 2, 3, 4, 5, 6, 7};
        optimized(input2, 3);
        print(input2);

        System.out.println();

        int[] input3 = {1, 2, 3, 4, 5, 6, 7, 8};
        optimized(input3, 3);
        print(input3);
    }

    private static void print(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}
