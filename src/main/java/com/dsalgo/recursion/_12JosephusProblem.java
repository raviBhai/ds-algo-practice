package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * let's say 1 to 40 people stand in circle.
 * 1st person will start counting, and every 7th person during the counting will be killed.
 * from 1, count reaches 7, so 7 is killed.
 * then from 8, counting starts again and 14th person is killed.
 * Return the last standing person. With input 40 and count 7, output will be 24
 *
 *
 * Use array to store 40 persons.
 *
 * Let's say there were 5 persons initially and every 3rd person will be killed.
 * 0 , 1 , 2 , 3 , 4
 * P1, P2, P3, P4, P5
 *
 * If starting index is 0, the next index will be index+k-1, which is 0+3-1=2
 * So, P3 will be removed, and persons after P3 will shift left in the array.
 * Array size is now reduced to 4
 *
 * Array is now modified to
 * 0 , 1 , 2 , 3
 * P1, P2, P4, P5
 *
 * From index 2, the 3rd next person will be at 0th index (2nd, 3rd, then 0th)
 * Next index will try to become as index + k-1 = 2 + 3 - 1 = 4
 * As the array has max index as 3, it cannot have index 4.
 * So, instead of index 4, it should start counting back from 0.
 * To do this, we can use modulo operator.
 * index = (index + k - 1 ) % array.length
 *
 *
 */
public class _12JosephusProblem {

    public static void solve(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int index = 0;
        solve(list, k, index);
        System.out.println(list);
    }

    private static void solve(List<Integer> list, int k, int index) {
        if (list.size() == 1) {
            return;
        }
        index = (index + k-1) % list.size();
        list.remove(index);
        solve(list, k, index);
    }

    public static void main(String[] args) {
        solve(40, 7);
    }
}
