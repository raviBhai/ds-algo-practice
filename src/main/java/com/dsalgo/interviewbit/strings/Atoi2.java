package com.dsalgo.interviewbit.strings;

public class Atoi2 {

    public static void main(String[] args) {
        Atoi2 atoi2 = new Atoi2();
        System.out.println(atoi2.myAtoi(" -42"));
    }

    public int myAtoi(String str) {

        if (str == null || str.isEmpty()) {
            return 0;
        }

        int count = 0;
        while (count < str.length() && str.charAt(count) == ' ') {
            count++;
        }

        boolean isNeg = false;
        if (count < str.length() && str.charAt(count) == '-') {
            isNeg = true;
            count++;
        } else if (count < str.length() && str.charAt(count) == '+') {
            isNeg = false;
            count++;
        }

        char[] characters = new char[str.length()];
        int i = 0;
        while (count < str.length() && isCharAtCountInteger(str, count)) {
            characters[i++] = str.charAt(count);
            count++;
        }

        long num = 0;
        for (int j = 0; j < i; j++) {
            num = num * 10 + characters[j] - '0';
            if (num > Integer.MAX_VALUE) {
                if (isNeg) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }

        if (isNeg) {
            num = num * -1;
        }

        return (int) num;
    }

    private boolean isCharAtCountInteger(String str, int count) {
        return str.charAt(count) >= '0' && str.charAt(count) <= '9';
    }
}
