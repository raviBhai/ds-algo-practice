package com.dsalgo.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Given a start and end number.
Given an array.
Multiply the start with any entry from the array and try to reach the end number.
One multiplication is 1 step. Same number can be taken from the array multiple times.
After every multiplication, take mod of 1,00,000 and consider that as the new start.

Example :
start : 3, end : 75, array : {2,5,7}

Multiply 3 with 5, get 15, get mod - 15 % 1,00,000 = 15
Multiply 15 with 5, get 75, end is reached

Steps taken will be 2.

Solution :
Initially we take start, that is 3, and multiply it with all the entries in the array.
3*2 = 6
3*5 = 15
3*7 = 21

This is level_1

We then take 6 and multiply it with all the entries in the array
6*2 = 12
6*5 = 30
6*21 = 126
This is level_2

We then take 15 and multiply it with all the entries in the array
15*2 = 30
15*5 = 75
15*21 = 315
This is level_2

Same with 21 and get 3 more numbers at level_2

At each level, after getting the multiplication result, we will do a mod so that the number is always between 0 and 99999
If we have got 1,00,000 after the multiplication, taking the mod will make it 0
If we have got 99999 after the multiplication, taking the mod will make it 99999
If we have got 1,00,001 after the multiplication, taking the mod will make it 1

So, at every level, the value can be between 0 and 99999 both inclusive.

This level traversal is similar to Dijkstra's algo, where entry at every level can be a node.
When we did first multiplication and reached 6,15,21 from 3, we can say that distance taken was 1.

As at every level, the distance (steps) increases incrementally, we do not need to take a PQ, simple Q will work

 */
public class _34_MinimumMultiplicationsToReachEnd {
    private static final int MAX = 100000;
    private static final int INF = Integer.MAX_VALUE;

    private static int solve(int source, int target, int[] array) {
        int[] distance = new int[MAX];
        Arrays.fill(distance, INF);
        distance[source] = 0;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, source));   // steps, node

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> polled = queue.poll();
            if (polled.second == target) {
                return polled.first;    // return distance[polled]; if we use this, then rather than having Pair on the Q, we can just have the Node on the Q
            }
            for (int i = 0; i < array.length; i++) {
                int newNode = polled.second * array[i] % MAX;
                if (polled.first + 1 < distance[newNode]) {
                    distance[newNode] = polled.first + 1;
                    queue.add(new Pair<>(polled.first + 1, newNode));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] array = {2, 5, 7};
        System.out.println(solve(3, 75, array));
    }
}
