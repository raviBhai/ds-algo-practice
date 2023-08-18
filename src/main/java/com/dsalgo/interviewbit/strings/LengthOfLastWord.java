package com.dsalgo.interviewbit.strings;

public class LengthOfLastWord {
    public static void main(String[] args) {
        LengthOfLastWord len = new LengthOfLastWord();
        System.out.println(len.lengthOfLastWord("sadfsdas !@#%   "));
    }

    public int lengthOfLastWord(final String A) {
        int length = 0;
        boolean isCharEncountered = false;
        int i;
        for (i = A.length() - 1; i >= 0; i--) {

            if (isCharEncountered) {
                if (A.charAt(i) != ' ') {
                    length++;
                    continue;
                } else {
                    break;
                }
            } else {
                if (A.charAt(i) != ' ') {
                    isCharEncountered = true;
                    length++;
                }
            }
        }
        return length;
    }
}
