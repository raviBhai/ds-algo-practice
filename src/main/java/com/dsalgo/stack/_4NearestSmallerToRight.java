package com.dsalgo.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class _4NearestSmallerToRight {

    private static void solve(int[] arr) {

        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length-1; i >= 0; i--) {
            if (stack.empty()) {
                result.add(-1);
            } else if (!stack.isEmpty() && stack.peek() < arr[i]) {
                result.add(stack.peek());
            } else if (!stack.isEmpty() && stack.peek() >= arr[i]) {
                //pop from stack till stack is not empty and stack top is greater than current element
                while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                    // the popped element will never be used in the answer.
                    // it is fine to pop the element because the current element arr[i] is less than the popped element and can be used to other elements which are not yet processed.
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
        int[] arr = {4, 5, 2, 10, 8};
        solve(arr);
    }
}
