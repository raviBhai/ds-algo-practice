package com.dsalgo.ctci;

public class StringCompression {
    public static void main(String[] args) {
        System.out.println(compress("aabcccccaaa"));
        System.out.println(compress("a"));
        System.out.println(compress("aa"));
        System.out.println(compress("aaa"));
        System.out.println(compress("abcd"));
        System.out.println(compress("AAAaaaDbbEE"));

        System.out.println(("***************************"));

        System.out.println(compress_2("aabcccccaaa"));
        System.out.println(compress_2("a"));
        System.out.println(compress_2("aa"));
        System.out.println(compress_2("aaa"));
        System.out.println(compress_2("abcd"));
        System.out.println(compress_2("AAAaaaDbbEE"));

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

    public static String compress_2(String s) {
        char prev = ' ', curr = ' ';
        int count = 1;
        StringBuilder sb = new StringBuilder();
        prev = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            curr = s.charAt(i);
            if (prev == curr) {
                count++;
            } else {
                sb.append(prev).append(count);
                count = 1;
            }
            prev = curr;
        }
        sb.append(curr).append(count);
        if (s.length() > sb.length()) {
            return sb.toString();
        }
        return s;
    }
}
