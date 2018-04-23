package com.dsalgo.interviewbit.math;

import java.util.ArrayList;

public class NumbersOfLengthNAndValueLessThanK {
    public static void main(String[] args) {
        NumbersOfLengthNAndValueLessThanK obj = new NumbersOfLengthNAndValueLessThanK();
        ArrayList<Integer> A = new ArrayList<Integer>(){{
            add(2);
            add(3);
            add(5);
            add(6);
            add(7);
            add(9);
        }};

        /*ArrayList<Integer> A = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
        }};*/

        /*ArrayList<Integer> A = new ArrayList<Integer>(){{
            add(0);
            add(1);
            add(2);
            add(5);
        }};*/

        /*ArrayList<Integer> A = new ArrayList<Integer>(){{
            add(2);
            add(9);
        }};*/

        /*ArrayList<Integer> A = new ArrayList<Integer>(){{
                add(0);
                add(1);
                add(2);
                add(5);
            }};*/

        /*ArrayList<Integer> A = new ArrayList<Integer>(){{
            add(0);
            add(1);
            add(5);
        }};*/

        System.out.println(obj.solve(A, 5, 42950));

    }

    public int solve(ArrayList<Integer> A, int B, int C) {
        int tempB = B - 1;
        int powOf10;
        int digitC;
        int result = 0;
        int length = A.size();
        int smallerNumbers = 0;
        int powOfLen;
        boolean isFirstNumber = true;
        boolean isOnlyNumber = false;
        int lengthOfC = (int) (Math.log10(C) + 1);

        if (B > lengthOfC) {
            return 0;
        }

        if (B < lengthOfC) {
            int res = (int) Math.pow(length, B);
            return res;
        }

        //else if B == lengthOfC

        if (lengthOfC == 1) {
            isOnlyNumber = true;
        }

        for (int i = tempB; i >= 0; i--) {
            powOf10 = (int) Math.pow(10, i);
            digitC = C / powOf10;
            NumLenUtil nl = totalNumbersLessThan(A, digitC, isFirstNumber, isOnlyNumber);
            if (isFirstNumber) {
                if (nl.smallCount == 0) {
                    return 0;
                }
            }

            powOfLen = (int) Math.pow(length, i);

            result += nl.smallCount * powOfLen;

            if (nl.smallCount == length) {
                break;
            }
            if (!nl.isPresent) {
                break;
            }

            C = C % powOf10;
            isFirstNumber = false;
        }

        return result;
    }

    private NumLenUtil totalNumbersLessThan(ArrayList<Integer> A, int number, boolean isFirstNumber, boolean isOnlyNumber) {
        NumLenUtil nl = new NumLenUtil();
        int count = 0;
        boolean isPresent = false;
        for (Integer i : A) {
            if (i == number) {
                isPresent = true;
            }
            if (!isOnlyNumber && isFirstNumber && i == 0) {
                continue;
            }
            if (i < number) {
                count++;
            }
        }
        nl.smallCount = count;
        nl.isPresent = isPresent;
        return nl;
    }
}

/**
 * A : [ 2, 3, 5, 6, 7, 9 ]
 B : 5
 C : 42950
 */
class NumLenUtil {
    int smallCount;
    boolean isPresent;
}
