package com.dsalgo.recursion;

/**
 * Write a function which takes 2 input integers N and K,
 * and then returns an output integer for the given N and K
 * N and K start from 1.
 * If N == 1 and K == 1, then return 0.
 *
 * To get output for any N, get the output for the previous N.
 * Then start tracing the previous N from left to right.
 * While tracing
 *      if a 0 is encountered, write 01 to the output of N.
 *      if a 1 is encountered, write 10 to the output of N.
 *
 * Example
 * N is on rows, K is on columns
 *
 * N=1 => 0
 * N=2 => 0 1
 * N=3 => 0 1 1 0
 * N=4 => 0 1 1 0 1 0 0 1
 *
 * N=4 has length of 8. Here, K is from 1 to 8.
 * So, for input N=4, K=5, return 1
 * for input N=4, K=6, return 0
 *
 *
 * Solution -
 * For N = 4, the length is Math.pow(2, N-1)
 * If the output of N=4 is divided in 2 halves at the center,
 * the first half is same as that of N=3 and the second half is complement of 1st half.
 * Given this, check if K lies in first half or second half and then recurse.
 */
public class _5KthSymbolInGrammar {
    public static int solve(int n, int k) {
        if (n == 1 && k == 1) {
            return 0;
        }
        int mid = ((int) Math.pow(2, n - 1)) / 2;
        if (k <= mid) {
            return solve(n - 1, k);
        } else {
            int prev = solve(n - 1, k - mid);   // if k == 9, then call solve(n, 1). IF k == 10, then call solve(n, 2). Hence doing k-mid
            return prev == 0 ? 1 : 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve(4,5));
        System.out.println(solve(4,6));
    }
}
