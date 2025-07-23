package com.dsalgo.slidingwindow;


import java.util.Deque;
import java.util.LinkedList;

/**
 * The problem is same as SlidingWindowMaximum
 *
 * Over a given input array, slide a window of size k and print the max in that window
 */
public class _4MaxOfAllSubArraysOfSizeK {

    private static void solve(int[] arr, int k) {
        int i = 0, j = 0;
        Deque<Integer> dq = new LinkedList<>();
        while (j < arr.length) {

            // calculation
            while (!dq.isEmpty() && arr[j] >= arr[dq.peekLast()]) {
                dq.removeLast();
            }
            dq.addLast(j);

            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {

                // get answer from calculation
                System.out.println(arr[dq.peekFirst()]);

                //slide
                while (!dq.isEmpty() && dq.peekFirst() <= i) {
                    dq.removeFirst();
                }
                i++;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        solve(a, 3);
        System.out.println("------");
        int[] b = {1, 3, -1, -3, 5, 3, 6, 7};
        solve(b, 3);
    }
}
