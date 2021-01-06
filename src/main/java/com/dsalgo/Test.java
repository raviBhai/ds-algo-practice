package com.dsalgo;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        Test test = new Test();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(test.ladderLength(beginWord, endWord, wordList));


    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (beginWord == null || beginWord.length() == 0 ||
                endWord == null || endWord.length() == 0 ||
                wordList == null || wordList.size() == 0) {
            return 0;
        }

        Set<String> chain = new LinkedHashSet<>();
        chain.add(beginWord);
        List<Integer> chainLengths = new ArrayList<>();
        measureChainLength(beginWord, endWord, chain, chainLengths, wordList);
        return maxLength(chainLengths);
    }

    private int maxLength(List<Integer> chainLengths) {
        if (chainLengths == null || chainLengths.size() == 0) {
            return 0;
        }

        Integer maxLen = Integer.MIN_VALUE;
        for (Integer currLen : chainLengths) {
            if (maxLen < currLen) {
                maxLen = currLen;
            }
        }
        return maxLen;
    }

    private void measureChainLength(String currWord, String endWord,  Set<String> chain, List<Integer> chainLengths, List<String> wordList) {
        if (currWord.equalsIgnoreCase(endWord)) {
            System.out.println(chain);
            chainLengths.add(chain.size());
            return;
        }

        for (String dictWord : wordList) {
            if (!chain.contains(dictWord) && isOneReplaceAway(dictWord, currWord)) {
                Set<String> newChain = new LinkedHashSet<>(chain);
                newChain.add(dictWord);
                measureChainLength(dictWord, endWord, newChain, chainLengths, wordList);
            }
        }

    }

    private boolean isOneReplaceAway(String s1, String s2) {
        boolean isDiffEncountered = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (isDiffEncountered) {
                    return false;
                }
                isDiffEncountered = true;
            }
        }
        return true;
    }

}
