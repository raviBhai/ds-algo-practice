package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _13Permutations {

    public static void solve(String op, String ip, List<String> list) {
        if (ip.length() == 0) {
            list.add(op);
            return;
        }
        for (int i = 0; i < ip.length(); i++) {
            char ch = ip.charAt(i);
            String op1 = op + ch;
            String ip1 = ip.substring(0, i) + ip.substring(i + 1);
            solve(op1, ip1, list);
        }
    }

    public static List<String> solve(String ip) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < ip.length(); i++) {
            char ch = ip.charAt(i);
            String op1 = "" + ch;
            String ip1 = ip.substring(0, i) + ip.substring(i + 1);
            solve(op1, ip1, list);
        }
        return list;
    }

    public static void permutation(String ip, String op, List<String> list) {
        if (ip.length() == 0) {
            list.add(op);
            return;
        }
        for (int i = 0; i < ip.length(); i++) {
            char ch = ip.charAt(i);
            String op1 = op + ch;
            String ip1 = ip.substring(0, i) + ip.substring(i + 1);
            permutation(ip1, op1, list);
        }
    }

    /**
     * Time complexity (TC) -
     *
     * TC is work done in entire operation.
     * Work done  = workDoneInOneNode * numberOfNodes
     *
     * NumberOfNodes - numOfNodesAtLevel_1 + numOfNodesAtLevel_2 + numOfNodesAtLevel_3 + ...
     *
     *
     * Let's take example of input = abcd
     * numOfNodesAtLevel_1 = 1
     *
     * numOfNodesAtLevel_2 = 4 (as recursion will be called 4 times)
     *
     * numOfNodesAtLevel_3 =
     *     for every node in level_2, there will be 3 nodes at this level
     *     so, numOfNodesAtLevel_3 = 4 * 3
     *
     * numOfNodesAtLevel_4 =
     *     for every node in level_3, there will be 2 nodes at this level
     *     so, numOfNodesAtLevel_4 = 4 * 3 * 2 = 24
     *
     * Hence, number of nodes at level n = n!
     * numOfNodesAtLevel_n = n!
     *
     * Total number of nodes = numOfNodesAtLevel_1 + numOfNodesAtLevel_2 + ... + numOfNodesAtLevel_n
     *  = 1 + (4) + (4 * 3) + (4 * 3 * 2) + ... + n!
     *
     *  For time complexity, take the maximum in the above sum and ignore the rest.
     *  Rest can be ignored because for big values of n, the maximum value becomes very prminent and the
     *  rest becomes negligible
     *
     *  Hence, number of nodes can be approximated to n!
     *
     *  Work done in one node = O(1)
     *  Work done in all nodes = O(n!)
     *
     *  TC = O(n!)

     *
     * @param ip
     * @param op
     * @param list
     */
    public static void permutationWithDuplicates(String ip, String op, List<String> list) {
        if (ip.length() == 0) {
            list.add(op);
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < ip.length(); i++) {
            char ch = ip.charAt(i);
            if (!set.contains(ch)) {
                set.add(ch);
                String op1 = op + ch;
                String ip1 = ip.substring(0, i) + ip.substring(i + 1);
                permutationWithDuplicates(ip1, op1, list);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solve("abc"));
        //System.out.println(solve("abcd"));

        List<String> list = new ArrayList<>();
        permutation("abc", "", list);
        System.out.println(list);

        list = new ArrayList<>();
        permutationWithDuplicates("aab", "", list);
        System.out.println(list);
    }
}
