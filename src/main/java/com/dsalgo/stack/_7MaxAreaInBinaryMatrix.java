package com.dsalgo.stack;

/**
 *
 * Given input matrix
 * {
 *     {0,1,1,0},
 *     {1,1,1,1},
 *     {1,1,1,1},
 *     {1,1,0,0}
 * }
 *
 * In the above matrix, the max area is 2*4 = 8, which is covered by 2nd and 3rd row.
 * As an output, we need to return this max area.
 *
 * We will reuse MaximumAreaInHistogram to solve this.
 *
 * In MaximumAreaInHistogram, 1D array is the input, but here we have 2D array as the input.
 * To solve this question using MaximumAreaInHistogram, we will convert the 2D array to 1D array and then reuse MaximumAreaInHistogram.
 *
 * We can iterate row wise.
 *
 * Take first row - {0,1,1,0}
 * Think of this as an histogram with heights as 0,1,1,0.
 * Call this as H1
 *
 * Then take second row - {1,1,1,1},
 * Add this to the first row, the result becomes - {1,2,2,1},
 * This becomes histogram H2.
 *
 * Then take third row - {1,1,1,1},
 * Add this to the previous result, the result would now become - {2,3,3,2},
 * This becomes histogram H3.
 *
 * Till H3, if we imagine that these are buildings with height 2,3,3,2, all of these have the same ground level.
 * Now, when we move to the fourth row - {1,1,0,0}, the ground level becomes this row.
 * Here, from the ground, first 2 entries {1,1} start, but the last 2 do not start from the ground.
 *
 * So, it we try to take fourth row and get an histogram H4, it would NOT be added directly to the previous result.
 * The result will NOT be - {3,4,3,2},
 * Rather, the result WILL be - {3,4,0,0},
 * This will become H4.
 *
 * We can call MaximumAreaInHistogram on H1 to H4 and get the max as the final result.
 *
 * H1 - {0,1,1,0}   - MaximumAreaInHistogram will be 2
 * H2 - {1,2,2,1}   - MaximumAreaInHistogram will be 4
 * H3 - {2,3,3,2}   - MaximumAreaInHistogram will be 8
 * H4 - {3,4,0,0}   - MaximumAreaInHistogram will be 6
 *
 * Out of these, the max is 8, which will be the final result
 *
 */
public class _7MaxAreaInBinaryMatrix {

    private static int solve(int[][] matrix) {
        int[] histogram = {0, 0, 0, 0};
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            int[] rowArray = matrix[i];

            for (int j = 0; j < rowArray.length; j++) {
                if (rowArray[j] == 0) {
                    histogram[j] = 0;
                } else {
                    histogram[j] += rowArray[j];
                }
            }
            int histogramArea = _6MaximumAreaInHistogram.solve(histogram);
            result = Math.max(result, histogramArea);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0,1,1,0},
                {1,1,1,1},
                {1,1,1,1},
                {1,1,0,0}
        };
        System.out.println(solve(matrix));
    }

}
