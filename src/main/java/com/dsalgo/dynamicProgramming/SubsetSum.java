package com.dsalgo.dynamicProgramming;

import java.util.*;

public class SubsetSum {
    private int sum;
    private int[] S;
    private boolean[][] dpTable;

    public SubsetSum(int sum, int[] S) {
        this.sum = sum;
        this.S = S;
        this.dpTable = new boolean[S.length + 1][sum + 1];
    }

    // https://leetcode.com/problems/combination-sum/solutions/16502/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning/
    // check Combination Sum II (can't reuse same element) : https://leetcode.com/problems/combination-sum-ii/

    public static void getAllCombinations(int total, int[] array, List<Integer> temp, List<List<Integer>> result, int index) {
        if (total == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = index; i < array.length; i++) {
            if (array[i] <= total) {
                // we can put this condition jst like used in _1PermutationsOfString
                //however, as this array is sorted, we can avoid this set and use the if condition like in below comment
                //if(i > index && array[i] == array[i-1]) continue;
                if (!set.contains(array[i])) {
                    set.add(array[i]);
                    temp.add(array[i]);
                    getAllCombinations(total - array[i], array, temp, result, i + 1);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    public void solve() {

        //first row is false expect from element at 0,0

        //first column is true

        for (int i = 0; i <= S.length; i++) {
            dpTable[i][0] = true;
        }

        for (int rowIndex = 1; rowIndex <= S.length; rowIndex++) {
            for (int columnIndex = 1; columnIndex <= sum; columnIndex++) {

                if (S[rowIndex - 1] > columnIndex) {
                    dpTable[rowIndex][columnIndex] = dpTable[rowIndex - 1][columnIndex];
                } else {
                    if (dpTable[rowIndex - 1][columnIndex] == true) {
                        dpTable[rowIndex][columnIndex] = dpTable[rowIndex - 1][columnIndex];
                    } else {
                        dpTable[rowIndex][columnIndex] = dpTable[rowIndex - 1][columnIndex - S[rowIndex - 1]];
                    }
                }

            }
        }

        System.out.println("Solution: " + dpTable[S.length][sum]);
    }

    private void printdpTable() {
        for (int i = 0; i < dpTable.length; i++) {
            for (int j = 0; j < dpTable[0].length; j++) {
                if (dpTable[i][j]) {
                    System.out.print("T" + " - ");
                } else {
                    System.out.print("F" + " - ");
                }
            }
            System.out.println();
        }
    }

    public void showResult() {
        int rowIndex  = S.length;
        int colIndex = sum;

        while (rowIndex > 0 || colIndex > 0) {
            if (dpTable[rowIndex][colIndex] == dpTable[rowIndex - 1][colIndex]) {
                rowIndex = rowIndex - 1;
            } else {
                System.out.println("We take: " + S[rowIndex - 1]);
                colIndex = colIndex - S[rowIndex - 1];
                rowIndex = rowIndex - 1;
            }
        }
    }

    public static void main(String[] args) {
        //int[] S = {5, 2, 3, 1};
        //int sum = 9;

        int[] S = {2, 3, 5, 6, 8, 10};
        int sum = 10;


        SubsetSum subsetSum = new SubsetSum(sum, S);
        subsetSum.solve();
        subsetSum.showResult();
        subsetSum.printdpTable();

        int[] array = {2, 5, 2, 1, 2};
        int sum1 = 5;
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(array);
        getAllCombinations(sum1, array, temp, result, 0);
        System.out.println(result);


    }
}

class RecursionSubSetSum {

    public static boolean solve(int[] arr, int sum, int n) {
        if (sum == 0) {
            return true;
        }
        if (sum != 0 && n == 0) {
            return false;
        }

        if (arr[n - 1] > sum) {
            return solve(arr, sum, n-1);
        } else {
            return solve(arr, sum, n - 1) || solve(arr, sum - arr[n - 1], n - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 6, 8, 10};
        int sum = 10;
        System.out.println(solve(arr, sum, arr.length));
    }
}


/**
 * Given an array, divide the array in 2 partitions such that sum of elements
 * within each partition is same. All elements of the array must be divided in 2 partitions
 * Output - Is it possible to divide the array in equal partitions
 */
class EqualPartitionSubset {

    public static boolean equalPartition(int[] arr, int n) {
        int arrSum = 0;
        for (int i = 0; i < n; i++) {
            arrSum += arr[i];
        }
        if (arrSum % 2 != 0) {
            return false;
        }
        return subset(arr, arrSum/2, n);
    }

    public static boolean subset(int[] arr, int sum, int n) {
        boolean[][] t = new boolean[n+1][sum+1];

        for (int i = 0; i < n+1; i++) {
            t[i][0] = true;
        }


        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < sum + 1; j++) {

                if (arr[i-1] <= j) {
                    t[i][j] = t[i-1][j] || t[i-1][j-arr[i-1]];
                } else {
                    t[i][j] = t[i-1][j];
                }
            }
        }

        return t[n][sum];
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 6, 8, 10};
        int sum = 10;
        System.out.println(subset(arr, sum, arr.length));
    }

}

class NumberOfPartitions {
    public static int numPartitions(int[] arr, int sum, int n) {
        int[][] t = new int[n+1][sum+1];

        for (int i = 0; i < n+1; i++) {
            t[i][0] = 1;
        }


        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < sum + 1; j++) {

                if (arr[i-1] <= j) {
                    t[i][j] = t[i-1][j] + t[i-1][j-arr[i-1]];
                } else {
                    t[i][j] = t[i-1][j];
                }
            }
        }

        return t[n][sum];
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 6, 8, 10};
        int sum = 10;
        System.out.println(numPartitions(arr, sum, arr.length));
    }
}

