package com.dsalgo.ctci;

public class PermutationOfPalindrome {

    public static final int MAX_CHARS = 256;

    public static void main(String[] args) {
        String s = "ravi";
        System.out.println(isPermutationOfPalindrome("ravi"));
        System.out.println(isPermutationOfPalindrome("tactcoapapa"));

        System.out.println(isPermutationOfPalindrome2("ravi"));
        System.out.println(isPermutationOfPalindrome2("tactcoapapa"));
    }

    private static boolean isPermutationOfPalindrome(String input) {
        int[] charCount = new int[MAX_CHARS];
        int ch, i, numberOfOddChars = 0;

        for (i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            charCount[ch]++;
        }

        for (i = 0; i < MAX_CHARS; i++) {
            if (charCount[i] % 2 != 0) {
                numberOfOddChars++;
            }
            if (numberOfOddChars > 1) {
                return false;
            }
        }
        return true;
    }

    //Improvement over above function
    private static boolean isPermutationOfPalindrome2(String input) {
        int[] charCount = new int[MAX_CHARS];
        int ch, i, numberOfOddChars = 0;

        for (i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            charCount[ch]++;
            if (charCount[ch] % 2 != 0) {
                numberOfOddChars++;
            } else {
                numberOfOddChars--;
            }
        }

        return numberOfOddChars <= 1;
    }

}
