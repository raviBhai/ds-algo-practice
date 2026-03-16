package com.dsalgo.greedy;

/*
Given 2 arrays.
First array represents cookies and second represents children.

The first array stores the sizes of cookies, and the second one stores the greed of the children.

Example -
sizeOfCookies - { 4 2 1 2 1 3 }
greedOfChildren - { 1 5 3 3 4 }

A parent has to give cookies to their children such that they satisfy the greed of maximum number of children.
Child at 0th index has a greed of 1, it means that this child will be satisfied if a cookie of size >=1 is given to it.

Child at 1st index has a greed of 5, it means that this child will be satisfied if a cookie of size >=5 is given to it.
As there is no cookie of size >=5, it means, this child cannot be satisfied.


Algorithm -
1. Sort both the arrays and take 2 pointers at the start of the arrays.
2. If the pointer on sizeOfCookies is >= that on greedOfChildren, then it means that child can be satisfied with that cookie.
We can move both the pointers to the next location.
3. Else, we move the cookies pointer to the next location to try and find out a bigger size cookie to satisfy the child
4. Return the number of children who are satisfied.

 */

import java.util.Arrays;

public class _1_AssignCookies {

    private static int solve(int[] cookiesSize, int[] greedOfChildren) {
        Arrays.sort(cookiesSize);
        Arrays.sort(greedOfChildren);
        int cookiePointer = 0, childrenPointer = 0;

        while (cookiePointer < cookiesSize.length && childrenPointer < greedOfChildren.length) {
            if (cookiesSize[cookiePointer] >= greedOfChildren[childrenPointer]) {
                System.out.println("Give cookie of size " + cookiesSize[cookiePointer] + " to child with greed " + greedOfChildren[childrenPointer]);
                cookiePointer++;
                childrenPointer++;
            } else {
                cookiePointer++;
            }
        }

        // if childrenPointer is at 3 after the while loop ends, it means the children at indexes 0,1,2 are given the cookies.
        // which means 0,1,2 which is 3 children are satisfied when childrenPointer is at 3
        return childrenPointer;
    }

    public static void main(String[] args) {
        int[] sizeOfCookies = {4, 2, 1, 2, 1, 3};
        int[] greedOfChildren = {1, 5, 3, 3, 4};
        System.out.println(solve(sizeOfCookies, greedOfChildren));
    }
}
