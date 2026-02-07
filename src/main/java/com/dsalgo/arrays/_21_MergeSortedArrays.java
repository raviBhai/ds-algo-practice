package com.dsalgo.arrays;

/*

Given 2 sorted arrays.
Merge them so that result arrays is in sorted order.
 */

import java.util.Arrays;

public class _21_MergeSortedArrays {
}


class WithExtraSpace {
    /*

        input arrays - array1 of size n, array2 of size m
        output array - array3 of size n+m

        have 2 pointers over - one over array1 as i, and another over array2 as j

        if i < j, put in array3[k] and do i++ and k++
        if i >= j, put in array3[k] and do j++ and k++

        Once array3 is populated completely, now we copy elements from array3 to array1 and array2

        first copy from array3 to array1 till array1 is exhausted.
        then copy from array3 to array2

     */
}


/*
Do not use extra space

Approach-1 :
If one of the array itself has an extra space :
https://github.com/kdn251/interviews/blob/master/company/facebook/MergeSortedArray.java
Start traversing from the end


Approach-2 :
If none of the arrays has any extra space :
array1 - 1,3,5,7
array2 - 0,2,6,8,9

iterate array1 from end and array2 from start
bigger elements should go to array2
let i and j be the iterators on array1 and array2 respectively.

if array1[i] > array2[j] {
     swap(array1[i], array2[j])
     i--
     j++
}
else if array1[i] <= array2[j] {
    break
}

Dry run :
array1 - 1,3,5,7
array2 - 0,2,6,8,9
array1[i] = 7, array2[j] = 0

7 > 0 -> swap ->
array1 - 1,3,5,0
array2 - 7,2,6,8,9
i--, j++

array1[i] = 5, array2[j] = 2
5 > 2 -> swap ->
array1 - 1,3,2,0
array2 - 7,5,6,8,9
i--, j++

array1[i] = 3, array2[j] = 6
3 <= 6 -> break
why break? - if 3 is less than 6, it means anything left of 3 is also less than 6

Now, we need to sort array1 and array2 separately.


 */
class WithoutExtraSpace {

    private static void solve(int[] array1, int[] array2) {
        int i = array1.length - 1;
        int j = 0;
        while (i >= 0 && j <= array2.length-1) {
            if (array1[i] > array2[j]) {
                int temp = array1[i];
                array1[i] = array2[j];
                array2[j] = temp;
                i--;
                j++;
            } else {
                break;
            }
        }
        Arrays.sort(array1);
        Arrays.sort(array2);
    }

    private static void print(int[] array) {
        for (int j : array) {
            System.out.print(j + " ");
        }
    }

    public static void main(String[] args) {
        int[] array1 = {1, 3, 5, 7};
        int[] array2 = {0, 2, 6, 8, 9};
        solve(array1, array2);
        print(array1);
        System.out.println();
        print(array2);
    }

}