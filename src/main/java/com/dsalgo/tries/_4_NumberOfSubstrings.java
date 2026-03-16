package com.dsalgo.tries;

class Node4 {
    Node4[] links;

    public Node4() {
        links = new Node4[26];
    }

    boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    void put(char ch, Node4 node) {
        links[ch - 'a'] = node;
    }

    Node4 get(char ch) {
        return links[ch - 'a'];
    }
}


/*

Trie class is not required here

 */
class _4_Trie {
    private final Node4 root;

    public _4_Trie() {
        root = new Node4();
    }
}

/*

Given an input string, return the number of all the unique substrings from it. Include empty substring as well as part of result.
input s = abab
indexes - 0123

output - 8  (return the count, not the substrings)
substrings - {  a, ab, aba, abab, b, ba, bab, a, ab, b, "" }
unique-substrings - {  a, ab, aba, abab, b, ba, bab, "" }

To generate all substrings, we will need 2 nested for loops -

Outer loop starts at 0
Inner loop will start at outer(0)
    inner loop counter - 0
        substring - (outer,inner) - (0,0) - a
    inner loop counter - 1
        substring - (outer,inner) - (0,1) - ab
    inner loop counter - 2
        substring - (outer,inner) - (0,2) - aba
    inner loop counter - 3
        substring - (outer,inner) - (0,3) - abab

We will store every substring in a Trie.
We initialize the root for the trie and make current point to root.
Now, let's try inserting "a" in the root, and then move the current to the trie node of a.
Inner loop will now move the counter to index(1) which is b, hence we will try to insert b in trie.
At the end, when outer loop counter was at 0, we moved inner loop counter from 0 to 3 and pushed all chars in the trie.

Now, when the outer loop counter moves to 1, we again start with root, hence mark current to root.

Outer loop starts at 1
Inner loop will start at outer(1)
    inner loop counter - 1
        substring - (outer,inner) - (1,1) - b
    inner loop counter - 2
        substring - (outer,inner) - (1,2) - ba
    inner loop counter - 3
        substring - (outer,inner) - (1,3) - bab

We now will want to push the char "b" to the trie in the root node, and then move ahead till we push bab to trie.

Outer loop starts at 2
Inner loop will start at outer(2)
    inner loop counter - 2
        substring - (outer,inner) - (2,2) - a
    inner loop counter - 3
        substring - (outer,inner) - (2,3) - ab

We now will want to push the char "a" to the trie in the root node.
The root already has "a", hence we move to the next char b, and to next trie node, which also has "b"
When the trie already has the char, it means, the substring until that char is already present in the trie.

It means, whenever the char is not present in the trie, it is part of unique substring, hence increase counter.

 */
public class _4_NumberOfSubstrings {

    public static int countNumberOfSubstrings(String s) {
        Node4 root = new Node4();

        int count = 0;

        for (int i = 0; i < s.length(); i++) {

            // mark current to root every time we start with the outer loop
            Node4 current = root;

            for (int j = i; j < s.length(); j++) {
                if (!current.containsKey(s.charAt(j))) {
                    current.put(s.charAt(j), new Node4());
                    count++;    // increment counter everytime the char is not found in trie
                }
                current = current.get(s.charAt(j));
            }
        }

        count += 1;     // increment by 1 to account for empty substring
        return count;
    }

    public static void main(String[] args) {
        String s = "abab";
        System.out.println(_4_NumberOfSubstrings.countNumberOfSubstrings(s));
    }
}
