package com.dsalgo.others;

import java.util.ArrayList;
import java.util.List;

public class TestHat {

    static List<String> list = new ArrayList<String>() {{
        add("");
    }};

    public static void main(String[] args) {
        permutations("hat");
        System.out.println(list);
    }

    public static void permutations(String input) {
        for (int i = 0; i < input.length(); i++) {
            permutations(input.charAt(i));
        }
    }

    private static void permutations(char ch) {
        List<String> bufferList = list;
        list = new ArrayList<>();
        for (String s : bufferList) {
            list.add(s + ch);

            if (s.length() > 0) {
                char lastChar = s.charAt(s.length() - 1);
                if (lastChar >= '1' && lastChar <= '9') {
                    lastChar = (char) (lastChar + '1' - '0');
                    list.add(s.substring(0, s.length() - 1) + lastChar);
                } else {
                    list.add(s + '1');
                }
            } else {
                list.add(s + '1');
            }
        }
    }
}