/**
 * Partition a set into two subsets such that the difference of subset sums is minimum
 *
 * This is only when array has +ve integers.
 * This will not work when array has -ve integers.
 */
class MinimumSubsetSumDifference {

    public static int minimumSubsetSumDifference(int[] arr) {

        int range = 0;
        for (int i = 0; i < arr.length; i++) {
            range += arr[i];
        }

        boolean[] t = subset(arr, range, arr.length);

        List<Integer> potentialSum = new ArrayList<>();
        for (int i = 0; i <= t.length / 2; i++) {
            if (t[i]) {
                potentialSum.add(i);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < potentialSum.size(); i++) {
            min = Math.min(min, range - 2 * potentialSum.get(i));
        }

        return min;
    }

    public static boolean[] subset(int[] arr, int sum, int n) {
        boolean[][] t = new boolean[n+1][sum+1];

        for (int i = 0; i < n+1; i++) {
            t[i][0] = true;
        }


        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < sum + 1; j++) {

                if (arr[i-1] <= j) {
                    t[i][j] = t[i-1][j] || t[i-1][j-arr[i-1]];
                } else {
                    t[i][j] = t[i-1][j];
                }
            }
        }

        return t[n];
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 7, 4};
        System.out.println(minimumSubsetSumDifference(arr1));

        int[] arr2 = {3, 9, 7, 3};
        System.out.println(minimumSubsetSumDifference(arr2));

    }

}

/**
 * Given an array, divide it in 2 partitions such that
 * their difference is equal to the given input difference.
 * Find the total number of such ways to divide the array.
 *
 * only 2 subsets, with each element of the array has to be in either of the 2 subsets
 */
class NumberOfSubsetWithGivenDifference {

    public static int numberOfSubsetWithGivenDifference(int[] arr, int diff) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int s1 = (sum + diff) / 2 ;
        return NumberOfPartitions.numPartitions(arr, s1, arr.length);
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3};
        int diff = 1;
        System.out.println(numberOfSubsetWithGivenDifference(arr, diff));
    }

}

class TargetSum {
    /**
     *
     * Given an array and a target sum integer.
     * Every element of the array can be converted to a +ve or -ve integer.
     * Then sum the array after conversion.
     * The sum should be same as given target input.
     * Count number of ways in which such conversion can be done.
     *
     * Solution -
     * In any conversion, group +ve and -ve in 2 groups.
     * Take out minus sign as common for the -ve integers.
     * Sum numbers within each group to get S1 and S2.
     * Take S1 - S2.
     * Count number of ways in which S1 - S2 = GivenTargetInput.
     * This is same as above problem NumberOfSubsetWithGivenDifference
     *
     */
}