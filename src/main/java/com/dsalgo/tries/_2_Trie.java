package com.dsalgo.tries;

/*
Build a data structure such that you can insert same word multiple times, and then count how many times you inserted it
You should also be able to count how many words start with a given prefix

Solution -
Store duplicate words - Trie

Count number of words -
Rather than having the flag "end" in the Trie's node, have a count.
When insertion of a word is complete, increment the last node's count.
When trying to get the count of words, trace over the word and reach the last node in Trie, and return the count.
Call this count as endsWith.


Count number of words starting with prefix -
Have another counter "countPrefix" within the node.
When you are trying to insert a word in trie, you traverse it node by node, that is, char by char.
Either you find an existing node or create a new one.
In either case, you get the current node.
Once you have the current node, increment the value of "countPrefix" in the current node.
When trying to find the number of words starting with prefix, reach the last node which becomes your current node and return its countPrefix

Erase word -
Erase the entire word, not the prefix.
Iterate over the word char by char, that is, node by node.
At every node, decrease the "countPrefix"
When you reach the last node, decrease the "endsWith" as well



 */

class Node2 {
    Node2[] links;
    int endsWith;
    int countPrefix;

    public Node2() {
        links = new Node2[26];
    }

    boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    void put(char ch, Node2 node) {
        links[ch - 'a'] = node;
    }

    Node2 get(char ch) {
        return links[ch - 'a'];
    }

    void increaseEndsWith() {
        endsWith++;
    }

    void decreaseEndsWith() {
        endsWith--;
    }

    void increaseCountPrefix() {
        countPrefix++;
    }

    void decreaseCountPrefix() {
        countPrefix--;
    }

    int getEndsWith() {
        return endsWith;
    }

    int getCountPrefix() {
        return countPrefix;
    }

}

public class _2_Trie {

    Node2 root;

    public _2_Trie() {
        root = new Node2();
    }

    public void insert(String word) {
        Node2 current = root;
        for (int i = 0; i < word.length(); i++) {
            if (!current.containsKey(word.charAt(i))) {
                current.put(word.charAt(i), new Node2());
            }
            current = current.get(word.charAt(i));
            current.increaseCountPrefix();
        }
        current.increaseEndsWith();
    }

    // ends with
    public int countWordsEqualTo(String word) {
        Node2 current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.containsKey(word.charAt(i))) {
                current = current.get(word.charAt(i));
            } else {
                return 0;
            }
        }
        return current.getEndsWith();
    }

    public int countWordsStartingWith(String word) {
        Node2 current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.containsKey(word.charAt(i))) {
                current = current.get(word.charAt(i));
            } else {
                return 0;
            }
        }
        return current.getCountPrefix();
    }

    // erase assumes that the entire word is present in trie.
    // never call erase to erase a prefix
    public void erase(String word) {
        Node2 current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.containsKey(word.charAt(i))) {
                current = current.get(word.charAt(i));
                current.decreaseCountPrefix();
            } else {
                return;
            }
        }
        current.decreaseEndsWith();
    }

    public static void main(String[] args) {
        _2_Trie trie = new _2_Trie();

        // insert 2 apple in trie
        trie.insert("apple");
        trie.insert("apple");

        System.out.println(trie.countWordsEqualTo("apple"));    // return 2

        trie.insert("apps");
        System.out.println(trie.countWordsEqualTo("app"));  // return 0, no complete word app present
        System.out.println(trie.countWordsEqualTo("apps")); // return 1
        System.out.println(trie.countWordsStartingWith("app")); // return 3

        trie.erase("apple");
        System.out.println(trie.countWordsEqualTo("apple"));    // return 1
    }

}
