package com.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _3_NDigitNumbersWithDigitsInIncreasingOrder {

    /**
     * time complexity
     *
     * if at each level, we consider that all 9 branches are being formed, then there will be 9 nodes at each level.
     * Hence, number of nodes combined at all n levels will be 9^n
     *
     * Work done at each node is O(1)
     *
     * Hence, overall time complexity will be O(9^n)
     *
     * @param n
     * @param intermediate
     * @param result
     */
    private static void solve(int n, List<Integer> intermediate, List<Integer> result) {
        if (n == 0) {
            int ans = 0;
            for (int i : intermediate) {
                ans = ans * 10 + i;
            }
            result.add(ans);
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (intermediate.isEmpty() || i > intermediate.get(intermediate.size() - 1)) {
                intermediate.add(i);
                solve(n - 1, intermediate, result);
                intermediate.remove(intermediate.size() - 1);
            }
        }
    }

    private static List<Integer> solve(int n) {
        List<Integer> intermediate = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        if (n == 1) {
            for (int i = 1; i <= 9; i++) {
                result.add(i);
            }
        } else {
            solve(n, intermediate, result);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(1));
        System.out.println(solve(2));
    }
}
