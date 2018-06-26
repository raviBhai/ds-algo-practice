package com.dsalgo.tries;

public class StoreIPAddress {
    public static final int CHARACTERS = 255;

    static class TrieNode {
        TrieNode[] children = new TrieNode[CHARACTERS];
        boolean isEndOfWord;
    }

    static TrieNode root;

    public static void insert(String key) {
        int index;
        TrieNode current = root;
        for (int i = 0; i < key.length(); i++) {
            index = key.charAt(i);
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    public static boolean search(String key) {
        int index;
        TrieNode current = root;
        for (int i = 0; i < key.length(); i++) {
            index = key.charAt(i);
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return (current != null && current.isEndOfWord);
    }

    public static void main(String[] args) {
        root = new TrieNode();
        String allValidIps[] = {"10.20.101.200", "10.30.101.200", "10.20.111.200", "10.20.101.201"};

        for (String ip : allValidIps) {
            insert(ip);
        }

        String testIps[] = {"10.20.101.20", "10.30.111.200", "10.20.111.201", "10.20.101.201"};
        for (String ip : testIps) {
            if (search(ip)) {
                System.out.println("valid ip");
            } else {
                System.out.println("invalid ip");
            }
        }

    }

}
