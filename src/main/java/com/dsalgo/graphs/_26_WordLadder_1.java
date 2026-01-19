package com.dsalgo.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*

Given source word, target word and a list of words.
Source word may or may not be present in the list.
Target word may or may not be present in the list.

From the source word, change one alphabet, consider this as step 1.
In how many such steps can we reach the target word. Return this.
If it is not possible to reach the target word from the source word, return 0.

Solution:
Example -
source word - hit
target word - cog
list words - {hot, dot, dog, lot, log, cog}

In the brute force approach, we can take the source word, and replace first alphabet "h" with a to z.
So we get - ait, bit, cit, ..., zit
We do this for 2nd and 3rd alphabet as well.
These will generate 72 possible outcomes from hit.
Out of these 72, one of the word will be "hot" which was generated when we replaced "i" with "o"
As the word "hot" is present in the input list, this can lead to the result.
If the source word was at level_1, the generated word "hot" is at level_2.

From "hot", we can again generate 72 combinations -
Replace "h" - aot, bot, cot, ...., zot
Replace "o" - hat, hbt, hct, ...., hzt
Replace "t" - hoa, hob, hoc, ...., hoz

Out of these 72 words, 2 are in the list - dot, lot
These 2 words can lead to the result. These 2 words are at level_3.

Similarly, we can build next levels, and check if we can get the target word.

As we are doing level order traversal, we can use BFS

At every level, as we need to check if the word is part of the input list, we can store the input word list in a set.
In BFS, we would want to use the visited array to not process a word if it is touched/found in the input list.
Rather than having a visited array, as we are having a set, we can remove the word from the set itself.


Time complexity -
Let's say word length is n
And number of words in the list is m

Inner 2 for loops run for n * 26

for one word, it generates 26n combinations.
For m words, it will be 26 * n * m combinations.
In the worst case, all can go in the queue.
Hence, the queue runs for 26 * n * m, which is the time complexity of this algorithm.

 */
public class _26_WordLadder_1 {

    private static int solve(String source, String target, String[] list) {
        Set<String> set = new HashSet<>();
        for (String s : list) {
            set.add(s);
        }
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(source, 1));
        if (set.contains(source)) {
            set.remove(source);
        }
        while (!queue.isEmpty()) {
            Pair<String, Integer> currentNode = queue.remove();
            if (currentNode.first.equals(target)) {
                return currentNode.second;
            }
            for (int i = 0; i < currentNode.first.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] charArray = currentNode.first.toCharArray();
                    charArray[i] = ch;
                    String newWord = new String(charArray);
                    if (set.contains(newWord)) {
                        queue.add(new Pair<>(newWord, currentNode.second + 1));
                        set.remove(newWord);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String s1 = "hit";
        String s2 = "cog";
        String[] list = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(solve(s1, s2, list)); // output 5 as it takes these levels : hit > hot > dot > dog > cog
    }
}
