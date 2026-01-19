package com.dsalgo.dynamicProgramming.mcm;

/**
 * Take 3 matrices M1, M2, M3 with sizes
 * M1 = 2x3
 * M2 = 3x4
 * M3 = 4x2
 *
 * For multiplication, numOfColumnsFromM1 should be equal to numOfRowsFromM2
 * The resultant matrix has the size as numOfRowsFromM1 x numOfColumnsFromM2
 *
 * If M1 is of size p.q and M2 is of size q.r, then total number of multiplications done will be p.q.r
 *
 *
 * We have to multiply M1.M2.M3, such that the total number of multiplications are minimum.
 *
 * We can do the multiplication in 2 ways, and both ways have different number of multiplications.
 *
 * Way-1 : (M1.M2)M3
 * M1 x M2 = 2.3 x 3.4
 * Total multiplications = 2.3.4 = 24
 * Output matrix size = 2x4
 *
 * Then multiply this new matrix with M3
 * 2.4 x 4.2
 * Total multiplications = 2.4.2 = 16
 * Output matrix size = 2x2
 *
 * Hence, total multiplications = 24+16 = 40
 *
 *
 * Way-2 : M1(M2.M3)
 * M2 x M3 = 3.4 x 4.2
 * Total multiplications = 3.4.2 = 24
 * Output matrix size = 3x2
 *
 * Then multiply this new matrix with M1
 * 2.3 x 3.2
 * Total multiplications = 2.3.2 = 12
 * Output matrix size = 2x2
 *
 * Hence, total multiplications = 24+12 = 36
 *
 *
 * Hence, the total number of multiplications depend on the order in which matrices are multiplied.
 *
 * We need to find the minimum of (40, 36)
 */
public class _1McmMinimumCost {

    public static int solve(int[] arr, int i, int j) {
        if (i >= j) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j-1; k++) {
            int temp = solve(arr, i, k)
                    + solve(arr, k+1, j)
                    + arr[i-1] * arr[k] * arr[j];

            min = Math.min(min, temp);
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {40, 20, 30, 10, 30};
        //int[] arr = {10, 20, 30, 40, 50};
        System.out.println(solve(arr, 1, arr.length - 1));
    }
}

class MemoizationMcmMinimumCost {

    private static int[][] t;

    public static int solve(int[] arr, int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (t[i][j] != -1) {
            return t[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j-1; k++) {
            int temp = solve(arr, i, k)
                    + solve(arr, k+1, j)
                    + arr[i-1] * arr[k] * arr[j];

            min = Math.min(min, temp);
        }
        t[i][j] = min;
        return t[i][j];
    }

    public static void main(String[] args) {
        int[] arr = {40, 20, 30, 10, 30};
        //int[] arr = {10, 20, 30, 40, 50};
        t = new int[arr.length + 1][arr.length + 1];
        init(t);
        System.out.println(solve(arr, 1, arr.length - 1));
        System.out.println("");
    }

    private static void init(int[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0 ; j < t[0].length; j++) {
                t[i][j] = -1;
            }
        }
    }
}
