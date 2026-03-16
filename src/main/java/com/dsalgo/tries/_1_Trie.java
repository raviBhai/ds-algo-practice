package com.dsalgo.tries;

class Node {
    Node[] links;
    boolean end;

    public Node() {
        links = new Node[26];
    }

    boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    void put(char ch, Node node) {
        links[ch - 'a'] = node;
    }

    Node get(char ch) {
        return links[ch - 'a'];
    }

    void setEnd() {
        end = true;
    }

    boolean isEnd() {
        return end;
    }
}


public class _1_Trie {

    private final Node root;

    public _1_Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            if (!current.containsKey(word.charAt(i))) {
                current.put(word.charAt(i), new Node());
            }
            current = current.get(word.charAt(i));
        }
        current.setEnd();
    }

    public boolean search(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            if (!current.containsKey(word.charAt(i))) {
                return false;
            }
            current = current.get(word.charAt(i));
        }
        if (current.isEnd()) {
            return true;
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!current.containsKey(prefix.charAt(i))) {
                return false;
            }
            current = current.get(prefix.charAt(i));
        }
        return true;
    }

    public static void main(String[] args) {
        _1_Trie trie = new _1_Trie();
        trie.insert("apple");
        trie.insert("apps");
        trie.insert("bag");
        trie.insert("bat");

        System.out.println(trie.search("bag"));     // true
        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("appx"));    // false

        System.out.println(trie.startsWith("ba"));          // true
        System.out.println(trie.startsWith("bx"));          // false
    }
}
