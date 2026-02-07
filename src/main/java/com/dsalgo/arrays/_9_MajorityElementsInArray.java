package com.dsalgo.arrays;

/*
Given input array of size N which has integers which are duplicate.

If there is an integer which occurs more than N/2 times, return that integer.

Algorithm : Moore's voting algorithm

Take 2 variables - candidate and count
Start at index -1 as candidate and count as 0
Start iterating the array.
If the count is 0, then make the current element as the candidate and count = 1
If the current element is same as candidate then count++, else count--

After iterating over the array, candidate will be one of the array element.

If the question guarantees that the input array has one element which occurs more than N/2 times,
then the element in the variable candidate is the answer, so return it.

If the question mentions that the input array may or may not have an element with count more than N/2,
then iterate once again over the array and check how may times the candidate appears in the array.
If it appears more than N/2 times, then return candidate else -1.

 */

import java.util.ArrayList;
import java.util.List;

public class _9_MajorityElementsInArray {

    private static int solve_inputHasMajorityElement(int[] array) {
        int candidate = -1, count = 0;
        for (int i = 0; i < array.length; i++) {
            if (count == 0) {
                candidate = array[i];
                count = 1;
            } else if (count != 0 && candidate == array[i]) {
                count++;
            } else if (count != 0 && candidate != array[i]) {
                count--;
            }
        }

        return candidate;
    }

    private static int solve_inputMayOrMayNotHaveMajorityElement(int[] array) {
        int candidate = -1, count = 0;
        for (int i = 0; i < array.length; i++) {
            if (count == 0) {
                candidate = array[i];
                count = 1;
            } else if (count != 0 && candidate == array[i]) {
                count++;
            } else if (count != 0 && candidate != array[i]) {
                count--;
            }
        }

        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == candidate) {
                count++;
            }
        }

        if (count > array.length / 2) {
            return candidate;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 1, 2, 2, 4, 1, 2, 4, 2, 2, 2};   // input has a majority element
        System.out.println(solve_inputHasMajorityElement(array1));

        int[] array2 = {1, 1, 2, 2 };   // input may or may not have a majority element
        System.out.println(solve_inputMayOrMayNotHaveMajorityElement(array2));
        System.out.println(solve_inputMayOrMayNotHaveMajorityElement(array1));
    }
}

/*
Find if the array has elements which occur more than N/3 times.
Return all such elements.

Algorithm :

In existing algo, we use one candidate and one count.
For this, we will use 2 candidates and 2 counts.

There can be at max 2 elements which can have count more than N/3
Example -
Let's say input array length is 9
N/3 = 9/3 = 3
Required count is more than N/3, so required count should be at least 4
2 elements can take up 4+4=8 places.
Remaining 1 place to be taken up by another element.

Let's say input array length is 10
N/3 = 10/3 = 3
Required count is more than N/3, so required count should be at least 4
2 elements can take up 4+4=8 places.
Remaining 2 places to be taken up by another element.


Edge case :

While iterating it is possible that candidate2 is having a value but count1 is zero.
At the current element, if count1 is 0 and the value of current element is same as the value of candidate2,
we will still initialize candidate1 with the current element value and make count1 as 1.
Rather, we should have incremented count2 as the current element is already present in candidate2.
To avoid this scenario, add an extra check in the if-else-if clause : candidate2 != array[i] / candidate1 != array[i]

 */
class Followup {

    private static List<Integer> solve(int[] array) {
        int candidate1 = -1, candidate2 = -1, count1 = 0, count2 = 0;
        for (int i = 0; i < array.length; i++) {
            if (count1 == 0 && candidate2 != array[i]) {
                count1 = 1;
                candidate1 = array[i];
            } else if (count2 == 0 && candidate1 != array[i]) {
                count2 = 1;
                candidate2 = array[i];
            } else if (candidate1 == array[i]) {
                count1++;
            } else if (candidate2 == array[i]) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        int minCount = array.length / 3 + 1;
        List<Integer> result = new ArrayList<>();
        for (int i : array) {
            if (i == candidate1) {
                count1++;
            } else if (i == candidate2) {
                count2++;
            }
        }
        if (count1 >= minCount) {
            result.add(candidate1);
        }
        if (count2 >= minCount) {
            result.add(candidate2);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 1, 1, 1, 3, 2, 2, 2};
        System.out.println(solve(array1));
        System.out.println();
        int[] array2 = {2, 1, 1, 3, 1, 4, 5, 6};
        System.out.println(solve(array2));
    }

}