package com.dsalgo.interviewbit.twoPointers;

import java.util.ArrayList;
import java.util.List;

public class ClosestElementsin3SortedArrays {
    public static void main(String[] args) {
        ClosestElementsin3SortedArrays ce = new ClosestElementsin3SortedArrays();
        List<Integer> A = new ArrayList<Integer>() {{
            add(1);
            add(4);
            add(5);
            add(8);
            add(10);
        }};
        List<Integer> B = new ArrayList<Integer>() {{
            add(6);
            add(9);
            add(15);
        }};
        List<Integer> C = new ArrayList<Integer>() {{
            add(2);
            add(3);
            add(6);
            add(6);
        }};

        System.out.println(ce.minimize(A, B, C));
    }

    public int minimize(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        int out = 0;
        int res_i = 0, res_j = 0, res_k = 0;
        int i = 0, j = 0, k = 0;

        int min, max;
        int diff = Integer.MAX_VALUE;

        while (i < A.size() && j < B.size() && k < C.size()) {
            min = Math.min(A.get(i), Math.min(B.get(j), C.get(k)));
            max = Math.max(A.get(i), Math.max(B.get(j), C.get(k)));
            if ((max - min) < diff) {
                diff = max - min;
                res_i = i;
                res_j = j;
                res_k = k;
            }

            if (diff == 0) {
                break;
            }

            if (A.get(i).intValue() == min) {
                i++;
            } else if (B.get(j).intValue() == min) {
                j++;
            } else {
                k++;
            }
        }

        int ij = Math.abs(A.get(res_i) - B.get(res_j));
        int jk = Math.abs(B.get(res_j) - C.get(res_k));
        int ik = Math.abs(A.get(res_i) - C.get(res_k));
        out = Math.max(ij, Math.max(jk, ik));
        return out;
    }
}
