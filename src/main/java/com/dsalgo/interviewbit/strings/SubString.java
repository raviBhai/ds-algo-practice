package com.dsalgo.interviewbit.strings;

public class SubString {
    public static void main(String[] args) {
        SubString sb = new SubString();
        String parent = "MyHellHellp";
        String sub = "ell";
        //System.out.println(sb.strStr2(parent, sub));
        System.out.println(sb.strstr(parent, sub));
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

    public int strstr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (haystack.isEmpty() && needle.isEmpty()) {
            return 0;
        }
        if (haystack.isEmpty()) {
            return -1;
        }
        if (needle.isEmpty()) {
            return 0;
        }
        int len1 = haystack.length();
        int len2 = needle.length();
        for (int i = 0; i <= len1 - len2; i++) {
            if (haystack.charAt(i) != needle.charAt(0)) {
                continue;
            } else {
                int j = 0;
                while (j < needle.length() && haystack.charAt(i + j) == needle.charAt(j)) {
                    j++;
                    if (j == needle.length()) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
