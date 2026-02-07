package com.dsalgo.arrays;

/*

Given an array which can have +ve and -ve numbers, return the sub array which has the max product.

Algorithm -
If the array has all +ve numbers, the result will be product of all numbers.

If the array has 2 -ve numbers, the result will be product of all numbers.

If the array has 1 -ve number, then calculate product of numbers before -ve and after -ve number.
Then result will be max of these products.

Let's call product before a -ve number as prefix and product after the -ve number as suffix.

If the array has 3 -ve numbers, then we need to find a way to ignore one of these 3 -ve numbers so that the product is negative.
    calculate prefix and suffix product for first -ve number
    calculate prefix and suffix product for second -ve number
    calculate prefix and suffix product for third -ve number

    result will be max of all these 6 prefix and suffix

So, the algorithm is to calculate all the prefix product and suffix product and maintain the max of these.
Then return max.

What if the array has a zero ?
The prefix or suffix will become 0, so it means this window (until 0) cannot have the max product.
Hence, start new window after 0

 */

public class _23_MaximumProductSubarray {

    private static int solve(int[] array) {

        int prefix = 1, suffix = 1;
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (prefix == 0) {
                prefix = 1;
            }
            if (suffix == 0) {
                suffix = 1;
            }
            prefix = prefix * array[i];
            result = Math.max(result, prefix);

            suffix = suffix * array[array.length - 1 - i];
            result = Math.max(result, suffix);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array1 = {2, 3, -2, 4};
        System.out.println(solve(array1));

        int[] array2 = {-2, 3, 4, -1, 0, -2, 3, 1, 4, 0, 4, 6, -1, 4};
        System.out.println(solve(array2));
    }
}
