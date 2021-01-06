package com.dsalgo.interviewbit.stacksAndQueues;

import java.util.*;

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("a", "b"));
    }

    public static String minWindow(String s, String t) {

        if (s == null || t == null || s.length() == 0 || t.length() == 0 || t.length() > s.length()) {
            return "";
        }

        Map<Character, Boolean> subStrMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            subStrMap.put(t.charAt(i), Boolean.FALSE);
        }

        int count = 0, i;
        Deque<Character> q = new LinkedList<>();
        String result = "";

        for (i = 0; i < s.length(); i++) {
            q.addLast(s.charAt(i));
            if (subStrMap.get(s.charAt(i)) != null && subStrMap.get(s.charAt(i)) == false) {
                subStrMap.put(s.charAt(i), true);
                count++;
            }
            if (count == subStrMap.size()) {
                result = getStringFromQ(q);
                break;
            }
        }

        String temp = "";
        List<Character> duplicateList = new ArrayList<>();

        outer:
        while (i < s.length()) {
            while (subStrMap.get(q.peek()) != null) {


                i++;

                if (i == s.length()) {
                    break outer;
                }

                if (subStrMap.get(s.charAt(i)) != null && s.charAt(i) != q.peek()) {
                    duplicateList.add(s.charAt(i));
                    q.addLast(s.charAt(i));
                    continue;
                } else if (s.charAt(i) == q.peek()) {
                    q.removeFirst();
                    q.addLast(s.charAt(i));
                    temp = getStringFromQ(q);
                    if (temp.length() < result.length()) {
                        result = temp;
                    }
                    break;
                } else {
                    q.addLast(s.charAt(i));
                }
            }

            while (subStrMap.get(q.peek()) == null) {
                q.removeFirst();
                temp = getStringFromQ(q);
                if (temp.length() < result.length()) {
                    result = temp;
                }
            }

            while (duplicateList.contains(q.peek())) {
                duplicateList.remove(q.peek());
                q.removeFirst();
                temp = getStringFromQ(q);
                if (temp.length() < result.length()) {
                    result = temp;
                }
            }

        }

        return result;

    }

    private static String getStringFromQ(Deque q) {
        Iterator<Character> it = q.iterator();
        String s = "";
        while (it.hasNext()) {
            s += it.next();
        }
        return s;
    }
}
