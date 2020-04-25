package com.dsalgo.interviewbit.strings;

public class ZigzagConversion {
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int count = 0, start = 0, run = 0;
        String out = "", result = "";
        int parentCount = 2*numRows - 2;
        boolean isCorner = false;
        for (int i = numRows; i >= 1; i--) {
            count = 2*i - 2;
            if (count == 0) {
                count = 2*numRows - 2;
            }
            if (i == numRows || i == 1) {
                isCorner = true;
            }
            run = start;
            out = "";
            boolean flag = true;
            while(run <= s.length() - 1) {
                out = out + s.charAt(run);
                if (!isCorner) {
                    if (flag) {
                        run += count;
                        flag = false;
                    } else {
                        run = run + (parentCount - count);
                        flag = true;
                    }
                } else {
                    run += count;
                }

            }
            result += out;
            start++;
            isCorner = false;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 4));
    }
}
