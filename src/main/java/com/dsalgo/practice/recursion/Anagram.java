package com.dsalgo.practice.recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Anagram {
    static int size;
    static char[] arr = null;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        size = word.length();
        arr = new char[word.length()];

        for (int i = 0; i < size; i++) {
            arr[i] = word.charAt(i);
        }

        doAnagram(size);
    }

    private static void doAnagram(int newSize) {
        if (newSize == 1) {
            return;
        }

        for (int i = 0; i < newSize; i++) {
            doAnagram(newSize - 1);

            if (newSize == 2)
            display();

            rotate(newSize);
        }
    }

    private static void rotate(int newSize) {
        int position = size - newSize;
        char temp = arr[position];
        int i;
        for (i = position + 1; i < size; i++) {
            arr[i - 1] = arr[i];
        }
        arr[i - 1] = temp;
    }

    private static void display() {
        System.out.print(++count + " ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
