package com.dsalgo.heap;




import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * Given input array - {1,2,7,8,9,10}
 * K=3, X=7
 * So, 3 nearest elements to 7 are 7,8,9. Return this as output.
 *
 * Solution -
 * Substract X from every element of the array, we now have a difference array - {-6, -5, 0, 1, 2, 3}
 *
 * Input Array - { 1,  2, 7, 8, 9, 10}
 * Diff Array -  {-6, -5, 0, 1, 2, 3}
 *
 * We can now build a pair, with first element as diff and second element as the corresponding element from the input array.
 * Then this pair can be pushed to max heap, where it will be sorted based on the first element of the pair.
 *
 * During the sorting within the heap, -6 will be considered as lowest, but it cannot be part of the solution.
 * Hence, rather than actual difference, absolute difference is requried.
 * So, while taking diff, do Math.abs()
 *
 */
public class _5FindKNearestElementsToX {

    private static void solve(int[] arr, int k, int x) {

        // Default heap is minHeap/ascending order, which is pair1 - pair2.
        // Here we want maxHeap/descending order, which is pair2 - pair1.
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair pair1, Pair pair2) {
                return pair2.first - pair1.first;
            }
        });

        for (int i : arr) {
            Pair pair = new Pair(Math.abs(i - x), i);

            maxHeap.offer(pair);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll().second);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9};
        solve(arr, 3, 7);
    }
}

class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
