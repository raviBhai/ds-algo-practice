package com.dsalgo.greedy;



public class _4_JumpGame {


}

/*

Given input array.
Example - { 2, 3, 1, 0, 4 }
If you are at index 0, that is value 2, then you can jump either to index_1 or index_2
That is, if the current value is 2, then you can at max jump to next 2 places.

Return if you can reach the last position.

With above example, you can reach the last element by following the path - 2 -> 3 -> 4

Example_2 - { 3, 2, 1, 0, 4}
With this example, you cannot reach the last element.


If all elements are greater than 0, it means you can definitely reach the last.


Algorithm -
Intuition: The goal is to determine if you can reach the last index by keeping track
 of the farthest position reachable at any given point.
 If you ever find yourself at an index that is beyond your current maximum reach,
 it means you've hit a "gap" (usually caused by a 0) and cannot proceed.

Algorithm Steps:

1. Initialize `maxReach`: Start with a variable `maxReach = 0`,
representing the furthest index you can currently get to.

2. Iterate through the array: Loop through each index `i` from 0 to n-1:

  • Check Accessibility: If the current index `i` is greater than `maxReach`, return false (you cannot step on this index).

  • Update Reach: Update `maxReach` to be the maximum of itself and the new potential reach from the current spot: `maxReach = max(maxReach, i + array[i])`.

  • Early Exit: If `maxReach` is already greater than or equal to the last index, return true.

3. Completion: If the loop finishes, return true.

Key Note: The only way to fail is if `maxReach` gets stuck at an index containing 0 and cannot skip over it.

 */
class _JumpGame_1 {
    private static boolean solve(int[] array) {
        int maxReach = 0;
        for (int i = 0; i < array.length; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + array[i]);
            if (maxReach >= array.length - 1) {
                return true;
            }
        }
        return true;
    }

    // do not use this, as this is not optimized. However, the solution works
    private static boolean recursion(int[] array, int index) {
        // return true if I have reached the last index
        if (index >= array.length - 1) {
            return true;
        }

        // value at current index is 0, which means I cannot move ahead, and as this is not the last index, i am stuck here, hence return false
        if (array[index] == 0) {
            return false;
        }
        boolean ans = false;

        // start with i=1, not i=0, because this denotes the number of steps that can be taken from the current index
        for (int i = 1; i <= array[index]; i++) {
            ans = ans || recursion(array, index + i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] array1 =  { 2, 3, 1, 0, 4 };
        System.out.println(solve(array1));

        int[] array2 = {3, 2, 1, 0, 4};
        System.out.println(solve(array2));

        System.out.println(recursion(array1, 0));
        System.out.println(recursion(array2, 0));

    }
}

/*

Follow up from previous question.
It is given that you will reach the end.
You need to figure out the minimum jumps required to reach the end.
Example - { 2, 3, 1, 0, 4 }
Jump from 2 -> 3 -> 4
Total 2 jumps.

Algorithm -
Recursive -
Define the problem statement in terms of index.
At any index, we have to do all possible stuff.
All possible stuff is to try to jump to all possible next places.
When trying to jump to next place, the number of jumps will be currentJumps + 1
Hence, at current index, we need to maintain the number of currentJumps.

solve(array, index, currentJumps)


Optimized -
Try to maintain a range.
A range denotes the elements that I can reach in the minimum jumps.
Range has left and right. If right points to the array.length-1, it means, we reached the last index in minimum jumps.

Initial range will be 0th index as both left and right.
Next range's left will be current range's right+1
Next range's right will be the farthest I can reach from the current range.
To get the farthest, iterate over the current range, and check for each index, what is the farthest each index can take you to.
You will need only one jump to reach the farthest.
Hence, increment jump by 1

 */
class _JumpGame_2 {

    // time complexity - from every index, i try to jump to all available next places
    // assume that from every index, i try to jump to all N indexes.
    // hence, it will be O(N ^ N)
    // this can be optimized with memoization, which will give N^2 time complexity
    private static int solveRecursion(int[] array, int index, int currentJumps) {
        if (index >= array.length - 1) {
            // base case - return the number of jumps required to reach the base case.
            // do not try to return the minimum jumps from the base case.
            // it should always return the number of jumps required to reach the base case.
            return currentJumps;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= array[index]; i++) {
            ans = Math.min(ans, solveRecursion(array, index + i, currentJumps + 1));
        }
        return ans;
    }

    private static int optimized(int[] array) {
        int jumps = 0;
        int left = 0;
        int right = 0;
        while (right < array.length - 1) {  // continue till one index before the last index, break when last index or greater than last index is reached
            int farthest = 0;
            for (int i = left; i <= right; i++) {
                farthest = Math.max(farthest, i + array[i]);
            }
            left = right + 1;
            right = farthest;
            jumps = jumps + 1;
        }
        return jumps;
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 1, 4};
        System.out.println(solveRecursion(array, 0, 0));
        System.out.println(optimized(array));
    }

}