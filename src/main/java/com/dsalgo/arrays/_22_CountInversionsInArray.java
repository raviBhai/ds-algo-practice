package com.dsalgo.arrays;

/*

Given an unsorted array
Count the number of inversions.

Inversion is when a number on left is greater than the number on right in the array.
These numbers need not be adjacent.

Example -
array - 5,3,2,4,1

inversions -
5,3
5,2
5,4
5,1
3,2
3,1
2,1
4,1


Algorithm :

Brute force -
For every element, check how many other elements are smaller, and keep the count.

Optimized approach -
Let's say there were two sorted arrays -

{ 2,3,5,6 }     and     { 2,2,4,4,8 }

Take 2 pointers i and j at the start of both the arrays.
Keep a global count as result
If array[i] > array[j], it means this is one inversion pair.
As the arrays are sorted, if element at i is greater than element at j, it means all the elements after i are greater than element at j
Then we do j++

If array[i] <= array[j], we will try to find a greater element in array_1, hence we do i++

Dry run -
{ 2,3,5,6 }     and     { 2,2,4,4,8 }
i = 0       array[i] = 2
j = 0       array[j] = 2
array[i] <= array[j]    do, i++
result = 0

i = 1       array[i] = 3
j = 0       array[j] = 2
array[i] > array[j]     do result = result + (num of elements from array[i] till end)
                           result = 0 + 3 = 3
                           j++

i = 1       array[i] = 3
j = 1       array[j] = 2
array[i] > array[j]     do result = result + (num of elements from array[i] till end)
                           result = 3 + 3 = 6
                           j++

i = 1       array[i] = 3
j = 2       array[j] = 4
array[i] <= array[j]    do, i++
result = 6

i = 2       array[i] = 5
j = 2       array[j] = 4
array[i] > array[j]     do result = result + (num of elements from array[i] till end)
                           result = 6 + 2 = 8
                           j++

i = 2       array[i] = 5
j = 3       array[j] = 4

and so on...

This is to show that if the two arrays are sorted, then we can get the inversion count.
Further observation - the two pointers i and j move the same way they move during the merge step in merge sort

However, we are given only one input array which is unsorted.

We can start merge sort on the input array.
During the merge step, we can add this calculation to have the count.

 */
public class _22_CountInversionsInArray {

    private static int result = 0;

    public static void sort(int[] data) {
        int[] workspace = new int[data.length];
        recMergeSort(workspace, 0, data.length - 1, data);
    }

    private static void recMergeSort(int[] workspace, int lower, int upper, int[] data) {
        if (lower == upper) {
            return;
        } else {
            int mid = (lower + upper) / 2;
            recMergeSort(workspace, lower, mid, data);
            recMergeSort(workspace, mid + 1, upper, data);
            merge(workspace, lower, mid + 1, upper, data);
        }
    }

    private static void merge(int[] workspace, int leftStart, int rightStart, int rightEnd, int[] data) {
        int leftEnd = rightStart - 1;
        int j = 0;
        int nElems = rightEnd - leftStart + 1;
        int lower = leftStart;


        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (data[leftStart] <= data[rightStart]) {
                workspace[j++] = data[leftStart++];
            } else {
                workspace[j++] = data[rightStart++];
                result += leftEnd - leftStart + 1;
            }
        }

        while (leftStart <= leftEnd) {
            workspace[j++] = data[leftStart++];
        }

        while (rightStart <= rightEnd) {
            workspace[j++] = data[rightStart++];
        }

        for (int i = 0; i < nElems; i++) {
            data[i + lower] = workspace[i];
        }
    }

    public static void main(String[] args) {
        int[] data = {5, 3, 2, 4, 1};
        sort(data);
        System.out.println(result);
    }
}

/*

The above solution uses global variable to maintain the result count.
However, we should try to not use global variable.
Below solution does not use global variable

 */
class WithoutGlobalVariable {
    public static int sort(int[] data) {
        int[] workspace = new int[data.length];
        return recMergeSort(workspace, 0, data.length - 1, data);
    }

    private static int recMergeSort(int[] workspace, int lower, int upper, int[] data) {
        int result = 0;
        if (lower == upper) {
            return 0;
        } else {
            int mid = (lower + upper) / 2;
            result += recMergeSort(workspace, lower, mid, data);
            result += recMergeSort(workspace, mid + 1, upper, data);
            result += merge(workspace, lower, mid + 1, upper, data);
        }
        return result;
    }

    private static int merge(int[] workspace, int leftStart, int rightStart, int rightEnd, int[] data) {
        int leftEnd = rightStart - 1;
        int j = 0;
        int nElems = rightEnd - leftStart + 1;
        int lower = leftStart;

        int result = 0;

        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (data[leftStart] <= data[rightStart]) {
                workspace[j++] = data[leftStart++];
            } else {
                workspace[j++] = data[rightStart++];
                result += leftEnd - leftStart + 1;
            }
        }

        while (leftStart <= leftEnd) {
            workspace[j++] = data[leftStart++];
        }

        while (rightStart <= rightEnd) {
            workspace[j++] = data[rightStart++];
        }

        for (int i = 0; i < nElems; i++) {
            data[i + lower] = workspace[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] data = {5, 3, 2, 4, 1};
        int result = sort(data);
        System.out.println(result);
    }
}
