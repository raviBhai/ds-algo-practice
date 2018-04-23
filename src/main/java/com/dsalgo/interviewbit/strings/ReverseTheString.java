package com.dsalgo.interviewbit.strings;

import java.util.Arrays;

public class ReverseTheString {
    public static void main(String[] args) {
        ReverseTheString rev = new ReverseTheString();
        //System.out.println(rev.reverseWords("the sky is blue moon"));
        rev.reverse("the sky is blue moon");
    }

    public String reverseWords(String a) {
        String output = "";
        String[] arr = a.split(" ");
        String temp;
        StringBuilder sb = new StringBuilder();

        for (int i = arr.length-1; i>= 0; i--) {
            temp = arr[i].trim();
            if (!temp.isEmpty()) {
                sb.append(temp).append(" ");
            }
        }
        output = sb.toString().trim();
        return output;
    }

    public void reverse(String a) {
        String[] arr = a.split(" ");
        for (int i = arr.length-1; i>=0; i--) {
            System.out.println(arr[i]);
        }
    }
}
