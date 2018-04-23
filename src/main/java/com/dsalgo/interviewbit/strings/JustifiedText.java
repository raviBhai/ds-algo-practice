package com.dsalgo.interviewbit.strings;

import java.util.ArrayList;

public class JustifiedText {
    public static void main(String[] args) {
        JustifiedText jft = new JustifiedText();
        ArrayList<String> A = new ArrayList<String>() {{
            /*add("This");
            add("is");
            add("an");
            add("example");
            add("of");
            add("text");
            add("justification.");*/

            //What must be
            //shall be.

            add("What");
            add("must");
            add("be");
            add("shall");
            add("be.");
        }};
        System.out.println(jft.fullJustify(A, 12));
    }


    public ArrayList<String> fullJustify(ArrayList<String> A, int B) {
        ArrayList<String> res = new ArrayList<>();
        if (A == null || A.isEmpty()) {
            return res;
        }

        res.add(A.get(0));

        String lastWord = "";
        int lastLength = 0;

        for (int i = 1; i < A.size(); i++) {
            lastLength = res.get(res.size()-1).length();
            if (lastLength + A.get(i).length() + 1 <= B) {
                lastWord = res.get(res.size()-1);
                lastWord = lastWord + " " + A.get(i);
                res.set(res.size() - 1, lastWord);
            } else {
                res.add(A.get(i));
            }
        }
        System.out.println(res);

        int numSpaces = 1;
        int diffSpaces = 0;
        int additionalSpace = 0;
        int remainderSpace = 0;
        boolean isLastSentence = false;
        for (int i = 0; i < res.size(); i++) {
            String sentence = res.get(i);
            diffSpaces = B - sentence.length();
            numSpaces = numSpaces(sentence);
            if (i == res.size() - 1) {
                isLastSentence = true;
            }
            if (numSpaces == 0 || isLastSentence) {
                additionalSpace = 0;
                remainderSpace = diffSpaces;
            } else {
                additionalSpace = diffSpaces / numSpaces;
                remainderSpace = diffSpaces % numSpaces;
            }

            char[] tmp = new char[sentence.length() + diffSpaces];
            int counter = 0;
            if (!isLastSentence) {

            }
            for (int j = 0; j < sentence.length(); j++) {
                if (sentence.charAt(j) == ' ') {
                    tmp[counter++] = ' ';
                    for (int k = 1; k <= additionalSpace; k++) {
                        tmp[counter++] = ' ';
                    }

                    if (!isLastSentence) {
                        if (remainderSpace > 0) {
                            tmp[counter++] = ' ';
                            remainderSpace--;
                        }
                    }
                } else {
                    tmp[counter++] = sentence.charAt(j);
                }
            }
            while (remainderSpace > 0) {
                tmp[counter++] = ' ';
                remainderSpace--;
            }
            res.set(i, String.valueOf(tmp));
        }
        //System.out.println(res);
        return res;
    }

    private int numSpaces(String word) {
        int numSpaces = 0;
        char ch;
        for (int i = 0; i < word.length(); i++) {
            ch = word.charAt(i);
            if (ch == ' ') {
                numSpaces++;
            }
        }
        return numSpaces;
    }
}
