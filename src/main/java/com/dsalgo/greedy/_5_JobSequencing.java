package com.dsalgo.greedy;

import java.util.Arrays;

/*

A list of jobs which have {id, deadline, profit} are given
Deadline is the day number on or before which that job should be done.
On one day, only one job can be done.
Do the jobs such that the profit is maximum, and return the profit.

Algorithm -
Take a slots array which denotes on which day which job is done.
On day 0, job with deadline 1 can be done.

Job with deadline 6 can be done on slots[5] to slots[0]

To maximize the profit, we sort the input array on profit.
Take the job with the max profit and try to do it on the last day of the deadline.
If a job is already done on the last day of the deadline, we can still do it if any day before the deadline is free.

{id, deadline, profit}
{7,2,10},
{1,4,20},
{8,2,22},
{5,4,25},
{2,5,60},
{4,6,65},
{3,6,70},
{6,2,80}

Take job with {6,2,80} as it has max profit.
Deadline is 2, so try to do it on the last day which is slot[1]
And then mark slot[1] with the id of this job.

Then take the job {3,6,70} which has the next max profit
Deadline is 6, so try to do it on the last day which is slot[5]
And then mark slot[5] with the id of this job.

Then take the job {4,6,65} which has the next max profit
Deadline is 6, so try to do it on the last day which is slot[5]
But slot[5] was already taken previously. So we move left to find a vacant slot.
We have slot[4] empty, so we do this job on slot[4]
And then mark slot[4] with the id of this job.

And so on

 */
public class _5_JobSequencing {

    private static int solve(int[][] jobs) {

        // sort on profit
        Arrays.sort(jobs, (j1, j2) -> j2[2] - j1[2]);

        int maxDeadline = 0;
        for(int[] job : jobs) {
            maxDeadline = Math.max(maxDeadline, job[1]);
        }
        int[] slots = new int[maxDeadline];
        int profit = 0;
        for(int[] job : jobs) {
            int deadline = job[1];
            for(int i = deadline - 1; i >= 0; i--) {
                if(slots[i] == 0) {
                    slots[i] = job[0];
                    profit += job[2];
                    break;
                }
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        // jobs - {id, deadline, profit}
        int[][] jobs = {
                {7,2,10},
                {1,4,20},
                {8,2,22},
                {5,4,25},
                {2,5,60},
                {4,6,65},
                {3,6,70},
                {6,2,80}
        };
        System.out.println(solve(jobs));
    }
}
