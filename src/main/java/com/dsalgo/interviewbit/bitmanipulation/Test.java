package com.dsalgo.interviewbit.bitmanipulation;

public class Test {

    public static void main(String[] args) {
        char[] A = {'a', 'b', 'c'};
        allSubsets(A);
    }

    private static void allSubsets(char[] A) {
        int N = A.length;

        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    System.out.print(A[j] + " ");
                }
            }
            System.out.println();
        }
    }

}
