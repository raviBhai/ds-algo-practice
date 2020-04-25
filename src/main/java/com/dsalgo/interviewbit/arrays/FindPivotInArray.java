package com.dsalgo.interviewbit.arrays;

/*

    Rotate an ascending order sorted array at some pivot
    eg- 6,7,8,1,2,3,4,5 - pivot is 1

 */
public class FindPivotInArray {
    public static void main(String[] args) {
        int[] arr1 = {4, 5, 6, 1, 2, 3};    //3
        int[] arr2 = {5, 6, 7, 8, 9, 10, 1, 2, 3};  //6
        int[] arr3 = {6,7,8,1,2,3,4,5}; //3
        int[] arr4 = {4,5,6,7,8,1,2,3}; //5
        int[] arr5 = {1,2}; //0
        int[] arr6 = {2,1}; //1

        System.out.println(findPivot(arr1));
        System.out.println(findPivot(arr2));
        System.out.println(findPivot(arr3));
        System.out.println(findPivot(arr4));
        System.out.println(findPivot(arr5));
        System.out.println(findPivot(arr6));

        System.out.println("************");

        System.out.println(findPivot_GeeksForGeeks(arr1, 0, arr1.length-1));
        System.out.println(findPivot_GeeksForGeeks(arr2, 0, arr2.length-1));
        System.out.println(findPivot_GeeksForGeeks(arr3, 0, arr3.length-1));
        System.out.println(findPivot_GeeksForGeeks(arr4, 0, arr4.length-1));
        System.out.println(findPivot_GeeksForGeeks(arr5, 0, arr5.length-1));
        System.out.println(findPivot_GeeksForGeeks(arr6, 0, arr6.length-1));

    }

    private static int findPivot(int[] arr) {
        int pivot = -1;

        if (arr.length == 0) {
            System.out.println(pivot);
        } else if (arr.length == 1) {
            pivot = 1;
        } else if (arr.length == 2) {
            if (arr[0] > arr[1]) {
                pivot = 1;
            } else {
                pivot = 0;
            }
        } else {
            pivot = findPivotHelper(arr, 0, arr.length - 1);
        }

        return pivot;
    }

    private static int findPivotHelper(int[] arr, int start, int end) {

        int curr = (start + end) / 2;
        if (arr[curr] < arr[curr - 1]) {
            return curr;
        } else if (arr[curr] > arr[curr - 1] && arr[curr] > arr[curr + 1]) {
            return curr+1;
        } else if (arr[curr] > arr[start]) {
            return findPivotHelper(arr, curr + 1, end);
        } else if (arr[curr] < arr[start]) {
            return findPivotHelper(arr, start, curr - 1);
        }

        return -1;
    }

    private static int findPivot_GeeksForGeeks(int[] arr, int start, int end) {
        if (end < start) {
            return -1;
        } else if (end == start) {
            return end;
        } else {
            int mid = (start + end) / 2;
            if (mid < end && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (mid > start && arr[mid] < arr[mid - 1]) {
                return mid - 1;
            } else if (arr[mid] <= arr [start]){
                return findPivot_GeeksForGeeks(arr, start, mid - 1);
            } else {
                return findPivot_GeeksForGeeks(arr,mid + 1, end);
            }
        }
    }
}
