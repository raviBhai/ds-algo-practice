package com.dsalgo.arrays;

/*

Pascal triangle is -

                1
              1    1
            1    2    1
          1    3    3    1
        1   4     6    4    1
      1   5    10    10   5    1

To generate last row, we use second last row.

The first 10 in the last row seems to be in between 4 and 6 from the second last row
Do sum of 4 and 6 to get 10

The first 5 in the last row seems to be in between 1 and 4 from the second last row
Do sum of 1 and 4 to get 5


 */

public class _19_PascalTriangle {
}

/*
For given row and col number, return the number present at that location.
The above pascal triangle has 6 rows and 6 cols.
Row and col numbers start from 1.
Input - Row = 6, col = 3
output - 10

There is a formula to generate the number at a row and col.
Formula is  : (row-1) C (col-1)     -      C is for combination : nCr
(6-1) C (3-1) = 5 C 2

nCr formula is : n! / ( (n-r)! * r! )

Let's optimize the formula :
5 C 2   = 5! / ( (5-2)! * 2! )
        = 5! / (3! * 2!)
        = 5 * 4 * 3! / (3! * 2!)
        = 5 * 4 / 2!
        = 5 * 4 / (2 * 1)

To solve this, it seems we will run a for loop 2 times and within it we will try to get numerator and denominator,
In the first iteration, we will get 5 and 2 and try to divide them, which will result in decimal value.
Hence, to avoid decimal, we can reverse the order of the elements in the denominator.
We can do 5/1 in first iteration, then 4/2 in second iteration, and so on.

5 C 2   = 5 * 4 / (2 * 1)
        = 5 * 4 / (1 * 2)
        = (5/1) * (4/2)

Time complexity - runs a for loop r times, that is, col times - O(col)

 */
class Problem1 {
    protected static int solve(int row, int col) {
        int n = row - 1;
        int r = col - 1;
        return nCr(n, r);
    }

    private static int nCr(int n, int r) {
        int ans = 1;
        for (int i = 0; i < r; i++) {
            ans = ans * (n-i);
            ans = ans / (i+1);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(solve(6,3));
    }
}

/*
Given a row number, print that entire row

For a given row, the number of cols is same as the row number.
For row 6, there are 6 cols.

Approach 1 - brute force
If asked to generate all elements in row 6,
then use  Problem1.solve() and call it from 1 to 6 to generate all the values in the row.

Time complexity -
Outer for loop runs col times, inner loop also runs col times - O(col * col)


Approach 2 - optimized approach
Elements of last row are -

1   -   row:6, col:1    -   5 C 0   -   1
5   -   row:6, col:2    -   5 C 1   -   (5 / 1)
10  -   row:6, col:3    -   5 C 2   -   (5 / 1) * (4 / 2)
10  -   row:6, col:4    -   5 C 3   -   (5 / 1) * (4 / 2) * (3 / 3)
5   -   row:6, col:5    -   5 C 4   -   (5 / 1) * (4 / 2) * (3 / 3) * (2 / 4)
1   -   row:6, col:6    -   5 C 5   -   (5 / 1) * (4 / 2) * (3 / 3) * (2 / 4) * (1 / 5)

First element is always 1, so we can loop from 2nd element till last element
From second element, we see numerator as 5 and denominator as 1
Then for third element, we use answer from second and multiply it with (numerator - 1) / (denominator + 1)

Time complexity - one for loop over the cols - O(col)
 */
class Problem2 {

    private static void bruteForce(int row) {
        int col = row;
        for (int i = 1; i <= col; i++) {
            System.out.print(Problem1.solve(row, i) + "  ");
        }
    }

    protected static void optimized(int row) {
        int ans = 1;
        int col = row;
        int numerator = row-1;
        int denominator = 1;

        System.out.print(1 + "  ");
        for (int i = 2; i <= col; i++) {
            ans = ans * numerator / denominator;
            numerator--;
            denominator++;
            System.out.print(ans + "  ");
        }
    }


    public static void main(String[] args) {
        bruteForce(6);
        System.out.println();
        optimized(6);
    }
}

/*
Print the entire pascal triangle
Use Problem2 to solve this.
Print elements for every row using  Problem2.optimized()

Input will be only row number, as number of cols is same as number of rows
 */
class Problem3 {

    private static void solve(int row) {
        for (int i = 1; i <= row; i++) {
            Problem2.optimized(i);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        solve(6);
    }
}