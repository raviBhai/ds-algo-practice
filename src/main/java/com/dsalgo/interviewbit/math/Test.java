package com.dsalgo.interviewbit.math;

public class Test {
    public static void main(String[] args) {
        //int[] input = {2, 3, 5, 6, 7, 9}; // A
        int[] input = {1, 2, 3, 4, 5, 6}; // A
        int length = 4; // B
        int[] max = {4,1,3,2}; // C : max 230

        System.out.println("MAx possible permutations : " + getLocalPermutations(input, length, max, 0));
    }

    private static int getLocalPermutations(int[] input, int length, int[] max, int iteration) {
        int numbersLessThanLocalMax = 0;
        boolean maxMatched = false;
        int localMax = max[iteration];
        for (int inputNumber : input) {
            if (inputNumber < localMax) {
                numbersLessThanLocalMax++;
            } else if (inputNumber == localMax) {
                maxMatched = true;
            }
        }
        if (length == 1) {
            return numbersLessThanLocalMax + (maxMatched == true ? 1 : 0);
        } else {
            int localCount = numbersLessThanLocalMax;
            for (int i = 1; i < length; i++) {
                localCount = localCount * input.length;
            }
            if (maxMatched) {
                localCount = localCount + 1 * getLocalPermutations(input, length - 1, max, iteration + 1);
            }
            return localCount;
        }
    }
}
