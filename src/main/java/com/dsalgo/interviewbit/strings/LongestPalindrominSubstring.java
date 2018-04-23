package com.dsalgo.interviewbit.strings;

public class LongestPalindrominSubstring {
    public static void main(String[] args) {
        LongestPalindrominSubstring lps = new LongestPalindrominSubstring();
        System.out.println(lps.longestPalindrome("rrrraaaamadamvi"));
        System.out.println(lps.longestPalindrome("aaaabaaa"));
    }

    public String longestPalindrome(String A) {
        if (A.isEmpty()) {
            return "";
        }
        String str = "";
        String tmpStr = "";
        char firstPtr;
        int start, end;
        boolean isPalindromeFound = false;
        for (int i = 0; i < A.length(); i++) {
            firstPtr = A.charAt(i);
            int j;
            for (j = A.length() - 1; j > i; j--) {
                if (firstPtr == A.charAt(j)) {
                    isPalindromeFound = false;
                    //break;
                    if (i == j) {
                        continue;
                    }
                    start = i;
                    end = j;
                    while (true) {
                        if (start > end) {
                            tmpStr = A.substring(i, j + 1);
                            if (str.length() < tmpStr.length()) {
                                str = tmpStr;
                            }
                            isPalindromeFound = true;
                            break;
                        } else {
                            if (A.charAt(start) == A.charAt(end)) {
                                start++;
                                end--;
                            } else {
                                break;
                            }
                        }
                    }
                    if (isPalindromeFound) {
                        break;
                    }
                }
            }


        }
        if (str.isEmpty()) {
            str = A.substring(0, 1);
        }
        return str;
    }
}
