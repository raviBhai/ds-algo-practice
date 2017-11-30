package com.dsalgo.ctci;

public class UniqueCharacters {
    public static void main(String[] args) {
        System.out.println(isUniqueChars("ravi"));
    }

    private static boolean isUniqueChars(String input) {
        if (input.length() > 128) {
            return false;
        }
        boolean[] chars = new boolean[128];
        for (int i = 0; i < input.length(); i++) {
            int val = input.charAt(i);
            if (chars[val]) {
                return false;
            }
            chars[val] = true;
        }
        return true;
    }
}
