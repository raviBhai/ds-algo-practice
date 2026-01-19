package com.dsalgo.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * https://github.com/kdn251/interviews/blob/master/company/google/DailyTemperatures.java
 *
 * For every entry, we need to check towards the right when can we get the next greater entry.
 * Hence, we can use NearestGreaterToRight
 */
public class _41DailyTemperatures {

    private static List<Integer> solve(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                result.add(0);
            } else if (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                result.add(stack.peek() - i);
            } else if (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    result.add(0);
                } else {
                    result.add(stack.peek() - i);
                }
            }
            stack.push(i);
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(solve(temperatures));
    }
}
