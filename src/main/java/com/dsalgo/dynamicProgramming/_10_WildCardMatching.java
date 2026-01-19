package com.dsalgo.dynamicProgramming;

/*

Given 2 Strings - s1 and s2
s1 can have wild card entries - ? or *
s2 will have only characters.
Return if s1 and s2 match.

? - one char matches
* - zero or more char matches


Examples -

s1 = ab?d
s2 = abcd
Returns - True, because  ? in s1 matches with 'c' in s2

s1 = ab*cd
s2 = abdefcd
Returns - True, because '*' matches with 'def' in s2

s1 = ab*cd
s2 = abcd
Returns - True, because '*' matches with zero chars in s2

s1 - ***
s2 - ""
Returns - True, because s1 has all '*' and s2 is empty, and '*' can match with zero chars as well

s1 - ab*d
s2 - abcc
Returns - False, because last char does not match


Solution -
Let's take this example -

s1 = ab*cd
s2 = abdefcd

We can have (i,j) starting from the end of these strings.
    compare - s1 = ab*cd,  s2 = abdefcd

Last char 'd' matches, so we shrink the strings and move i,j left
    compare - s1 = ab*c,  s2 = abdefc

Then, char 'c' matches, so we shrink the strings and move i,j left
    compare - s1 = ab*,  s2 = abdef

Now, as we have '*' at the end of s1, it can match with zero or more chars in s2
    CASE_1  :   compare - s1 = ab,  s2 = abdef  :   zero char
    CASE_2  :   compare - s1 = ab,  s2 = abde  :    1 char
    CASE_3  :   compare - s1 = ab,  s2 = abd  :     2 char
    CASE_4  :   compare - s1 = ab,  s2 = ab  :      3 char
    CASE_5  :   compare - s1 = ab,  s2 = a  :       4 char
    CASE_6  :   compare - s1 = ab,  s2 = ''  :      5 char

The moment we encounter an '*', we encounter all paths.
All paths, means, we use Recursion.

As we see that in case if '*' we get all the paths, we use recursion.
Here, we can generate all these recursive calls using the for loop to get all the comparisons listed above
Else, we can replace the for loop with recursion itself.
How?

Let's take s1 = ab*, s2 = abdef
compare(s1 = ab*, s2 = abdef)
We can make 2 choices -
    1. '*' means empty string
    2. '*' means one char and keep the '*' for next comparison
This will lead to 2 recursive calls -

compare(s1 = ab*, s2 = abdef)
    - compare(s1 = ab, s2 = abdef)  : '*' means empty string -> this generated CASE_1 above
    - compare(s1 = ab*, s2 = abde)  : '*' means one char and keep the '*' for next comparison
           - Again make 2 choices
                - compare(s1 = ab, s2 = abde)  : CASE_2
                - compare(s1 = ab*, s2 = abd)  :
                   - Again make 2 choices
                        - compare(s1 = ab, s2 = abd)  : CASE_3
                        - compare(s1 = ab*, s2 = ab)  :
                           - Again make 2 choices
                                - compare(s1 = ab, s2 = ab)  : CASE_4
                                - compare(s1 = ab*, s2 = a)  :
                                   - Again make 2 choices
                                        - compare(s1 = ab, s2 = a)  : CASE_5
                                        - compare(s1 = ab*, s2 = '')  :

This can be expressed in this recursive call -
    solve(i,j) {
        solve(i-1,j)    :   '*' means empty string
        solve(i,j-1)    :   '*' means one char and keep the '*' for next comparison
     }


Base cases :
We have s1 and s2 with indexes i,j
i,j can run below 0, thus exhausting their strings.
We can have 4 cases -

s1 - exhausted, s2 - exhausted
s1 - exhausted, s2 - not-exhausted
s1 - not-exhausted, s2 - exhausted
s1 - not-exhausted, s2 - not-exhausted

s1 - exhausted, s2 - exhausted
Both exhausted, return True

s1 - exhausted, s2 - not-exhausted
Nothing left to compare in s1 which could have had wildcards, hence return False

s1 - not-exhausted, s2 - exhausted
Nothing left in s2, but s1 has chars.
If all the chars in s1 are '*', even if s2 is empty return True
If all the chars in s1 are NOT '*', and if s2 is empty then return False

 */

public class _10_WildCardMatching {

    private static boolean solve(String s1, String s2, int i, int j) {

        // base conditions

        //s1 - exhausted, s2 - exhausted
        if (i < 0 && j < 0) {
            return true;
        }

        //s1 - exhausted, s2 - not-exhausted
        if (i < 0 && j >= 0) {
            return false;
        }

        // s1 - not-exhausted, s2 - exhausted, check if all chars in s1 are '*'
        if (i >= 0 && j < 0) {
            for (int ii = 0; ii <= i; ii++) {
                if (s1.charAt(ii) != '*') {
                    return false;
                }
            }
            return true;
        }

        if (s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?') {
            return solve(s1, s2, i - 1, j - 1);
        } else if (s1.charAt(i) == '*') {
            // generate all cases using recursion rather than for loop
            return solve(s1, s2, i - 1, j) || solve(s1, s2, i, j - 1);
        }

        return false;
    }


    private static boolean memoization(String s1, String s2, int i, int j, Boolean[][] dp) {

        // base conditions

        //s1 - exhausted, s2 - exhausted
        if (i < 0 && j < 0) {
            return true;
        }

        //s1 - exhausted, s2 - not-exhausted
        if (i < 0 && j >= 0) {
            return false;
        }

        // s1 - not-exhausted, s2 - exhausted, check if all chars in s1 are '*'
        if (i >= 0 && j < 0) {
            for (int ii = 0; ii <= i; ii++) {
                if (s1.charAt(ii) != '*') {
                    return false;
                }
            }
            return true;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?') {
            dp[i][j] = memoization(s1, s2, i - 1, j - 1, dp);
        } else if (s1.charAt(i) == '*') {
            // generate all cases using recursion rather than for loop
            dp[i][j] = memoization(s1, s2, i - 1, j, dp) || memoization(s1, s2, i, j - 1, dp);
        }

        // this condition is not present in recursive solution, but it is required here, because in recursive solution, it returns false if both if, else-if does not match
        // we need to memoize that "return false" here as well, and then return the state from the dp[][] array
        else {
            dp[i][j] = false;
        }

        return dp[i][j];
    }

    public static void main(String[] args) {
        String s1 = "ab*cd";
        String s2 = "abdefcd";
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        System.out.println(solve(s1, s2, i, j));    // true

        System.out.println(solve("ab?d", "abcd", 3, 3));    // true
        System.out.println(solve("ab?d", "abce", 3, 3));    // false

        // memoization
        Boolean[][] dp = new Boolean[i+1][j+1];
        System.out.println(memoization(s1, s2, i, j, dp));    // true

        dp = new Boolean[i+1][j+1];
        System.out.println(memoization("ab?d", "abcd", 3, 3, dp));    // true

        dp = new Boolean[i+1][j+1];
        System.out.println(memoization("ab?d", "abce", 3, 3, dp));    // false

    }
}
