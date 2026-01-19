package com.dsalgo.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * Given input array, for every element, find the next nearest largest element. If not found, return -1 for that element
 *
 * Input - 1,3,2,4
 * Output - 3,4,4,-1
 *
 * Next nearest largest for 1 is 3
 * Next nearest largest for 3 is 4
 * Next nearest largest for 2 is 4
 * Next nearest largest for 4 is NOT FOUND, hence use -1
 *
 * Brute force -
 * Use 2 nested loops -
 *  1. Outer loop "i" to run from 0 to n-1
 *  2. Inner loop "j" to run from "i+1" to n-1
 *
 * As these are conditional nested loops, try to use STACK.
 *
 * Let's say, we have now decided to use stack.
 * So, to get the output for 0th element, which is 1, we would want other elements on the stack.
 * We would want 3,2,4 on the stack, such that, 3 is at the top and 4 at the bottom.
 * To do so, we will have to traverse the given input array in reverse order and put on stack.
 *
 */

// This is also called as NextLargestNumber
public class _2NearestGreaterToRight {

    private static void solve(int[] arr) {

        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length-1; i >= 0; i--) {
            if (stack.empty()) {
                result.add(-1);
            } else if (!stack.isEmpty() && stack.peek() > arr[i]) {
                result.add(stack.peek());
            } else if (!stack.isEmpty() && stack.peek() <= arr[i]) {
                //pop from stack till stack is not empty and stack top is less than current element
                while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                    // the popped element will never be used in the answer.
                    // it is fine to pop the element because the current element arr[i] is greater than the popped element and can be used for other elements which are not yet processed.
                    stack.pop();
                }

                //stack can be empty after doing pop()
                if (stack.isEmpty()) {
                    result.add(-1);
                } else {
                    result.add(stack.peek());
                }
            }

            // after processing every element and before moving to the previous element in the array to process, push the current element on stack
            stack.push(arr[i]);
        }

        Collections.reverse(result);

        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4};
        solve(arr);
    }
}
