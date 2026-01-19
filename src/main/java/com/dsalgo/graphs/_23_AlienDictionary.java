package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;

/*

Given N strings and an integer K.

If K is 5, it denotes first 5 alphabets - a to e
The N strings will be made up of only these 5 alphabets.

Aliens use these alphabets, but their ordering is different.
For us, the order is a,b,c,d,e
For aliens, the order can be anything, such as, b,d,a,e,c
The N strings are in a dictionary order, the order of the dictionary will be the order of the aliens.

Output - Return the order of the aliens

Example -
K = 4
N :
b a a
a b c d
a b c a
c a b
c a d

In any dictionary, to order any 2 strings, only one char difference is required.
Let's say, we have 2 strings as baa and abcd, we start tracing them from the start and keep tracing till we find a difference of char.
At 0th char, we find the difference (b and a), hence, we can say that, b comes before a.

If we had cab and cad as strings, we start tracing from the start, and see that first 2 chars are same, but 3rd char is different.
Hence, b comes before d

Every char can be treated as node, and while tracing the dictionary, we can link the nodes.
Once all the strings are traced, we would have a directed graph.
We can then do topo sort on this to get the output.
For the above example, the alien order is b,d,a,c

In the above example, if K were 5 and N would still be same, then it would mean there would be 5 chars - a,b,c,d,e
In this case, a,b,c,d would form one component and e would form another component of the graph.

How to take 2 strings for tracing :
take 1st and 2nd string, trace them and get the nodes.
then take 2nd and 3rd string, trace them and get the nodes.
then take 3rd and 4th string, trace them and get the nodes.
then take 4th and 5th string, trace them and get the nodes.


Follow up questions:
1. When is the ordering not possible ?
Answer - In 2 cases:
Case_1: When tracing the 2 strings, if their lengths are different, the larger string appears before shorter and shorter is substring of longer
    - example: abcd, abc - while tracing, we will compare first 3 chars, and there would be nothing to determine the order.
    - to handle this, modify the for loops while tracing S1 and S2. It is not done in the code below.

Case_2: When there is cyclic dependency:
abc
bac
acb

First 2 strings give the order a -> b
Next 2 strings give the order b -> a
In this case, the output of topo sort will not have the same length as that of N. It is not done in the code below.

 */
public class _23_AlienDictionary {

    private static String solve(String[] dictionary, int K) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int i = 0; i < dictionary.length - 1; i++) {
            String s1 = dictionary[i];
            String s2 = dictionary[i+1];
            int minLen = Math.min(s1.length(), s2.length());
            for (int j = 0; j < minLen; j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    adjacencyList.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    break;
                }
            }
        }

        // use bfs or dfs to do topo sort
        List<Integer> sortedOrder = _19_TopologicalSortUsingBfs_KahnAlgo.topo(adjacencyList);
        //List<Integer> sortedOrder = _18_TopologicalSortUsingDfs.topologicalSort(adjacencyList);
        StringBuilder sb = new StringBuilder();
        for (int i : sortedOrder) {
            sb.append((char) (i + 'a'));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] dictionary = {
          "baa",
          "abcd",
          "abca",
          "cab",
          "cad"
        };
        System.out.println(solve(dictionary, 4));   // bdac
        System.out.println(solve(dictionary, 5));   // bedac, e can be anywhere
    }
}
