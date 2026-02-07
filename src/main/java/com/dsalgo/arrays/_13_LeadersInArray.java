package com.dsalgo.arrays;


import java.util.ArrayList;
import java.util.List;

/*

Given input array, find the leader elements in it.
Leader element is the one which has all the smaller elements from the next index

input - 10, 22, 12, 3, 0, 6
output - 22, 12, 6

output can be in any order.

Algorithm -
Start traversing backwards.
Last element will always be the leader.

Mark it as max.
Moving back, if the current element is greater than the max, then current element is the leader and becomes the new max

 */
public class _13_LeadersInArray {

    private static List<Integer> solve(int[] array) {
        List<Integer> result = new ArrayList<>();
        result.add(array[array.length - 1]);

        int max = array[array.length - 1];
        for (int i = array.length - 2; i >= 0; i--) {
            if (max < array[i]) {
                max = array[i];
                result.add(array[i]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array = {10, 22, 12, 3, 0, 6};
        System.out.println(solve(array));
    }
}
