package com.dsalgo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class StoneGame2 {


    private Map<String, Integer> map = new HashMap<>();
    public void stoneGameII(int[] piles) {

        //System.out.println(helper(piles, 1, 2, -1));


        System.out.println("***************** Below with memoization *****************");

        System.out.println(helperDp(piles, 1, 2, -1));



    }



    private int helper(int[] piles, int start, int end, int offset) {

        if (offset >= piles.length ) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            int sum=0;
            for (int j = offset + 1; j <= i + offset; j++) {
                if (j < piles.length) {
                    sum += piles[j];
                }

            }

            int aliceHasTaken = i;
            int bobCanTake = Math.max(aliceHasTaken*2, end);

            int bobStart = offset + aliceHasTaken + 1;
            int bobEnd = offset + aliceHasTaken + bobCanTake;

            int min = Integer.MAX_VALUE;
            for (int k = bobStart; k <= bobEnd; k++) {
                int numPiecesBobHasTaken = k - bobStart + 1;
                int aliceCanTake = Math.max(bobCanTake, numPiecesBobHasTaken * 2);
                min = Math.min(min, helper(piles, 1, aliceCanTake, k));
            }

            max = Math.max(max, sum + min);
        }
        return max;
    }


    private int helperDp(int[] piles, int start, int end, int offset) {

        String key = start + "," + end + "," + offset;
        if (map.get(key) == null) {
            if (offset >= piles.length ) {
                return 0;
            }

            int max = Integer.MIN_VALUE;
            for (int i = start; i <= end; i++) {
                int sum=0;
                for (int j = offset + 1; j <= i + offset; j++) {
                    if (j < piles.length) {
                        sum += piles[j];
                    }

                }

                int aliceHasTaken = i;
                int bobCanTake = Math.max(aliceHasTaken*2, end);

                int bobStart = offset + aliceHasTaken + 1;
                int bobEnd = offset + aliceHasTaken + bobCanTake;

                int min = Integer.MAX_VALUE;
                for (int k = bobStart; k <= bobEnd; k++) {
                    int numPiecesBobHasTaken = k - bobStart + 1;
                    int aliceCanTake = Math.max(bobCanTake, numPiecesBobHasTaken * 2);
                    min = Math.min(min, helperDp(piles, 1, aliceCanTake, k));
                }

                max = Math.max(max, sum + min);
            }
            map.put(key, max);
        }
        return map.get(key);

    }

    public static void main(String[] args) {
        StoneGame2 sg2 = new StoneGame2();
        //int[] piles = {2,7,9,4,4};
        //int[] piles = {8270,7145,575,5156,5126,2905,8793,7817,5532,5726,7071,7730,5200,5369,5763,7148,8287,9449,7567,4850,1385,2135,1737,9511,8065,7063,8023,7729,7084,8407};
        //int[] piles = {1,2,3,4,5,100};
        int[] piles = {806,6146,3503,3024,226,682,3500,4560,3346,5981,9508,2658,4984,9713,8485,1646,5321,4418,1940,7445,3769,7310,4776,9134,6821,6334,9889,312,8722,5894,9118,1911,8106,3068,6070,1117,5172,2852,2637,7477,8197,5091,3898,5198,707,8886,8312,4673,4884,8665,9476,433,1816,3462,756,1934,1506,314,6032,710,695,1958,6015,8588,7402,6375,390,5033,7510,6761,5549,5364,4206,7365,6484,6783,3151,9141,8986,9309,476,5697,7932,2165,655,7598,79};
        sg2.stoneGameII(piles);
    }
}
