package com.dsalgo.interviewbit.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaveArray {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>();
        A.add(5);
        A.add(1);
        A.add(3);
        A.add(2);
        A.add(4);
        System.out.println(wave(A));
    }

    public static ArrayList<Integer> wave(ArrayList<Integer> A) {

        Integer[] arr = new Integer[A.size()];
        arr = A.toArray(arr);
        Arrays.sort(arr);


        ArrayList<Integer> res = new ArrayList<>();
        int rear;

        int i = 0;
        while (i < arr.length) {
            rear = arr.length - 1 - i;
            if (i < rear) {
                res.add(arr[rear]);
                res.add(arr[i]);
            } else if (i == rear) {
                res.add(arr[i]);
            } else {
                break;
            }
            i++;
        }
        return res;
    }
}
