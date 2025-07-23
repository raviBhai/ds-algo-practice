package com.dsalgo.backtracking;

public class _2LargestNumberInKSwaps {

    public static void main(String[] args) {
        char[] input = {'7','2','1'};
        MyString result = new MyString(String.valueOf(input));
        solve(input, 2, 0, result);
        System.out.println(result.getStr());

        char[] input2 = {'5','4','7','7'};
        result = new MyString(String.valueOf(input));
        solve(input2, 2, 0, result);
        System.out.println(result.getStr());

        char[] input3 = {'1','2','3','4', '5', '6', '7'};
        result = new MyString(String.valueOf(input));
        solve(input3, 4, 0, result);
        System.out.println(result.getStr());
    }

    private static void solve(char[] input, int k, int index, MyString result) {
        if (k == 0 || index == input.length - 1) {
            return;
        }

        char max = maxCharFromIndex(input, index+1);
        for (int i = index + 1; i < input.length; i++) {
            if (input[index] < input[i] && input[i] == max) {
                swap(input, index, i);
                if (String.valueOf(input).compareTo(result.getStr()) > 0) {     // if input > result
                    result.setStr(String.valueOf(input));
                }
                solve(input, k - 1, index + 1, result);
                swap(input, index, i);
            }
        }
        solve(input, k, index + 1, result);
    }

    private static char maxCharFromIndex(char[] input, int index) {
        char max = input[index];
        for (int i = index + 1; i < input.length; i++) {
            if (input[i] > max) {
                max = input[i];
            }
        }
        return max;
    }

    private static void swap(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

}

class MyString {
    String str;

    public MyString(String str) {
        this.str = str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}