package com.dsalgo.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * We will calculate the area for each bar(entry) in the histogram.
 * Then we will return the maximum of all the areas.
 *
 * To calculate area of each bar(entry),
 * we can get index of nearestSmallestToLeft and index of nearestSmallestToRight.
 * Then we can count the number of bars between nearestSmallestToLeft and nearestSmallestToRight.
 * NumberOfBars = nearestSmallestToRight - nearestSmallestToLeft - 1
 *
 * Once we get NumberOfBars for each entry, we can then multiply it with the height of each bar and get the max of all.
 *
 * Input    - {6, 2, 5, 4, 5, 1, 6};
 * Indexes  - {0, 1, 2, 3, 4, 5, 6}
 * Take 2 pseudoIndexes, -1 and 7, which are to the left and right of the indexes.
 * For 1st element, which is 6, the nearest left index will be -1
 * For last element, which is 6, the nearest right index will be 7
 *
 * Input                    -    { 6,   2,   5,   4,   5,   1,   6};
 * Indexes                  - -1 { 0,   1,   2,   3,   4,   5,   6} 7
 * nearestSmallestToLeft    -    {-1,  -1,   1,   1,   3,  -1,   5}
 * nearestSmallestToRight   -    { 1,   5,   3,   5,   5,   7,   7}
 * NumberOfBars             -    { 1,   5,   1,   3,   1,   7,   1}
 *
 *
 * While calculating nearestSmallestToLeft, for the very first element, it will be -1.
 * For any element which does not have a smaller entry in the left part, we return -1.
 *
 */
public class _6MaximumAreaInHistogram {

    public static void main(String[] args) {
        int[] arr = {6, 2, 5, 4, 5, 1, 6};
        System.out.println(solve(arr));
    }

    public static int solve(int[] arr) {
        List<Integer> left = nearestSmallerLeft(arr);
        List<Integer> right = nearestSmallerRight(arr);
        int maxArea = Integer.MIN_VALUE;
        int currentArea = -1;
        for (int i = 0; i < arr.length; i++) {
            currentArea = (right.get(i) - left.get(i) - 1) * arr[i];
            maxArea = Math.max(maxArea, currentArea);
        }
        return maxArea;
    }

    private static List<Integer> nearestSmallerLeft(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty()) {
                result.add(-1);
            } else if (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                result.add(stack.peek());
            } else if (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    result.add(-1);
                } else {
                    result.add(stack.peek());
                }
            }
            stack.push(i);
        }
        return result;
    }

    private static List<Integer> nearestSmallerRight(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        int pseudoIndex = arr.length;

        for (int i = arr.length-1; i >= 0; i--) {
            if (stack.isEmpty()) {
                result.add(pseudoIndex);
            } else if (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                result.add(stack.peek());
            } else if (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    result.add(pseudoIndex);
                } else {
                    result.add(stack.peek());
                }
            }
            stack.push(i);
        }
        Collections.reverse(result);
        return result;
    }
}
