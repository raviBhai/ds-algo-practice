package com.dsalgo.practice.stacksqueues;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReverseString {
    private String input;
    private String output;

    public ReverseString(String input) {
        this.input = input;
    }

    public String reverse() {
        int length = input.length();
        ArrayStack<Character> stack = new ArrayStack<Character>(length);

        for (int i = 0; i < length; i++) {
            stack.push(input.charAt(i));
        }

        output = "";
        while (!stack.isEmpty()) {
            output = output + stack.pop();
        }

        return output;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        ReverseString reverseString = new ReverseString(word);
        System.out.println(reverseString.reverse());
    }
}
