package com.dsalgo.arrays;

/*

Given input array with integers.
If the entire array represents a number, rearrange the numbers such that the rearranged array represents the next big number.
If the next big number is not possible, it means, return the smallest possible arrangement.

Input   -  2, 1, 5, 4, 3, 0, 0
Output  -  2, 3, 0, 0, 1, 4, 5

Input  - 5, 4, 3, 2, 1
Output - 1, 2, 3, 4, 5

Algorithm -
This is solved NextPermutation, however, NextPermutation is not using optimal solution.

Algo in NextPermutation is to start from second last and move to last element to find an element which is immediately bigger.
However, in NextPermutation, it was attempted for every element.
This can be avoided.

We can find this number by traversing from the second last element to the first element and check if the current element is less than the next element.
We traverse from 0 (second last 0), then 3, then 4, then 5, then 1
At 1, we know that the next element is bigger than this.
This is the index which will be swapped with a number which is immediately bigger but occurs after this index in the array.
Let's call this index as the breakpoint index.

To get the next bigger number, again we start from the last and move to first element.
As the elements from last to first will be in an increasing order (0, 0, 3, 4, 5) but only till the breakpoint, hence, the moment we find a bigger number we take it and swap.

We can get 3 as the next bigger number.

Swap 1,3
After swap, the input will become
 2, 3, 5, 4, 1, 0, 0

Now, the remaining numbers from breakpoint+1 to the end should be in increasing order.
We don't need to sort this section, because, even after swap, the numbers from breakpoint+1  to last will still be in descending order.
Hence, we can just reverse this section

 */

public class _12_NextPermutation {

    private static void solve(int[] array) {

        // find breakpoint
        int n = array.length;
        int breakpoint = -1;
        for(int i = n-2; i >=0; i--) {
            if (array[i] < array[i + 1]) {
                breakpoint = i;
                break;
            }
        }

        // swap with next bigger number
        for (int i = n - 1; i > breakpoint; i--) {  // breakpoint to i will always be in increasing order
            if (array[i] > array[breakpoint]) {
                swap(array, i, breakpoint);
                break;
            }
        }

        // reverse from breakpoint+1 to the end
        int end = n-1, start = breakpoint+1;
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void print(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
    }

    public static void main(String[] args) {
        int[] array = {2, 1, 5, 4, 3, 0, 0};
        solve(array);
        print(array);
    }
}
