package com.dsalgo.ctci;

public class URLify {
    public static void main(String[] args) {
        String s = "Ravi Agrawal             ";
        int l = 12;
        System.out.println(URLify(s.toCharArray(), l));
    }

    public static String URLify(char[] input, int trueLength) {
        int spaces = 0;
        for (int i = 0; i < trueLength; i++) {
            if (input[i] == ' ') {
                spaces++;
            }
        }

        int index = trueLength + spaces * 2;
        for (int i = trueLength - 1; i >= 0; i--) {
            char ch = input[i];
            if (ch == ' ') {
                input[index - 1] = '0';
                input[index - 2] = '2';
                input[index - 3] = '%';
                index -= 3;
            } else {
                input[index - 1] = ch;
                index--;
            }
        }


        return new String(input);
    }
}
