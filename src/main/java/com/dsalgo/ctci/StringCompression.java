package com.dsalgo.ctci;

public class StringCompression {
    public static void main(String[] args) {
        System.out.println(compress("aabcccccaaa"));
        System.out.println(compress("a"));
        System.out.println(compress("aa"));
        System.out.println(compress("aaa"));
        System.out.println(compress("abcd"));
        System.out.println(compress("AAAaaaDbbEE"));
    }

    private static String compress(String s) {
        char previous = ' ', current = ' ';
        int counter = 1, i;

        StringBuilder sb = new StringBuilder();

        for (i = 0; i < s.length(); i++) {
            if (i != 0) {
                previous = s.charAt(i - 1);
            }
            current = s.charAt(i);

            if (previous == current) {
                counter++;
            } else {
                if (previous != ' ') {
                    sb.append(previous).append(counter);
                    counter = 1;
                }
            }
        }
        sb.append(previous).append(counter);

        if (s.length() > sb.length()) {
            return sb.toString();
        }

        return s;
    }
}
