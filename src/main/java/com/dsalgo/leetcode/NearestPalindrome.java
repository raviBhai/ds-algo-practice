package com.dsalgo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class NearestPalindrome {
    public String nearestPalindromic(String n) {

        if (n == null || n.length() == 0) {
            return n;
        }

        int len = n.length();
        int i = len % 2 == 0 ? len / 2 - 1 : len / 2;
        long  left = Long.parseLong(n.substring(0, i+1));

        List<Long> candidates = new ArrayList<>();
        candidates.add(getPalindrome(left, len % 2 == 0));
        candidates.add(getPalindrome(left-1, len % 2 == 0));
        candidates.add(getPalindrome(left+1, len % 2 == 0));
        candidates.add((long)Math.pow(10, len-1) - 1);
        candidates.add((long)Math.pow(10, len) + 1);

        long min = Long.MAX_VALUE;
        long nl = Long.parseLong(n);
        long res=0;
        for (Long can : candidates) {
            if (can == nl) {
                continue;
            } else if (Math.abs(can - nl) < min) {
                min = Math.abs(can - nl);
                res = can;
            } else if (Math.abs(can - nl) == min) {
                res = Math.min(res, can);
            }
        }

        return String.valueOf(res);

    }

    private Long getPalindrome(long left, boolean even) {
        long res = left;
        if (!even) {
            left = left / 10;
        }
        while (left > 0) {
            res = res * 10 + left%10;
            left = left / 10;
        }
        return res;
    }

    public static void main(String[] args) {
        NearestPalindrome np = new NearestPalindrome();
        System.out.println(np.nearestPalindromic("123"));
    }
}
