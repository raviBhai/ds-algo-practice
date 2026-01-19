package com.dsalgo.binarysearch;

/**
 * Given input array such that elements are in increasing order, then decreasing order, then again increasing and decreasing order.
 * Multiple peaks are created. Return any peak.
 * If all the elements are in increasing order, then  the last element is the peak
 * If all the elements are in decreasing order, then  the first element is the peak
 *
 * Solution -
 * Use binary search algorithm.
 * Initialize start and end, and get mid.
 * mid can be 0th index, last index or anywhere in the middle.
 *
 * For now, consider that mid is NOT 0th or last index.
 *
 * Check if arr[mid] is greater than both its neighbours, then the element at mid is peak, we can return it.
 * If not, check if mid is part of rise or slope.
 * If mid is part of rise, and mid is not the peak, it means, the peak will be after mid, so do start = mid+1
 * If mid is part of slope, and mid is not the peak, it means, the peak will be before mid, so do end = mid-1
 *
 * Handle elements at 0th and last index -
 * If mid is 0th element, check if it is greater than 1st element, if yes, mid is the peak
 * If mid is last element, check if it is greater than last-1 element, if yes, mid is the peak
 */
public class _15_FindPeakElement {
    
    private static int solve(int[] arr) {
        
        int start = 0, end = arr.length-1, mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (mid > 0 && mid < arr.length - 1) {
                if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                    return arr[mid];
                } else if (arr[mid] > arr[mid - 1]) {
                    start = mid + 1;
                } else if (arr[mid] < arr[mid - 1]) {
                    end = mid - 1;
                }
            } else if (mid == 0 && arr[mid] > arr[mid + 1]) {
                return arr[mid];
            } else if (mid == arr.length - 1 && arr[mid] > arr[mid - 1]) {
                return arr[mid];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 20, 15, 2, 23, 90, 67};
        System.out.println(solve(arr1));

        int[] arr2 = {1, 2, 3, 4, 5, 6};
        System.out.println(solve(arr2));

        int[] arr3 = {5, 4, 3, 2, 1};
        System.out.println(solve(arr3));
    }
}
