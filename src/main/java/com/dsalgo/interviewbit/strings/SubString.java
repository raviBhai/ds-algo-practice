package com.dsalgo.interviewbit.strings;

public class SubString {
    public static void main(String[] args) {
        SubString sb = new SubString();
        String parent = "MyHellHellp";
        String sub = "ell";
        System.out.println(sb.strStr2(parent, sub));
    }

    public int strStr2(final String parent, final String sub) {
        if (parent == null || parent.isEmpty() || sub == null || sub.isEmpty()) {
            return -1;
        }
        int index = -1;
        boolean isFound = false;
        for (int i = 0; i <= parent.length() - sub.length(); i++) {
            int j;
            for (j = 0; j < sub.length(); j++) {
                if (sub.charAt(j) == parent.charAt(j + i)) {

                } else {
                    break;
                }
                if (j == sub.length() - 1) {
                    index = i;
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                break;
            }
        }
        return index;
    }

    public int strStr(final String parent, final String sub) {
        if (parent == null || parent.isEmpty() || sub == null || sub.isEmpty()) {
            return -1;
        }

        int index = -1;
        int pI = 0;
        int sI = 0;

        while (true) {
            if (sI == sub.length() || pI == parent.length()) {
                break;
            }
            if (pI <= parent.length() - 1 && sI <= sub.length() - 1 && parent.charAt(pI) == sub.charAt(sI)) {
                pI++;
                sI++;
            } else {
                sI = 0;
                if (pI <= parent.length() - 1 && sI <= sub.length() - 1 && parent.charAt(pI) == sub.charAt(sI)) {
                    continue;
                } else {
                    pI++;
                }
            }
        }
        if (sI == sub.length()) {
            index = pI - sI;
        }
        return index;
    }
}
