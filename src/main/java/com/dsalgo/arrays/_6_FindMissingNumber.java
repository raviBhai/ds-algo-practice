package com.dsalgo.arrays;

/*

Given an array of size N-1 and an integer N.
The array will have the numbers from 1 to N except one number.
Find the missing number.

Input - 1,2,3,5
N - 5
Missing number - 4

Algorithm -
If we use set, we will use extra space.
We can avoid using it.

Solution-1 :
for input N, get the sum from 1 to N using N*(N+1)/2
Then sum all the numbers in the array.
Take the difference of these two sums, this is the missing number.

Splution-2 :
XOR - when same numbers are XOR'ed, it gives 0. When a number and 0 is XOR'ed, it gives the number.
12^12 = 0
12^0 = 12

Using the input variable N, we can XOR all numbers from 1 to N.
Then, we can XOR all the numbers in the array.

Input - 1,2,3,5
N - 5

XOR1 - 1^2^3^4^5
XOR2 -  1^2^3^5

XOR1^XOR2 =  1^2^3^4^5 ^ 1^2^3^5
          = (1^1) ^ (2^2) ^ (3^3) ^ (4) ^ (5^5)
          = 0     ^  0    ^  0    ^  4  ^  0
          = 4

To get the two XOR's, we can have 2 for loops.
We can try to optimize it by having 1 for loop.
We can use the for loop for the array to get the one extra calculation for XOR1

 */

public class _6_FindMissingNumber {

    private static int solve(int[] array, int N) {
        int xor1 = 0;
        int xor2 = 0;
        for (int i = 0; i < array.length; i++) {
            xor2 ^= array[i];
        }
        for (int i = 1; i <= N; i++) {
            xor1 ^= i;
        }

        int missing = xor1 ^ xor2;

        return missing;
    }

    private static int optimized(int[] array, int N) {
        int xor1 = 0;
        int xor2 = 0;
        for (int i = 0; i < array.length; i++) {
            xor2 ^= array[i];

            //extra calcultation
            xor1 ^= i+1;    // xor from 1 to 4, need to xor 5 as well which we will do outside the loop
        }

        // xor 5
        xor1 ^= N;

        int missing = xor1 ^ xor2;

        return missing;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 5};
        int N = 5;
        System.out.println(solve(array, 5));
        System.out.println(optimized(array, 5));
    }
}

/*
Given array has duplicates and only one number is unique.
The duplicate count for a number is even.
Find the unique number.

Input - 1,1,2,2,3,3,4,5,5
Output - 4

Algorithm -
Do XOR of all numbers.
Duplicate will become 0 and then 0 XOR with unique will be the number itself

 */
class FindNonDuplicateElement {

}