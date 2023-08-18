package com.dsalgo.interviewbit.math;

import java.util.*;

public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        System.out.println(ri.reverse(-1146467285));
    }

    public int reverse(int A) {
        int temp = A;
        int last;
        Long reverse = 0l;
        while (temp != 0) {
            last = temp % 10;
            reverse = reverse * 10 + last;
            if (reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE) {
                return 0;
            }
            temp = temp / 10;
        }
        return reverse.intValue();
    }

    private void test() {
        Map<String, Integer> map = new HashMap();
        TreeMap tmap = new TreeMap();

        Iterator<Map.Entry<String, Integer>> iterator1 = map.entrySet().iterator();

        Deque<Integer> deque = new LinkedList<>();
        Iterator<Integer> iterator = deque.iterator();


    }
}
