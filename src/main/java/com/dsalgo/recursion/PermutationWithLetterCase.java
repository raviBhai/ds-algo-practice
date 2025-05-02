package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Input = "a1B2"
 * In the above input, either of a or b can be small or in caps case.
 * In the output, convert small to caps or vice-versa and print permutations
 * Output - "a1B2", "a1b2", "A1B2", "A1b2"
 */
public class PermutationWithLetterCase {

    public static List<String> solve(String input) {
        List<String> list = new ArrayList<>();
        solve(input, "", list);
        return list;
    }

    private static void solve(String ip, String op, List<String> list) {
        if (ip.length() == 0) {
            list.add(op);
            return;
        }
        if ((ip.charAt(0) >= 'a' && ip.charAt(0) <= 'z')
                || (ip.charAt(0) >= 'A' && ip.charAt(0) <= 'Z')) {
            String op1 = op;
            String op2 = op;

            op1 = op1 + Character.toUpperCase(ip.charAt(0));
            op2 = op2 + Character.toLowerCase(ip.charAt(0));

            ip = ip.substring(1);

            solve(ip, op1, list);
            solve(ip, op2, list);
        } else {
            String op1 = op + ip.charAt(0);
            ip = ip.substring(1);
            solve(ip, op1, list);
        }
    }

    public static void main(String[] args) {
        List<String> list = solve("a1B2");
        System.out.println(list);
    }
}
