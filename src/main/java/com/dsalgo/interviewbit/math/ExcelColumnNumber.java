package com.dsalgo.interviewbit.math;

public class ExcelColumnNumber {
    public static void main(String[] args) {
        ExcelColumnNumber ex = new ExcelColumnNumber();
        System.out.println(ex.titleToNumber("ABC"));
    }

    public int titleToNumber(String A) {
        if (A.isEmpty()) {
            return 0;
        }
        char ch;
        int rowNum = 0;
        for (int i = A.length() - 1; i >= 0; i--) {
            ch = A.charAt(i);
            rowNum = rowNum + valueOf(ch, A.length() - i - 1);
        }
        return rowNum;
    }

    private int valueOf(char ch, int count) {
        int index = indexOf(ch);
        int multiplier = 1;
        if (count != 0) {
            while (count != 0) {
                multiplier = multiplier * 26;
                count--;
            }
        }
        return index * multiplier;
    }

    private int indexOf(char ch) {
        return ch - 'A' + 1;
    }
}
