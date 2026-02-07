package com.dsalgo.arrays;

/*
Given input array and a number N,
Check if the array has 2 numbers whose sum is N

Algorithm :

Brute force :
For every element, iterate over the input array to generate all tbe pairs and check the sum.
This is O(N^2)

Solution-1 :
We want a better time complexity than O(N^2)
Let's say if we want O(N), it means we can go over the array once.
It means, at every element, I should have a way to know whether there exists another number in the array with whom the sum can be N.
To do so, at every element, we can put the element in the map along with its index.


 Solution-2 :
 Sort the array.
 Use left and right pointer.
 Do the sum of left and right.
 If sun > N, do right--, else do left++

 */

import java.util.HashMap;
import java.util.Map;

public class _7_TwoSumProblem {

    private static void solve(int[] array, int N) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int balance = N - array[i];
            if (map.containsKey(balance)) {
                System.out.println(array[map.get(balance)] + " - " + array[i]);
                return;
            } else {
                map.put(array[i], i);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 6, 2, 3, 8, 5};
        solve(array, 14);
    }

}
