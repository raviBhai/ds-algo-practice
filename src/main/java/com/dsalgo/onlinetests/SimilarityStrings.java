package com.dsalgo.onlinetests;

import java.util.ArrayList;
import java.util.List;

public class SimilarityStrings {

    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        inputs.add("ababaa");
        inputs.add("ababa");
        inputs.add("aa");
        List<Integer> result = usernameDisparity(inputs);
        System.out.println(result);
    }

    public static List<Integer> usernameDisparity(List<String> inputs) {
        List<Integer> result = new ArrayList<>();
        for(String input : inputs) {
            int similaritySum = getSimilaritySum(input);
            result.add(similaritySum);
        }
        return result;
    }

    private static int getSimilaritySum_working(String input) {
        int similaritySum = 0;

        for(int i = 0; i < input.length(); i++) {
            similaritySum += getSimilarity(input, input.substring(i, input.length()));
        }

        return similaritySum;
    }

    private static int getSimilaritySum(String input) {
        int similaritySum = input.length();
        for (int i = 1; i < input.length(); i++) {
            int j = i;
            while (input.charAt(j) == input.charAt(j - i)) {
                similaritySum++;
                j++;
                if (j >= input.length()) {
                    break;
                }
            }
        }

        return similaritySum;
    }

    private static int getSimilaritySum_2(String input) {
        int similaritySum = input.length();
        int i = 0;
        int prev = 2;
        int j = 1;
        while(i < input.length() && j < input.length()) {
            if(input.charAt(i) == input.charAt(j)) {
                similaritySum++;
                i++;
                j++;
                if(j >= input.length()) {
                    i = 0;
                    j = prev;
                    prev++;
                }
            } else {
                i = 0;
                j = prev;
                prev++;
            }
        }
        return similaritySum;
    }

    private static int getSimilarity(String s1, String s2) {
        int similarity = 0;
        int i = 0, j = 0;

        while(i < s1.length() && j < s2.length()) {
            if(s1.charAt(i) == s2.charAt(j)) {
                similarity++;
                i++;
                j++;
            } else {
                break;
            }
        }

        return similarity;
    }
}
