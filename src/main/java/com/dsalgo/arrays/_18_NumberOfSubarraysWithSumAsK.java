package com.dsalgo.arrays;

/*

Given input array and a number K. Count how many subarrays have the sum as K

This is based on the logic used in _5MaximumSizeSubarraySumEqualsK.WhenNumbersCanBeLessThanOrEqualToZero

Algorithm -
1. Iterate over the array and at every index, add to the sum
2. Maintain a map which maintains that this sum is encountered how many times
3. If the map contains sum-K with value as count, it means that till this index, the number of subarrays with sum as K is equal to count

 */

import java.util.HashMap;
import java.util.Map;

public class _18_NumberOfSubarraysWithSumAsK {

    private static int solve(int[] arr, int k) {

        int sum = 0;

        // Initialization of the map with (0,1) is required for the case when the answer window starts from 0th index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  // number of subarrays with sum 0 before starting the iteration

        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];

            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            // increment count for sum in the map
            int value = map.getOrDefault(sum, 0);
            map.put(sum, value + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, -3, 1, 1, 1, 1, 4, 2, -3};
        System.out.println(solve(array, 3));
    }
}

/*

Similar to above problem.
Rather than sum as K, find number of subarrays which have xor as K

input  = {4, 2, 2, 6, 4};
K = 6

sub arrays with xor as K :

{4,2}       :           convert to binary and xor  =   100^010 = 110
{2,2,6}     :           2 and 2 cross, hence remaining 6 which is unique is the xor
{6}         :           single element is the xor
{4,2,2,6,4} :           4's and 2's cross each others,  hence remaining 6 which is unique is the xor

Output      :   4

Logic :

In the previous problem, the logic was that we maintain sum till the current element.
Then we would check if there exists a subarray from start which has a sum x. Let's call this subarray as part1
If yes, it means that from the next element after part1 ends till the current element, the sum is k

If part1 sum is x, the formula was :
x + k = sum
x = sum - k

Hence, we used to check if the map has (sum - k) till the current element.
The objective was to find the value of x.

Similarly, in this problem, we can maintain xor till current element.
Then we would try to find if there exists a subarray from the start which has xor as x. Let's call this subarray as part1
Let x be the xor of part1

x ^ k = xorTillNow
Do xor k on both sides

(x ^ k) ^ k = xorTillNow ^ k

x = xorTillNow ^ k

We got the value of x which we need to check in the map


 */
class NumberOfSubarraysWithXorAsK {

    private static int solve(int[] array, int k) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int xorTillNow = 0;
        int tempXor = 0;
        for (int i = 0; i < array.length; i++) {
            xorTillNow = xorTillNow ^ array[i];

            tempXor = xorTillNow ^ k;
            if (map.containsKey(tempXor)) {
                count += map.get(tempXor);
            }

            int value = map.getOrDefault(xorTillNow, 0);
            map.put(xorTillNow, value + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] array = {4, 2, 2, 6, 4};
        int k = 6;
        System.out.println(solve(array, k));
    }

}