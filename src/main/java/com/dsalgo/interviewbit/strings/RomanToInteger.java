package com.dsalgo.interviewbit.strings;

public class RomanToInteger {
    public static void main(String[] args) {
        RomanToInteger rti = new RomanToInteger();
        System.out.println(rti.romanToInt("IX"));
    }

    public int romanToInt(String A) {
        if (A.isEmpty()) {
            return 0;
        }
        char ch;
        int output = 0;
        for (int i = 0; i < A.length(); i++) {
            ch = A.charAt(i);
            if (ch == 'I') {
                if (A.length() > (i + 1) && A.charAt(i + 1) == 'V') {
                    i++;
                    output += 4;
                } else {
                    if (A.length() > (i + 1) && A.charAt(i + 1) == 'X') {
                        i++;
                        output += 9;
                    } else {
                        output += 1;
                    }
                }
            } else if (ch == 'X') {
                if (A.length() > (i + 1) && A.charAt(i + 1) == 'L') {
                    i++;
                    output += 40;
                } else if (A.length() > (i + 1) && A.charAt(i + 1) == 'C') {
                    i++;
                    output += 90;
                } else {
                    output += 10;
                }
            } else if (ch == 'C') {
                if (A.length() > (i + 1) && A.charAt(i + 1) == 'D') {
                    i++;
                    output += 400;
                } else if (A.length() > (i + 1) && A.charAt(i + 1) == 'M') {
                    i++;
                    output += 900;
                } else {
                    output += 100;
                }
            } else if (ch == 'V') {
                output += 5;
            } else if (ch == 'L') {
                output += 50;
            } else if (ch == 'D') {
                output += 500;
            } else if (ch == 'M') {
                output += 1000;
            }
        }
        return output;
    }
}
