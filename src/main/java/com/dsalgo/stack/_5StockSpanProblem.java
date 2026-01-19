package com.dsalgo.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given prices of stock on each day.
 * For every day, find the number of days when the stock price is consecutive smaller or equal to before it.
 * Count the current day while calculating consecutive smaller or equal before it.
 *
 *
 * Input - {100,80,60,70,60,75,85}
 * for 100, nothing small on left, so output is 1
 * for 80, nothing small on left, so output is 1
 * for 60, nothing small on left, so output is 1
 * for 70, only 60 is smaller and consecutive before it, so output is 2
 * for 60, nothing is consecutive small on left, so output is 1
 * for 75, (60,70,60) are consecutive small on left, so output is 4
 * for 85, (80,60,70,60,75) are consecutive small on left, so output is 6
 *
 * Final output is - {1,1,1,2,1,4,6}
 *
 * As we have to find consecutive smaller or equal to before it,
 * we are trying to find the nearest greater on left and stop the search.
 * So, we can use the concept from _3NearestGreaterToLeft
 *
 * Rather than storing the element in the stack, we can store the index of the element in the stack.
 * In the result, we want the count of the number of elements between current and nearest greatest element, including current element,
 * hence do [currentElementIndex - indexOfNearestGreatestElementFromStack]
 */
public class _5StockSpanProblem {

    private static void solve(int[] arr) {

        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= arr.length-1; i++) {
            if (stack.empty()) {
                // stack can be empty while processing 1st element or any later element in the input array
                // empty stack means that there is no element to the left which is greater than the current element
                // for eg, if we are at the 4th element, which is i=3, and the stack is empty, meaning, no greater element on the left, then result should be 4, which is i+1
                // in such a case, the result will be currentIndex + 1, which is i + 1
                // it is written as i - (-1) below
                // X- other way to see it as that when the stack is empty, it means, the element at index -1 would be smaller than the current element
                // X- hence, just like while capturing the answer when the stack is not empty, here also, we did currentIndex - indexOfSmallerElement
                result.add(i- -1);
            } else if (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                result.add(i - stack.peek());
            } else if (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                //pop from stack till stack is not empty and stack top is less than current element
                while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                    // the popped element will never be used in the answer.
                    // it is fine to pop the element because the current element arr[i] is greater than the popped element and can be used to other elements which are not yet processed.
                    stack.pop();
                }

                //stack can be empty after doing pop()
                if (stack.isEmpty()) {
                    result.add(i - -1);
                } else {
                    result.add(i - stack.peek());
                }
            }

            // after processing every element and before moving to the previous element in the array to process, push the current element on stack
            stack.push(i);
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr = {100, 80, 60, 70, 60, 75, 85};
        solve(arr);
    }
}
