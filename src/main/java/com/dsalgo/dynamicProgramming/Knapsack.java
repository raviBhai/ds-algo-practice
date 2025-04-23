package com.dsalgo.dynamicProgramming;

public class Knapsack {
    private int numOfItems;
    private int capacityOfKnapsack;
    private int[][] knapsackTable;
    private int totalBenefit;
    private int[] weights;
    private int[] values;

    public Knapsack (int numOfItems, int capacityOfKnapsack, int[] weights, int[] values) {
        this.numOfItems = numOfItems;
        this.capacityOfKnapsack = capacityOfKnapsack;
        this.weights = weights;
        this.values = values;
        this.knapsackTable = new int[numOfItems + 1][capacityOfKnapsack + 1];
    }

    public void solve() {

        for (int i = 1; i <= numOfItems; i++) {
            for (int w = 1; w <= capacityOfKnapsack; w++) {
                int notTakingItem = knapsackTable[i - 1][w];
                int takingItem = 0;

                if (weights[i] <= w) {
                    takingItem = values[i] + knapsackTable[i - 1][w - weights[i]];
                }

                knapsackTable[i][w] = Math.max(notTakingItem, takingItem);
            }
        }
        totalBenefit = knapsackTable[numOfItems][capacityOfKnapsack];
    }

    public void showResult() {
        System.out.println("Total benefit: " + totalBenefit);
        for (int n = numOfItems, w = capacityOfKnapsack; n > 0; n--) {
            if (knapsackTable[n][w] != 0 && knapsackTable[n][w] != knapsackTable[n - 1][w]) {
                System.out.println("We take item #:" + n);
                w = w - weights[n];
            }
        }
    }

    public static void main(String[] args) {
        int numOfItems = 3;
        int capacityOfKnapsack = 5;

        // int[] weights = {4, 2, 3}
        // int[] values = {10, 4, 7}

        int[] weights = new int[numOfItems + 1];
        weights[1] = 4;
        weights[2] = 2;
        weights[3] = 3;

        int[] values = new int[numOfItems + 1];
        values[1] = 10;
        values[2] = 4;
        values[3] = 7;

        Knapsack knapsack = new Knapsack(numOfItems, capacityOfKnapsack, weights, values);
        knapsack.solve();
        knapsack.showResult();
    }


}

class RecursiveSolution {

    public int knapsack(int[] wt, int[] val, int w, int n) {

        if (w == 0 || n == 0) {
            return 0;
        }

        if (wt[n-1] <= w) {
            return Math.max(
                    val[n-1] + knapsack(wt, val, w - wt[n-1], n-1),
                    knapsack(wt, val, w, n-1)
            );
        } else {    // wt[n-1] > w
            return knapsack(wt, val, w, n-1);
        }
    }

}

class Memoization {
    int n, w;
    int [][] t;

    public Memoization(int n, int w) {
        this.n = n;
        this.w = w;
        this.t = new int[n+1][w+1];
        init(t);
    }

    public int knapsack(int[] wt, int[] val, int w, int n) {

        if (w == 0 || n == 0) {
            return 0;
        }

        if (t[n][w] != -1) {
            return t[n][w];
        }

        if (wt[n-1] <= w) {
            t[n][w] = Math.max(
                    val[n-1] + knapsack(wt, val, w - wt[n-1], n-1),
                    knapsack(wt, val, w, n-1)
            );
            return t[n][w];
        } else {    // wt[n-1] > w
            t[n][w] = knapsack(wt, val, w, n-1);
            return t[n][w];
        }
    }

    private void init(int[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0 ; j < t[0].length; j++) {
                t[i][j] = -1;
            }
        }
    }

}

class TopDown {

    int[] wt;
    int[] val;
    int w;
    int n;
    int[][] t;

    public TopDown(int[] wt, int[] val, int capacity) {
        this.wt = wt;
        this.val = val;
        this.n = wt.length;
        this.w = capacity;
        this.t = new int[n+1][w+1];
    }

    public int solve() {
        for (int j = 0; j < w+1; j++) {
            t[0][j] = 0;
        }

        for (int i = 0; i < n+1; i++) {
            t[i][0] = 0;
        }

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < w+1; j++) {
                if (wt[i-1] <= j) {
                    t[i][j] = Math.max(
                      val[i-1] + t[i-1][j-wt[i-1]],
                      t[i-1][j]
                    );
                } else {
                    t[i][j] = t[i-1][j];
                }
            }
        }
        return t[n][w];
    }
}

class Client {
    public static void main(String[] args) {
        int w = 5;
        int n = 3;
        int[] wt = {4, 2, 3};
        int[] val = {10, 4, 7};

        RecursiveSolution recursiveSolution = new RecursiveSolution();
        int recSol = recursiveSolution.knapsack(wt, val, w, n);
        System.out.println("Rec sol - " + recSol);

        Memoization memoization = new Memoization(n, w);
        int memSol = memoization.knapsack(wt, val, w, n);
        System.out.println("Mem sol - " + memSol);

        TopDown topDown = new TopDown(wt, val, w);
        int topDownSol = topDown.solve();
        System.out.println("Top down solution - " + topDownSol);
    }
}

/**
 * UnboundedKnapsack is exactly same as RodCutting problem.
 * There will be no change in the code.
 *
 * In RodCutting problem, input can be given in 2 ways -
 * 1. LengthOfRod, PriceArray (0th element is price of 1m, 1st element is price of 2m,... )
 * 2. LengthOfRod, PriceArray, LengthArray. But these 2 arrays will have same size.
 *
 * With input-2 above, if length of rod is 10, LengthArray need not necessarily have 1 to 10 elements.
 * It can have elements such as LengthArray{3, 5, 6, 8} and PriceArray{10, 20, 30, 40}
 * This means, price of 3m is 10, price of 5m is 20 and so on...
 *
 */
class UnboundedKnapsack {
    int[] wt;
    int[] val;
    int w;
    int n;
    int[][] t;

    public UnboundedKnapsack(int[] wt, int[] val, int capacity) {
        this.wt = wt;
        this.val = val;
        this.n = wt.length;
        this.w = capacity;
        this.t = new int[n+1][w+1];
    }

    public int solve() {
        for (int j = 0; j < w+1; j++) {
            t[0][j] = 0;
        }

        for (int i = 0; i < n+1; i++) {
            t[i][0] = 0;
        }

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < w+1; j++) {
                if (wt[i-1] <= j) {
                    t[i][j] = Math.max(
                            val[i-1] + t[i][j-wt[i-1]],
                            t[i-1][j]
                    );
                } else {
                    t[i][j] = t[i-1][j];
                }
            }
        }
        return t[n][w];
    }

    public static void main(String[] args) {
        int w = 5;
        int n = 3;
        int[] wt = {4, 2, 3};
        int[] val = {10, 4, 7};

        UnboundedKnapsack unboundedKnapsack = new UnboundedKnapsack(wt, val, w);
        int solution = unboundedKnapsack.solve();
        System.out.println("unbounded knapsack solution - " + solution);
    }
}