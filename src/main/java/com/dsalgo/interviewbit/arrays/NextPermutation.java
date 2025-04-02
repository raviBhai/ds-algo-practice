package com.dsalgo.interviewbit.arrays;

import java.util.ArrayList;

public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        ArrayList<Integer> a = new ArrayList<>();
        /*a.add(11);
        a.add(66);
        a.add(77);
        a.add(88);
        a.add(33);*/
        a.add(1);
        a.add(2);
        a.add(5);
        a.add(4);
        a.add(3);


        System.out.println("Before Perm Opr");
        System.out.println(a);

        np.nextPermutation(a);
        System.out.println("After Perm opr");
        System.out.println(a);
    }

    public void nextPermutation(ArrayList<Integer> a) {

        if (a == null || a.isEmpty()) {
            return;
        }

        int length = a.size();

        boolean isPermutationPossible = false;
        for (int i = length-2; i >= 0; i--) {
            int maxAfterThis = getMaxAfterIndex(i, a);
            if (maxAfterThis != i) {
                isPermutationPossible = true;
                swap(i, maxAfterThis, a);
                sortInDescendeingOrderFromIndex(i+1, a);
                break;
            }
        }
        if (!isPermutationPossible) {
            //reverse the array
            sortInDescendeingOrderFromIndex(0, a);
        }
    }

    public int getMaxAfterIndex(int index, ArrayList<Integer> a) {
        int current = a.get(index);
        int max = current;
        int maxIndex = current;

        for (int i = index+1; i<a.size(); i++) {
            if (a.get(i) > current) {
                if (current == max) {
                    max = a.get(i);
                    maxIndex = i;
                } else {
                    if (a.get(i) < max) {
                        max = a.get(i);
                        maxIndex = i;
                    }
                }
            }
        }
        if (current == max) {
            return index;
        }
        return maxIndex;
    }

    //use this instead of above function
    public int getMaxAfterIndex_2(int index, ArrayList<Integer> a) {
        int current = a.get(index);
        int max = Integer.MIN_VALUE;
        int maxIndex = index;
        for (int i = index + 1; i < a.size(); i++) {
            if (max == Integer.MIN_VALUE && a.get(i) > current) {
                max = a.get(i);
                maxIndex = i;
            } else {
                if (a.get(i) < max && a.get(i) > current) {
                    max = a.get(i);
                    maxIndex = i;
                }
            }
        }
        if (max == Integer.MIN_VALUE) {
            max = current;
            maxIndex = index;
        }
        return maxIndex;
    }

    public int getMaxAfterIndex_3(int index, ArrayList<Integer> a) {
        int maxIndex = -1;
        for (int i = index + 1; i < a.size(); i++) {
            if (maxIndex == -1 && a.get(i) > a.get(index)) {
                maxIndex = i;
            } else {
                if (maxIndex != -1 &&
                        a.get(i) < a.get(maxIndex) &&
                        a.get(i) > a.get(index)) {
                    maxIndex = i;
                }
            }
        }
        if (maxIndex == -1) {
            maxIndex = index;
        }
        return maxIndex;
    }

    public void sortInDescendeingOrderFromIndex(int index, ArrayList<Integer> a) {
        int out, in, min;
        for (out = index; out < a.size(); out++) {
            min = out;
            for (in = out+1; in < a.size(); in++) {
                if (a.get(in) < a.get(min)) {
                    min = in;
                }
            }
            swap(min, out, a);
        }
    }

    public static void swap(int i, int j, ArrayList<Integer> a) {
        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }
}
