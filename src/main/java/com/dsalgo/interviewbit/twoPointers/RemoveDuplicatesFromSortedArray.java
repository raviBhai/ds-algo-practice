package com.dsalgo.interviewbit.twoPointers;

import java.util.ArrayList;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray rd = new RemoveDuplicatesFromSortedArray();
        /*ArrayList<Integer> a = new ArrayList<Integer>() {{
            add(500);
            add(500);
            add(500);
        }};
        rd.removeDuplicates(a);
        System.out.println(a);*/

        //int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] nums = {1, 1, 2};
        //int[] nums = {1, 1};
        System.out.println(rd.removeDuplicates(nums));
    }

    public int removeDuplicates(ArrayList<Integer> a) {
        if (a.size() < 2) {
            return a.size();
        }
        int len = 0;

        int prev = a.get(a.size() - 1);
        for (int i = a.size()-2; i >=0; i--) {
            if (a.get(i) == prev) {
                a.remove(i);
            } else {
                prev = a.get(i);
            }
        }
        len = a.size();
        return len;


    }

    /**
     *
     *
     *  if (a.size() < 2) return a.size();

     // List#remove is proportional to the number of elements after the removed index
     // Thus, we move from the end for optimal run time
     int prev = a.get(a.size() - 1);
     for (int i = a.size() - 2; i >= 0; i--) {
     if (a.get(i) == prev) {
     a.remove(i);
     }
     else {
     prev = a.get(i);
     }
     }

     return a.size();
     *
     */

    //https://github.com/kdn251/interviews/blob/master/company/facebook/RemoveDuplicatesFromSortedArray.java

    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length < 2) {
            return nums.length;
        }

        int curr = nums.length - 2, prev = nums.length - 1;
        int count = 0;
        while (curr >= 0) {
            if (nums[curr] != nums[prev]) {
                curr--;
                prev--;
            } else {
                moveToEnd(nums, curr);
                count++;
                curr--;
                prev--;
            }
        }
        return nums.length - count;
    }

    void moveToEnd(int[]  nums, int i) {
        int tmp = nums[i], j = i + 1;
        for (; j < nums.length; j++) {
            nums[j - 1] = nums[j];
        }
        nums[j - 1] = tmp;
    }
}
