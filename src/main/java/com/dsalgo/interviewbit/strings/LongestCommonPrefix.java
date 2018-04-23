package com.dsalgo.interviewbit.strings;

import java.util.ArrayList;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        ArrayList<String> A = new ArrayList<>();
        A.add("abcdefghasdfsdfdsf");
        A.add("abcdefghijkasdf");
        A.add("abcdefghsd");

        System.out.println(lcp.longestCommonPrefix(A));
    }

    public String longestCommonPrefix(ArrayList<String> A) {
        if (A.isEmpty()) {
            return "";
        }
        if (A.size() == 1) {
            return A.get(0);
        }
        int strLen = A.get(0).length();
        int listSize = A.size();
        char ch;
        String out = "";
        boolean matches = false;

        for (int i = 0; i < strLen; i++) {
            ch = A.get(0).charAt(i);
            for (int j = 1; j < listSize; j++) {
                String str = A.get(j);
                if (i <= str.length() - 1 && str.charAt(i) == ch) {
                    matches = true;
                } else {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                out = out + ch;
            } else {
                break;
            }
        }

        return out;
    }
}
