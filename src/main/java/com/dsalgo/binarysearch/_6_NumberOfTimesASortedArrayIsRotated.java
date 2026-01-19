package com.dsalgo.binarysearch;

/**
 *
 * given input array which is rotated 3 times - 6,7,8,1,2,3,4,5
 *
 * Return 3 as it is rotated 3 times.
 *
 * Here, the pivot element is 1.
 * The index of pivot element is 3.
 *
 * We can find the index of pivot element and return it.
 *
 *
 * Solution -
 * 1. Get mid element.
 * 2. Check if mid is less than prev and next.
 * 3. If yes, return mid
 * 4. If no, pivot is either in left or right part.
 * 5. So, either of left or right will be unsorted.
 * 6. Compare start/end with mid to figure out which part is not sorted, and then update first and last accordingly.
 * 7. Edge case - Input array - [4,5,6,7,0,1,2]
 * 8. Mid - 7, and both left and right part are sorted.
 * 9. When both the parts are sorted, add the condition - if arr[start] <= arr[end], then return arr[start]
 *
 */
public class _6_NumberOfTimesASortedArrayIsRotated {

    public static int solve(int[] arr) {
        int start = 0, end = arr.length-1, mid = 0, prev = 0, next = 0;
        int n = arr.length;

        while(start <= end) {

            if (arr[start] <= arr[end]) {
                return start;
            }

            mid = start + (end - start) / 2;
            next = (mid + 1) % n;
            prev = (mid - 1 + n) % n;

            if (arr[mid] <= arr[prev] && arr[mid] <= arr[next]) {
                return mid;
            } else if (arr[start] <= arr[mid]) {
                start = mid + 1;
            } else if (arr[mid] <= arr[end]) {
                end = mid - 1;
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2}; //4
        System.out.println(solve(arr));

        int[] arr1 = {4, 5, 6, 1, 2, 3};    //3
        System.out.println(solve(arr1));


        int[] arr2 = {5, 6, 7, 8, 9, 10, 1, 2, 3};  //6
        System.out.println(solve(arr2));

        int[] arr3 = {6,7,8,1,2,3,4,5}; //3
        System.out.println(solve(arr3));

        int[] arr4 = {4,5,6,7,8,1,2,3}; //5
        System.out.println(solve(arr4));

        int[] arr5 = {1,2}; //0
        System.out.println(solve(arr5));

        int[] arr6 = {2,1}; //1
        System.out.println(solve(arr6));

    }
}
