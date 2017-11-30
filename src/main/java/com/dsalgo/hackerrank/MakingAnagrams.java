package com.dsalgo.hackerrank;

import java.util.Scanner;

public class MakingAnagrams {
    public static final int MAX_ALPHABETS = 26;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a.toLowerCase(), b.toLowerCase()));
    }

    private static int numberNeeded(String first, String second) {
        int[] arrA = getCharCountArray(first);
        int[] arrB = getCharCountArray(second);
        int delta = getDelta(arrA, arrB);
        return delta;
    }

    private static int[] getCharCountArray(String a) {
        int[] array = new int[MAX_ALPHABETS];
        for (int i = 0; i < a.length(); i++) {
            array[a.charAt(i) - 'a']++;
        }
        return array;
    }

    private static int getDelta(int[] charCount1, int[] charCount2) {
        if (charCount1.length != charCount2.length) {
            return -1;
        }
        int result = 0;
        for (int i = 0; i < charCount1.length; i++) {
            result = result + Math.abs(charCount1[i] - charCount2[i]);
        }
        return result;
    }

}
