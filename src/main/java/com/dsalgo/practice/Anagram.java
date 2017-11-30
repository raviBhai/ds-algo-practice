package com.dsalgo.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Anagram {

    public static final int NUMBER_OF_LETTERS = 26;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        String second = br.readLine();
        System.out.println(getDifferenceNumber(first, second));
    }

    public static int getDifferenceNumber(String first, String second) {
        int[] charCount1 = getCharCounts(first);
        int[] charCount2 = getCharCounts(second);
        return getDelta(charCount1, charCount2);
    }

    public static int[] getCharCounts(String s) {
        int[] charCount = new int[NUMBER_OF_LETTERS];
        int offset = (int) 'a';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charCount[c - offset]++;
        }
        return charCount;
    }

    public static int getDelta(int[] charCount1, int[] charCount2) {
        int result = 0;
        if (charCount1.length != charCount2.length) {
            return -1;
        }
        for (int i = 0; i < charCount1.length; i++) {
            int difference = Math.abs(charCount1[i] - charCount2[i]);
            result += difference;
        }
        return result;
    }
}
