package com.dsalgo.greedy;

import java.util.Arrays;


/*

Given an input array which has jobs that has to be scheduled and run by a machine.
Each entry in the array represents the time taken to run that job.
The machine takes the job which has the least amount of time to execute.
On completing current job, it then moves to the next job.

The other jobs wait till previous jobs complete.
Return the average waiting time, which is totalWaitTime / numberOfJobs

Algorithm -
1. As we want to take the job with the least execution time, sort the input array.
2. Then we process the jobs by iterating over the array.
3. We will maintain 2 variables - currentTime and waitTime
3. Before we actually process the job in the for loop, we know that the job that is about to be processed has waited till the currentTime.
4. Hence, total waitTime increases by currentTime.
5. Then, once the job is processed, the currentTime will increase by the amount of time taken the job to process.

Example -
input - {4, 3, 7, 1, 2}
sort it - {1, 2, 3, 4, 7}

currentTime - 0
waitTime - 0

take job_0 - 1
time it waited was till the currentTime - 0
waitTime = waitTime + currentTime = 0 + 0 = 0
now the job will process and will take time as 1 to complete its execution, so that currentTime moves by 1
currentTime = currentTime + 1 = 0 + 1 = 1

take job_1 - 2
time it waited was till the currentTime - 1
waitTime = waitTime + currentTime = 0 + 1 = 1
now the job will process and will take time as 2 to complete its execution, so that currentTime moves by 2
currentTime = currentTime + 2 = 1 + 2 = 3

take job_2 - 3
time it waited was till the currentTime - 3
waitTime = waitTime + currentTime = 1 + 3 = 4
now the job will process and will take time as 3 to complete its execution, so that currentTime moves by 3
currentTime = currentTime + 3 = 3 + 3 = 6

take job_3 - 4
time it waited was till the currentTime - 6
waitTime = waitTime + currentTime = 4 + 6 = 10
now the job will process and will take time as 4 to complete its execution, so that currentTime moves by 4
currentTime = currentTime + 4 = 6 + 4 = 10

take job_4 - 7
time it waited was till the currentTime - 10
waitTime = waitTime + currentTime = 10 + 10 = 20
now the job will process and will take time as 4 to complete its execution, so that currentTime moves by 4
currentTime = currentTime + 7 = 10 + 7 = 17

After processing all the jobs, the total waitTime is 20
Total jobs processed = 5
Average wait time = 20/5 = 4

Return 4

 */
public class _3_ShortestJobFirst {

    private static int solve(int[] jobs) {
        int currentTime = 0;
        int waitTime = 0;

        Arrays.sort(jobs);
        for (int i = 0; i < jobs.length; i++) {
            waitTime += currentTime;

            // process the job, with some operation here, and then increase the current time.
            currentTime += jobs[i];
        }
        return waitTime / jobs.length;
    }

    public static void main(String[] args) {
        int[] jobs = {4, 3, 7, 1, 2};
        System.out.println(solve(jobs));
    }
}
