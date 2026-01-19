package com.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _8Subset {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
    }
}

/**
 *
 * In the backtrack call - backtrack(list, tempList, nums, i + 1);
 * we used i+1, and did not use start+1
 *
 * When i+1 is used, the element at an index is used only once.
 *
 * If start+1 were used instead of i+1, it would have been similar to index+1 as used in _1PermutationsOfString.
 * Here, an element at an index is used at all the nodes in a level in the choice diagram tree.
 *
 *
 *
 */