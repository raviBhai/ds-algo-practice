package com.dsalgo.leetcode;

public class CountAndSay {

    public static void main(String[] args) {
        System.out.println("result - " + countAndSay(4));
    }

    public static String countAndSay(int n) {

        if (n == 0) {
            return "";
        }

        return helper(n);
    }

    private static String helper(int n) {
        if (n == 1) {
            return "1";
        }
        String s = say(helper(n - 1));
        return s;
    }

    private static String say(String s) {
        if (s == "1") {
            return "11";
        } else if (s == "11") {
            return "21";
        } else if (s == "21") {
            return "1211";
        }
        return "";
    }
}
