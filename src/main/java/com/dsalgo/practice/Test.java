package com.dsalgo.practice;

public class Test {
    public static void main(String[] args) {
        char a = 'a';
        System.out.println(a);

        int offset = (int) a;
        System.out.println(offset);

        char d = 'd';
        System.out.println(d-a);
        System.out.println(d-offset);

        char zero = '9';
        System.out.println(zero);

        int zeroOffset = (int)zero;
        System.out.println(zeroOffset);

        String s1 = new String("ravi");
        String s2 = new String("ravi");
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());


        String t = "abc" + '1';
        System.out.println(t);

        char ch = '6';
        ch = (char) (ch + '1' - '0');
        System.out.println(ch);

        char dot = '.';
        System.out.println(dot);
        System.out.println((int)dot);

        int doti = 46;
        System.out.println((char)doti);
    }
}
