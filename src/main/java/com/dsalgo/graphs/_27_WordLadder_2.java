package com.dsalgo.graphs;

import java.util.*;

/*

This is an extension of the _26_WordLadder_1
Given input as sourceWord, targetWord and a list of words,
return all the shortest sequences from source to target.

Example -
source - bat
target - coz
list - {pat, bot, pot, poz, coz}
output :
    - bat, pat, pot, poz, coz
    - bat, bot, pot, poz, coz

In the _26_WordLadder_1 question, we used Queue which stored the word and the sequence length.
In this question, we need to store the sequence as well.
Rather than storing all 3 things - word, length, sequence - on the queue, we can just store the sequence.
We can obtain the word by using the last entry in the sequence, and length is not required for this problem.

Handle visited :
In the _26_WordLadder_1 question, the moment we saw that the newWord is part of the input list, we removed it from the set.
Here, we should NOT do the same.

So, when to remove it from the set ?
The queue contains sequences, and a list of sequences can belong to the same level.
The moment all the sequences from the same level are processed from the queue, and we try to process the sequence from the new level,
we can delete the entry from the set.
The moment we get a sequence from the new level, we can get the last word from the sequence which will be the newWord.
We can then delete the newWord from the set.

Example -
start word - bat
Replacing 1st alphabet, we get 26 combinations, out of which one is in the input list - pat
Replacing 2nd alphabet, we get 26 combinations, out of which one is in the input list - bot

Below is the tree that we get. At each level, we store a list.
To generate the new level, take the last word from the sequence of the previous level.
To generate level 3, take "pat" from the level_2 and generate "pot" and add it to the sequence.

                    bat
                    /\
                   /  \
                  /    \
                bat,   bat,
                pat    bot
                 |      |
                 |      |
                bat,    bat,
                pat,    bot,
                pot     pot

At level 3 in the path root > left > left, the moment we encounter "pot", we do not remove it from the set.
In this path, we got "pot" from "pat"
We can get "pot" again in another path root > right > left, when we modify "bot" and get "pot".
If we had removed "pot" in path_1, we could have not got it in path_2
And, as both the paths are valid, and can be the answer, we should not remove the word the moment we encounter it.
However, we can remove the word when all the entries in the previous level are processed from the queue.

 */
public class _27_WordLadder_2 {

    private static List<List<String>> solve(String source, String target, String[] list) {
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        for (String s : list) {
            set.add(s);
        }
        queue.add(Arrays.asList(source));

        int currentLevel = 0;
        List<String> toBeRemoved = new ArrayList<>();

        List<List<String>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<String> currentLevelSequence = queue.poll();
            String lastWord = currentLevelSequence.get(currentLevelSequence.size() - 1);
            if (lastWord.equals(target)) {

                // if result is empty, this is the first shorted sequence, hence add to the result
                if (result.isEmpty()) {
                    result.add(currentLevelSequence);
                } else if (result.get(result.size() - 1).size() == currentLevelSequence.size()) {
                    // if result is not empty, it means, we already have a shorted sequence in the result. If the current sequence length is same as existing sequence length, then add current sequence to the list
                    result.add(currentLevelSequence);
                }
            }

            // remove from the set
            // the moment I encounter a list of bigger size, it means I moved to the next level, hence do currentLevel++
            if (currentLevelSequence.size() > currentLevel) {
                currentLevel++;
                // the moment I moved to the next level, remove the newly added words from the set
                for (String s : toBeRemoved) {
                    set.remove(s);
                }
                toBeRemoved = new ArrayList<>();
            }


            for (int i = 0; i < lastWord.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    String newWord = lastWord.substring(0, i) + ch + lastWord.substring(i + 1);
                    if (set.contains(newWord)) {
                        List<String> newSequence = new ArrayList<>(currentLevelSequence);
                        newSequence.add(newWord);
                        queue.add(newSequence);
                        toBeRemoved.add(newWord);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s1 = "bat";
        String s2 = "coz";
        String[] list = {"pat", "bot", "pot", "poz", "coz"};
        System.out.println(solve(s1, s2, list));
    }
}
