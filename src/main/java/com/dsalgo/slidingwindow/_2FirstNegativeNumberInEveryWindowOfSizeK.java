package com.dsalgo.slidingwindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Input array - 12, -1, -7, 8, -15, 30, 16, 28
 * Window size = 3
 * Print first negative in every window.
 * If window does not have negative, print 0
 * Output -
 * -1   - {12, -1, -7}
 * -1   - {-1, -7, 8}
 * -7   - {-7, 8, -15}
 * -15  - {8, -15, 30}
 * -15  - {-15, 30, 16}
 *  0   - {30, 16, 28}
 */
public class _2FirstNegativeNumberInEveryWindowOfSizeK {

    public static void main(String[] args) {
        int[] arr = {12, -1, -7, 8, -15, 30, 16, 28};
        solve(arr, 3);
    }


    private static void solve(int[] arr, int k) {
        Deque<Integer> deque = new LinkedList<>();

        int i = 0, j = 0;
        while (j < arr.length) {
            if (arr[j] < 0) {
                deque.addLast(j);
            }

            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {

                // print 0 when window does not have negative number
                if (deque.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(arr[deque.peek()]);
                }

                // In the above step, we have printed the element from the current window.
                // Hence, before sliding to the next window, we need to remove the element from the current window.
                // We will slide by doing i++, hence, we need to remove the elements at i and before i, that is, <= i
                while (!deque.isEmpty() && deque.peek() <= i) {
                    deque.removeFirst();
                }

                i++;
                j++;

            }
        }
    }
}
