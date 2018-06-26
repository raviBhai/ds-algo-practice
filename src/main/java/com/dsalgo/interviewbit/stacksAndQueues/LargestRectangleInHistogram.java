package com.dsalgo.interviewbit.stacksAndQueues;

import java.util.ArrayList;
import java.util.Stack;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        LargestRectangleInHistogram largestArea = new LargestRectangleInHistogram();
        ArrayList<Integer> A = new ArrayList<Integer>() {{
            add(6);
            add(2);
            add(5);
            add(4);
            add(5);
            add(1);
            add(6);
        }};
        System.out.println(largestArea.largestRectangleArea(A));
    }

    public int largestRectangleArea(ArrayList<Integer> A) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = -1;
        int area = -1;
        int top;
        int i = 0;
        while (i < A.size()) {
            if (stack.isEmpty() || A.get(stack.peek()) <= A.get(i)) {
                stack.push(i++);
            } else {
                top = stack.pop();
                if (stack.isEmpty()) {
                    area = A.get(top) * i;
                } else {
                    area = A.get(top) * (i - stack.peek() - 1);
                }
            }
            if (area > maxArea) {
                maxArea = area;
            }
        }

        while (!stack.isEmpty()) {
            top = stack.pop();
            if (stack.isEmpty()) {
                area = A.get(top) * i;
            } else {
                area = A.get(top) * (i - stack.peek() - 1);
            }
            if (area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }
}
