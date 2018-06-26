package com.dsalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        int[] arr = {25626, 25757, 24367, 24267, 16, 100, 2, 7277};
        System.out.println(Arrays.toString(delta_encode(arr)));
    }

    static int[] delta_encode(int[] array) {
        /*
         * Write your code here.
         */


        List<Integer> list = new ArrayList<>();
        list.add(array[0]);

        for (int i = 1; i < array.length; i++) {
            int diff = array[i] - array[i - 1];
            if (diff >= -127 && diff <= 127) {
                //fits in the range
            } else {
                //does not fit in the range
                list.add(-128);
            }
            list.add(diff);
        }

        int[] out = new int[list.size()];
        for (int j = 0; j < list.size(); j++) {
            out[j] = list.get(j);
        }

        return out;
    }
}
