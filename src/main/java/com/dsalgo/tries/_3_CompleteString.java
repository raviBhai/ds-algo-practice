package com.dsalgo.tries;

import java.util.ArrayList;
import java.util.List;

/*

Given a list of String.
A particular string can have prefixes.
If all the prefixes of a string are present in the array, then the string is a complete string.

List - { n, ninja, ninj, ni, nin, ninga}

The string ninja has the prefixes - n, ni, nin, ninj, ninja
All these are present in the input list, hence ninja is a complete string.

If there are multiple complete strings, return the longest.
If there are 2 complete strings of same length, then return the lexicographically smaller one.

Algorithm -

Brute force -
1. Store all the strings from the input list in a set
2. Then iterate over the input list again, and for every string, generate the prefixes.
3. Then check if all the prefixes are present in the set.


Better approach -
As we are storing multiple strings, we can use a trie.
Store all the strings in the input list in a trie.

For every string, the end char's node will have end=true in trie.
We can take advantage of this, and check all prefixes for each string.
How ?
1. For every string in the input, iterate over the chars in it.
2. If the trie has the node at that char with end=true, it means that a string ending at this char was present in the input list, that is why the current char has end=true

 */

public class _3_CompleteString {


}

class _Trie_CompleteString {
    Node root;

    public _Trie_CompleteString() {
        root = new Node();
    }

    public void insert(String word) {
        Node current  = root;
        for (int i = 0; i < word.length(); i++) {
            if (!current.containsKey(word.charAt(i))) {
                current.put(word.charAt(i), new Node());
            }
            current = current.get(word.charAt(i));
        }
        current.setEnd();
    }

    private boolean checkAllPrefixes(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.containsKey(word.charAt(i))) {
                current = current.get(word.charAt(i));
            } else {
                // current char not present in trie
                return false;
            }
            if (!current.isEnd()) {
                // current char present but its prefix ending at current char not present in trie
                return false;
            }
        }
        return true;
    }

    public String completeString(List<String> input) {
        for (String s : input) {
            this.insert(s);
        }

        String longest = "";
        for (String s : input) {
            if (this.checkAllPrefixes(s)) {
                if (s.length() > longest.length()) {
                    longest = s;
                } else if (s.length() == longest.length() && s.compareTo(longest) < 0) {
                    longest = s;
                }
            }
        }

        if (longest.equals("")) {
            return "None";
        }
        return longest;
    }

    public static void main(String[] args) {
        _Trie_CompleteString trie = new _Trie_CompleteString();
        List<String> list = new ArrayList<>();
        list.add("n");
        list.add("ninja");
        list.add("ninj");
        list.add("ni");
        list.add("nin");
        list.add("ninga");

        System.out.println(trie.completeString(list));
    }
}