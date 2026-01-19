package com.dsalgo.heap;

import java.util.PriorityQueue;

/**
 * Given input array which represents ropes of different lengths.
 * We have to join all the ropes. At one time, we will join 2 ropes.
 * While joining two ropes, a cost is incurred.
 * We have to join the ropes such that the cost is minimised.
 *
 * How to calculate the cost while joining 2 ropes -
 * When 2 ropes are joined, the length of the output rope is the cost.
 *
 * Example -
 * Input arr - {1,2,3,4,5}
 * Let's start joining from the start
 * Have a global variable cost.
 *
 * Join 1 and 2. We get new rope of length 3. Cost = 3
 * Join 3(new rope) and 3(from input array). We get new rope of length 6. Cost = 3 + 6
 * Join 6(new rope) and 4(from input array). We get new rope of length 10. Cost = 3 + 6 + 10
 * Join 10(new rope) and 5(from input array). We get new rope of length 15. Cost = 3 + 6 + 10 + 15
 * Total cost becomes - 34
 *
 * Algorithm to minimise the total cost.
 * Whenever you add 2 ropes, add the new rope to the pool of existing ropes.
 * From the pool of ropes, always pick 2 smallest ropes. Then the cost will be minimised.
 *
 * Input arr/pool - {1,2,3,4,5}
 * Take 2 smallest ropes - 1 and 2. Join them. New rope - 3. Cost = 3
 * Add new rope of length 3 to the pool
 * pool - {3,3,4,5}
 * Take 2 smallest ropes - 3 and 3. Join them. New rope - 6. Cost = 3 + 6
 * Add new rope of length 6 to the pool
 * pool - {6,4,5}
 * Take 2 smallest ropes - 4 and 5. Join them. New rope - 9. Cost = 3 + 6 + 9
 * Add new rope of length 9 to the pool
 * pool - {6,9}
 * Take 2 smallest ropes - 6 and 9. Join them. New rope - 15. Cost = 3 + 6 + 9 + 15
 * Add new rope of length 15 to the pool
 * pool - {15}
 * End the calculation when the pool size becomes less than 2.
 * Total cost = 3 + 6 + 9 + 15 = 33
 * This will be the minimum cost.
 *
 * To get smallest 2 from the pool, use min heap.
 *
 */
public class _9ConnectRopesToMinimiseTheCost {

    private static void solve(int[] arr) {
        int totalCost = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i : arr) {
            minHeap.offer(i);
        }

        while (minHeap.size() >= 2) {
            int rope1 = minHeap.poll();
            int rope2 = minHeap.poll();
            int newRope = rope1 + rope2;
            totalCost += newRope;
            minHeap.offer(newRope);
        }

        System.out.println("minimum cost to join all ropes is - " + totalCost);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        solve(arr);
    }
}
