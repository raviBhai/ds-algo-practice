package com.dsalgo.dynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {
    private String word;
    private boolean[][] dpTable;
    private Set<String> dictionary;

    public WordBreak(String word, Set<String> dictionary) {
        this.word = word;
        this.dictionary = dictionary;
        dpTable = new boolean[word.length()][word.length()];
    }

    public boolean solve(String word) {

        int n = word.length();
        for (int l = 1; l <= n; l++) {
            int lastIndex = n - l;
            for (int i = 0; i <= lastIndex; i++) {
                System.out.println(word.substring(i, i + l) + " :: i - " + i + ", l - " + l);
                if (dictionary.contains(word.substring(i, i + l))) {
                    dpTable[i][i + l - 1] = true;
                } else {
                    System.out.print("Splits :: ");
                    for (int split = i+1; split < (i+l); split++) {
                        String first = word.substring(i, split);
                        String second = word.substring(split, i + l);
                        System.out.print("{" + first + ", " + second + "}; ");
                        //Both the split words are in dp, then the word should also be in dp
                        if (dpTable[i][split - 1] && dpTable[split][i + l - 1]) {
                            dpTable[i][i + l - 1] = true;
                            break;
                        }
                    }
                    System.out.println();
                }
            }
        }

        return dpTable[0][word.length() - 1];
    }

    public void solve() {
        int n = word.length();
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                String s = word.substring(i, i + l);
                if (dictionary.contains(s)) {
                    dpTable[i][i + l - 1] = true;
                } else {
                    for (int split = i + 1; split < (i + l); split++) {
                        if (dpTable[i][split - 1] && dpTable[split][i + l - 1]) {
                            dpTable[i][i + l - 1] = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<String>() {{
            add("i");
            add("a");
            add("am");
            add("ace");
        }};

        String word = "iamace";

        WordBreak wordBreak = new WordBreak(word, dictionary);

        if (wordBreak.solve(word)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
