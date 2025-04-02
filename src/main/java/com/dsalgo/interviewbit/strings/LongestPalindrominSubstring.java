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

class AnotherSolution {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    public static String longestPalindrome(String s) {

        int len = 0;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length()-1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (isPalindrome(s, i, j)) {
                        if (len < (j-i+1)) {
                            len = j-i+1;
                            res = s.substring(i, j+1);
                        }
                        break;
                    }
                }
            }
        }

        return res;
    }

    private static boolean isPalindrome(String s, int i, int j) {
        while (true) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
            if (i > j) {
                return true;
            }
        }
    }

    //use this
    private static boolean isPalindrome2(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
