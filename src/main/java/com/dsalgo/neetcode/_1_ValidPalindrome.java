package com.dsalgo.neetcode;

public class _1_ValidPalindrome {

    public static boolean isPalindrome(String s) {


        int i = 0, j = s.length()-1;

        while(i <= j) {

            while(!isAlphaNumeric(s.charAt(i))) {
                i++;
            }

            while(!isAlphaNumeric(s.charAt(j))) {
                j--;
            }

            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    private static boolean isAlphaNumeric(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    public static void main(String[] args) {
        String s = "Was it a car or a cat I saw?";
        System.out.println(isPalindrome(s));
    }
}
