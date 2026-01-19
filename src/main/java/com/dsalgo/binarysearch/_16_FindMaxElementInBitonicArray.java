package com.dsalgo.binarysearch;

/**
 * Given input array which is bitonic.
 * Bitonic means that elements in the array are in increasing order, and then in decreasing order
 * such that it creates a peak. Only one peak can be present.
 *
 * Output - return the max element. Max element will be the peak element.
 * This is similar to _15_FindPeakElement
 *
 * Even if the input is strictly bitonic array with minimum 3 elements such that elements are in increasing and decreasing order,
 * we still need the entire code from _15_FindPeakElement,
 * We need the last 2 else-if blocks even in case of strictly bitonic array.
 *
 * Let's say the array size is more than 3 and the peak was at element 1.
 * The end pointer will start moving towards the left, and it is possible that it may reach the index 1.
 * In this case, start will be at 0 and end will be at 1.
 * At this time, we will need the last 2 else-if blocks
 *
 */
public class _16_FindMaxElementInBitonicArray {
}
