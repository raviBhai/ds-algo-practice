package com.dsalgo.interviewbit.arrays;

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int index = -1;
        int pivot = findPivot(nums, 0, nums.length-1);
        if (pivot == -1) {
            index = binarySearch(nums, 0, nums.length-1, target);
        } else {
            index = binarySearch(nums, 0, pivot, target);
            if (index == -1) {
                index = binarySearch(nums, pivot+1, nums.length-1, target);
            }
            if (index == -1) {
                return -1;
            }
        }
        return index;
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        int mid = 0;
        while (true) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (start > end) {
                return -1;
            } else {
                if (target > nums[mid]) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            }
        }
    }

    private int findPivot(int[] nums, int start, int end) {
        if (end < start) {
            return -1;
        } else if (start == end) {
            return start;
        } else {
            int mid = (start + end) / 2;
            if (mid < end && nums[mid] > nums[mid+1]) {
                return mid;
            } else if (mid > start && nums[mid] < nums[mid-1]) {
                return mid-1;
            } else if (nums[mid] <= nums[start]) {
                return findPivot(nums, start, mid-1);
            } else {
                return findPivot(nums, mid+1, end);
            }
        }
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray in = new SearchInRotatedSortedArray();
        int[] nums = {1,3};
        int search = in.search(nums, 0);
        System.out.println(search);
    }
}
