package com.dsalgo.dynamicProgramming;


/*

There are houses which has money.
Houses have security system such that if a house is broken-in, adjacent house will be alerted.
Hence, a robber cannot rob adjacent houses.
Return - what is the maximum money a robber can rob from all these houses.

This is exactly same as _2_MaximumSumOfNonAdjacentElements

 */
public class _3_HouseRobber {


}

/*

Follow up from above question with one change.
The houses are in a circle such that first and last houses are also adjacent.

Solution:
If there are N houses from 0 to n-1,
    solve for 0 to n-2 house using _2_MaximumSumOfNonAdjacentElements - ignore last house
    solve for 1 to n-1 house using _2_MaximumSumOfNonAdjacentElements - ignore first house
    Return maximum of these

 */

class HouseRobber_2 {

    private static int solve(int[] array) {

        if (array.length == 1) {
            return array[0];
        }

        int[] array1 = new int[array.length - 1];
        int[] array2 = new int[array.length - 1];

        for (int i = 0; i < array.length; i++) {
            if (i != 0) {   // ignore the first house
                array2[i-1] = array[i];
            }

            if (i != array.length-1) {  // ignore the last house
                array1[i] = array[i];
            }
        }

        int res1 = _2_MaximumSumOfNonAdjacentElements.tabulation(array1);
        int res2 = _2_MaximumSumOfNonAdjacentElements.tabulation(array2);
        return Math.max(res1, res2);
    }

    public static void main(String[] args) {
        int[] array = {2, 1, 4, 9};
        System.out.println(solve(array));
    }

}
