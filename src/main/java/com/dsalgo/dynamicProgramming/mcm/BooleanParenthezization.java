package com.dsalgo.dynamicProgramming.mcm;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a boolean expression inputStr that contain symbols and operators.
 * The task is to count the number of ways we can parenthesize
 * the expression so that the value of the expression evaluates to true.
 */
public class BooleanParenthezization {

    public static int solve(String s, int i, int j, boolean req) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            if (req == true) {
                if (s.charAt(i) == 'T') {
                    return 1;
                } else {
                    return 0;
                }
            }

            if (req == false) {
                if (s.charAt(i) == 'F') {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        int ans = 0;

        for (int k = i + 1; k <= j - 1; k += 2) {
            int lt = solve(s, i, k - 1, true);
            int lf = solve(s, i, k - 1, false);

            int rt = solve(s, k + 1, j, true);
            int rf = solve(s, k + 1, j, false);

            if (s.charAt(k) == '&') {
                if (req == true) {
                    ans = ans + lt * rt;
                } else {    // req == false
                    ans = ans + lf * rt + lf * rf + lt * rf;
                }
            }

            if (s.charAt(k) == '|') {
                if (req == true) {
                    ans = ans + lt * rt + lt * rf + lf * rt;
                } else {    // req == false
                    ans = ans + lf * rf;
                }
            }

            if (s.charAt(k) == '^') {
                if (req == true) {
                    ans = ans + lt * rf + lf * rt;
                } else {    // req == false
                    ans = ans + lf * rf + lt * rt;
                }
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "T|T&F^T";
        System.out.println(solve(s, 0, s.length() - 1, true));
    }
}

class MemoizedBooleanParenthezization {

    public static Map<String, Integer> map = new HashMap<>();

    public static int solve(String s, int i, int j, boolean req) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            if (req == true) {
                if (s.charAt(i) == 'T') {
                    return 1;
                } else {
                    return 0;
                }
            }

            if (req == false) {
                if (s.charAt(i) == 'F') {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        String key = getKey(i, j, req);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int ans = 0;

        for (int k = i + 1; k <= j - 1; k += 2) {
            int lt = solve(s, i, k - 1, true);
            int lf = solve(s, i, k - 1, false);

            int rt = solve(s, k + 1, j, true);
            int rf = solve(s, k + 1, j, false);

            if (s.charAt(k) == '&') {
                if (req == true) {
                    ans = ans + lt * rt;
                } else {    // req == false
                    ans = ans + lf * rt + lf * rf + lt * rf;
                }
            }

            if (s.charAt(k) == '|') {
                if (req == true) {
                    ans = ans + lt * rt + lt * rf + lf * rt;
                } else {    // req == false
                    ans = ans + lf * rf;
                }
            }

            if (s.charAt(k) == '^') {
                if (req == true) {
                    ans = ans + lt * rf + lf * rt;
                } else {    // req == false
                    ans = ans + lf * rf + lt * rt;
                }
            }

        }
        map.put(key, ans);
        return map.get(key);
    }

    private static String getKey(int i, int j, boolean req) {
        return i + "_" + j + "_" + req;
    }

    public static void main(String[] args) {
        String s = "T|T&F^T";
        System.out.println(solve(s, 0, s.length() - 1, true));
    }
}