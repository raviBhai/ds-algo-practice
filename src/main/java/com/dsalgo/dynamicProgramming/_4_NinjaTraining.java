package com.dsalgo.dynamicProgramming;

/*

Geek is going for a training program for n days.
He can perform any of these activities: Running, Fighting, and Learning Practice.
Each activity has some point on each day.
Geek can do only one training in one day.
As Geek wants to improve all his skills, he can't do the same activity on two consecutive days.
Given a 2D array arr[][] of size n where arr[i][0], arr[i][1], and arr[i][2]
represent the merit points for Running, Fighting, and Learning on the i-th day,
determine the maximum total merit points Geek can achieve .

Algorithm:

Let's say input is
10 50 1     - Day 0
5 100 11    - Day 1

If we go greedy, we will pick 50 on day0, and then we can't pick the same training on next day, hence we pick 11 on day1.
Output will be 50+11 = 61.
However, the output could have been 10+100 = 110

Hence, greedy will not work.
This means we need to find all the possible ways of picking the training and then return the one with max points.
As we need to find all possible ways, we do recursion.

Steps -
1. Define problem in index
2. Do all stuff for an index
3. Get best

As days can be from 0 to N-1, it can be our index.
So, we can say that solve(day) is a function which will give us the max points for input arg day.

As next step, we need to perform all the stuff we can do at this index.
At this index, we can perform all the trainings, but exclude the training which was performed the last day.
Hence, we need to know what training was performed on the last day.
Hence, we add one more arg called last to the solve function -> solve(day, last)
solve(day, last)
If there are 3 days, day will be from 0 to 2
If there are 3 tasks, they will be 0,1,2

For days :
0 - day_1
1 - day_2
2 - day_3

For tasks :
0 - task_1
1 - task_2
2 - task_3
3 - NO_TASK

Given this, solve(1,2) means that it gives the max points that player can earn on day_2 when task_3 was performed on day_3

We will solve recursion top down, that is, start from the last day, not the first day.
we start from last day, then we move to second last day, then 3rd last day and so on.

Last day - index : day = 2
As there is no day beyond last day, we can say that no task was performed beyond last day.
We can call solve with solve(2,3) which says that get max points for day_3 when NO_TASK was performed beyond day_3

Base case :
day_1 - day index will be 0
We will know what task was performed on day_2, we will ignore that and then take the task with max points on day_1

For current day :
We iterate through all the tasks, ignore the last task, then calculate the points with each task.
IF the player performs task_i, points earned with that task will be input[day][i] + solve(day-1, i)
We return the max points with all the tasks.


 */

public class _4_NinjaTraining {

    private static int solve(int day, int last, int[][] input) {
        if (day == 0) {
            int max = 0;
            for (int i = 0; i <= 2; i++) {
                if (i != last) {
                    max = Math.max(max, input[day][i]);
                }
            }
            return max;
        } else {
            int max = 0;
            for (int i = 0; i <= 2; i++) {
                if (i != last) {
                    int pointsOnThisDay = input[day][i] + solve(day - 1, i, input);
                    max = Math.max(max, pointsOnThisDay);
                }
            }
            return max;
        }
    }

    private static int memoization(int day, int last, int[][] input, int[][]dp) {
        if (day == 0) {
            int max = 0;
            for (int i = 0; i <= 2; i++) {
                if (i != last) {
                    max = Math.max(max, input[day][i]);
                }
            }
            return max;
        } else {
            if (dp[day][last] != -1) {
                return dp[day][last];
            }
            int max = 0;
            for (int i = 0; i <= 2; i++) {
                if (i != last) {
                    int pointsOnThisDay = input[day][i] + memoization(day - 1, i, input, dp);
                    max = Math.max(max, pointsOnThisDay);
                }
            }
            dp[day][last] = max;
            return dp[day][last];
        }
    }

    public static void main(String[] args) {
        int[][] input = {
                {1,2,5},    // day-0
                {3,1,1},    // day-1
                {3,3,3}     // day-2
        };

        int last_day_index = 2;   //0,1,2
        int last_task = 3;  // NO_TASK

        System.out.println(solve(last_day_index,last_task, input));  // expected output - 11


        // memoization
        int[][] dp = new int[last_day_index + 1][last_task + 1];
        for (int i = 0; i < last_day_index + 1; i++) {
            for (int j = 0; j < last_task + 1; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(memoization(last_day_index, last_task, input, dp));
    }
}
