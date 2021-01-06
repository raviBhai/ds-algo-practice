package com.dsalgo.interviewbit.strings;

public class IntegerToRoman {

    public static void main(String[] args) {
        System.out.println(intToRoman(10));
        System.out.println(intToRoman(6));
        System.out.println(intToRoman(1984));
    }

    public static String intToRoman(int num) {
        return helper(num, "");
    }

    private static String helper(int num, String s) {
        if (num == 0) {
            return s;
        } else if (num >= 1 && num <= 3) {
            return helper(num-1, s + "I");
        } else if (num == 4) {
            return helper(num-4, s + "IV");
        } else if (num >= 5  && num <= 8) {
            return helper(num-5, s + "V");
        } else if (num == 9) {
            return helper(num-9, s + "IX");
        } else if (num >= 10 && num <= 39) {
            return helper(num-10, s + "X");
        } else if (num >= 40 && num <= 49) {
            return helper(num-40, s + "XL");
        } else if (num >= 50 && num <= 89) {
            return helper(num-50, s + "L");
        } else if (num >= 90 && num <= 99) {
            return helper(num-90, s + "XC");
        } else if (num >= 100 && num <= 399) {
            return helper(num-100, s + "C");
        } else if (num >= 400 && num <= 499) {
            return helper(num-400, s + "CD");
        }  else if (num >= 500 && num <= 899) {
            return helper(num-500, s + "D");
        } else if (num >= 900 && num <= 999) {
            return helper(num-900, s + "CM");
        } else if (num >= 1000) {
            return helper(num-1000, s + "M");
        }

        return s;
    }
}
