package com.dsalgo.heap;

import java.util.PriorityQueue;

public class _8KClosestPointsToOrigin {

    private static void solve(int[][] arr, int k) {

        // Sort the maxHeap based on distance from origin. The second of the pair can be the arr index rather than having the actual arr
        PriorityQueue<Pair2> maxHeap = new PriorityQueue<>((p1, p2) -> p2.first - p1.first);

        for (int i = 0; i < arr.length; i++) {
            int[] tempArr = arr[i];

            // in the distance formula, x1 and y1 are 0. Further, we have to order the distance,
            // so it is not required to calculate the sqrt. We can order without the sqrt
            int distanceFromOrigin = tempArr[0] * tempArr[0] + tempArr[1] * tempArr[1];

            Pair2 pair2 = new Pair2(distanceFromOrigin, i);
            maxHeap.offer(pair2);

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }


        while (!maxHeap.isEmpty()) {
            Pair2 pair2 = maxHeap.poll();
            System.out.println(arr[pair2.second][0] + " " + arr[pair2.second][1]);
        }
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 3},
                {-2, 2},
                {5, 8},
                {0, 1}
        };
        solve(arr, 2);
    }
}

class Pair2 {
    int first;
    int second;

    public Pair2(int first, int second) {
        this.first = first;
        this.second = second;
    }
}