package com.dsalgo.ctci;

public class PairProgram {
    public static void main(String[] args) {
        Pair source = new Pair(2, 3);
        Pair target = new Pair(20, 29);
        System.out.println(isPoss(source, target));
    }

    public static boolean isPoss(Pair source, Pair target){
        if (source.l > target.l || source.r > target.r) {
            return false;
        }

        if (source.l == target.l && source.r == target.r) {
            return true;
        } else {
            Pair left = new Pair(source.l + source.r, source.r);
            Pair right = new Pair(source.l, source.l + source.r);
            return isPoss(left, target) || isPoss(right, target);
        }
    }
}
class Pair {
    int l;
    int r;

    public Pair(int l, int r) {
        this.l = l;
        this.r = r;
    }
}
