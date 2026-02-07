package com.dsalgo.arrays;

/*
Given input array which has 0,1,2.
Sort it without using standard sorting.


Solution-1:
Count number of 0s, 1s, 2s
Then update the array as per the number of 0,1,2


Solution-2 :

Dutch national flag algorithm

Consider 3 levels - low, medium, high.
low, medium, high - represent indexes in the array.

The array has numbers from 0 to n-1


We need to have below 3 rules which defines what can be present at an index.
0 to low-1      :       should have 0's
low to med-1    :       should have 1's
high+1 to n-1   :       should have 2's

As we see, the range med to high is missing from above rules.
This range can have all the unsorted numbers.

The array should always comply with the above set of rules.

As initially the array is unsorted, hence med will be start index and high will be end index.
low can be start index as well.

We then move over the array indexed from mid to high. The element at the current index can be 0 or 1 or 2.
Based on what the element value is (0 / 1 / 2), we try to place the element in the appropriate range based on the rules.

Let's say we start traversing from med and the array[med] is 0 -

0       0       0       1       1       1       0       1       2       1       2       2
0               low-1   low             med-1   med                     high    high+1

in the above array, element at index med is 0
We can move this 0 to the index low (where we have 1), and then shift the 1's to the right.
Rather than shifting, we can just swap low,med


After swap -
0       0       0       0       1       1       1       1       2       1       2       2
0               low-1   low             med-1   med                     high    high+1

As per the rules, 0's should be till low-1, not till low, and 1's should be till med-1, not till med.
Hence, we increment both low and med

if array[med] == 0
swap(low,med)
low++
med++

After increment
0       0       0       0       1       1       1       1       2       1       2       2
0                       low-1   low             med-1   med             high    high+1

When array[med] == 1
As per the rules, 1's should be till med-1, not till med.
Hence, we increment med

After increment
0       0       0       0       1       1       1       1       2       1       2       2
0                       low-1   low                     med-1   med     high    high+1

if array[med] == 1
med++


When array[med] == 2
As per the rules, 2's should be from high+1, not high
So we swap med, high and then decrement high such that 2's start from high+1

After swap
0       0       0       0       1       1       1       1       1       2       2       2
0                       low-1   low                     med-1   med     high    high+1

After decrement
0       0       0       0       1       1       1       1       1       2       2       2
0                       low-1   low                     med-1   med
                                                                high    high+1

if array[med] == 2
swap(med,high)
high--


 */

public class _8_SortArrayWithZeroOneTwo {

    private static void solve(int[] array) {
        int count0 = 0, count1 = 0, count2 = 0;
        for (int i : array) {
            if (i == 0) count0++;
            else if (i == 1) count1++;
            else if (i==2) count2++;
        }

        for (int i = 0; i < count0; i++) {
            array[i] = 0;
        }

        for (int i = count0; i < count0 + count1; i++) {
            array[i] = 1;
        }

        for (int i = count0 + count1; i < array.length; i++) {
            array[i] = 2;
        }
    }

    private static void dutchNationalFlagAlgo(int[] array) {
        int low = 0, med = 0, high = array.length - 1;
        while (med <= high) {
            if (array[med] == 0) {
                swap(array, low, med);
                low++;
                med++;
            } else if (array[med] == 1) {
                med++;
            } else if (array[med] == 2) {
                swap(array, med, high);
                high--;
            }
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
        int[] array = {0, 1, 2, 1, 1, 0, 2, 0, 1};
        solve(array);
        print(array);

        System.out.println();

        int[] array1 = {0, 1, 2, 1, 1, 0, 2, 0, 1};
        dutchNationalFlagAlgo(array1);
        print(array1);
    }
}
