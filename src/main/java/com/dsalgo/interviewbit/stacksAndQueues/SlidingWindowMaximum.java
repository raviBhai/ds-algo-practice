package com.dsalgo.interviewbit.stacksAndQueues;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        List<Integer> A = new ArrayList<Integer>() {{
            add(1);
            add(3);
            add(-1);
            add(-3);
            add(5);
            add(3);
            add(6);
            add(7);
        }};
        //ArrayList<Integer> maximum = slidingWindowMaximum.slidingMaximum(A, 3);
        //System.out.println(maximum);

        int[] a = {10, 9,8,7,6,5,4,3,2,1};
        printMax(a, a.length, 3);
        System.out.println();
        slide(a, a.length, 3);
        System.out.println();
        printMax_highTimeComplexity(a, a.length, 3);

        System.out.println("Slide minimum:");

        slideMin(a, a.length, 3);
        System.out.println();

    }


    public ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {
        ArrayList<Integer> output = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>(B);
        if (A.isEmpty()) {
            return output;
        }

        for (Integer i : A) {
            if (queue.size() == B) {
                output.add(getMaxFromQueue(queue));
                queue.remove();
            }
            queue.add(i);
        }
        output.add(getMaxFromQueue(queue));
        return output;
    }

    private int getMaxFromQueue(ArrayDeque<Integer> queue) {
        Integer max = queue.peek();
        for (Integer i : queue) {
            if (i.compareTo(max) > 0) {
                max = i;
            }
        }
        return max;
    }

    static void printMax_highTimeComplexity(int arr[], int n, int k) {
        int max;
        for (int i = 0; i <= n-k; i++) {
            max = arr[i];
            for (int j = i+1; j < i + k; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            System.out.print(max + " ");
        }
    }


    static void slide(int[] arr, int n, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int i;

        for (i = 0; i < k; i++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }

        for (; i < n; i++) {
            //print max from previous window
            System.out.print(arr[deque.peek()] + " ");

            //remove elements from previous window
            while (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.removeFirst();
            }

            //remove smaller elements than the current element
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.removeLast();
            }

            //add current element
            deque.addLast(i);
        }

        //print max of the last window
        System.out.print(arr[deque.peek()] + " ");
    }

    static void printMax(int arr[],int n, int k)
    {
        // Create a Double Ended Queue, Qi that will store indexes of array elements
        // The queue will store indexes of useful elements in every window and it will
        // maintain decreasing order of values from front to rear in Qi, i.e.,
        // arr[Qi.front[]] to arr[Qi.rear()] are sorted in decreasing order
        Deque<Integer> Qi = new LinkedList<>();

        /* Process first k (or first window) elements of array */
        int i;
        for(i = 0; i < k; ++i)
        {
            // For very element, the previous smaller elements are useless so
            // remove them from Qi
            while(!Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();   // Remove from rear

            // Add new element at rear of queue
            Qi.addLast(i);
        }

        // Process rest of the elements, i.e., from arr[k] to arr[n-1]
        for( ;i < n; ++i)
        {
            // The element at the front of the queue is the largest element of
            // previous window, so print it
            System.out.print(arr[Qi.peek()] + " ");

            // Remove the elements which are out of this window
            while((!Qi.isEmpty()) && Qi.peek() <= i-k)
                Qi.removeFirst();

            // Remove all elements smaller than the currently
            // being added element (remove useless elements)
            while((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()])
                Qi.removeLast();


            // Add current element at the rear of Qi
            Qi.addLast(i);

        }

        // Print the maximum element of last window
        System.out.print(arr[Qi.peek()]);
    }


    static void slideMin(int[] arr, int n, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int i;

        for (i = 0; i < k; i++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] > arr[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }

        for (; i < n; i++) {
            //print max from previous window
            System.out.print(arr[deque.peek()] + " ");

            //remove elements from previous window
            while (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.removeFirst();
            }

            //remove smaller elements than the current element
            while (!deque.isEmpty() && arr[deque.peekLast()] > arr[i]) {
                deque.removeLast();
            }

            //add current element
            deque.addLast(i);
        }

        //print max of the last window
        System.out.print(arr[deque.peek()] + " ");
    }
}
