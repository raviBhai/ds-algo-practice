package com.dsalgo;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        String[] array = {"eat", "tea", "tan", "ate", "nat", "bat"};

        Arrays.sort(array);

        for (String s : array) {
            System.out.println(s);
        }
    }
}
