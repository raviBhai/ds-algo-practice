package com.dsalgo.heap;

/**
 * Given input array = {1,3,12,5,15,11}
 * Given inputs k1 = 3 and k2 = 6
 *
 * If the array were sorted, it would have been - array = {1,3,5,11,12,15}
 * from the input array, 3rd smallest element is 5 and 6th smallest is 15
 *
 * Sum the elements between 3rd and 6th - 11+12 = 23
 *
 * Algorithm -
 * Use _2FindKthSmallestElement to find kth smallest.
 * Call it 2 times to get outputs for K1 and K2.
 * Then iterate over the array and check if the value is between the outputs of K1 and K2.
 * If yes, add it to the final sum.
 *
 * Here, we do not want to sort the entire array.
 * Sorting time complexity will become n*log(n)
 * Instead, if we use heap 2 times, sorting time complexity will become n*log(k1) + n*log(k2) which is less than n*log(n)
 */
public class _10SumElementsBetweenK1thSmallestAndK2thSmallest {

    private static void solve(int[] arr, int k1, int k2) {
        int output_k1 = _2FindKthSmallestElement.solve(arr, k1);
        int output_k2 = _2FindKthSmallestElement.solve(arr, k2);

        int result = 0;
        for (int i : arr) {
            if (i > output_k1 && i < output_k2) {
                result += i;
            }
        }
        System.out.println("sum is - " + result);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 12, 5, 15, 11};
        solve(arr, 3, 6);
    }
}
