package com.dsalgo.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*

Given start and end times of meetings and one single meeting room.
At one time, only one meeting can take place in the room.
Find the maximum number of meetings that can happen in the room.

Algorithm -
To maximize the number of meetings in the room, we will try to have current meeting end as early as possible so that we can start the next meeting.
It means, we can sort the meeting times by end time.

After sorting, first meeting can always be held.
So, consider the first meeting and add it to result.
Start iterating from the second meeting, and check if the start time of the second meeting is greater than end time of first time.
If yes, then add second meeting to the result, and make the current end time as the end time of the second meeting.

 */
public class _6_MaxNumberOfMeetingsInOneRoom {

    private static void solve(int[] start, int[] end) {

        List<Triplet> meetings = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            Triplet triplet = new Triplet(start[i], end[i], i);
            meetings.add(triplet);
        }

        // Triplet - startTime, endTime, indexOfMeeting.
        // sort on end time
        Collections.sort(meetings, (t1, t2) -> t1.second - t2.second);

        int currentEndTime = meetings.get(0).second;
        int numberOfMeetingsHeld = 1;
        List<Integer> meetingOrder = new ArrayList<>();
        meetingOrder.add(meetings.get(0).third);

        for (int i = 1; i < meetings.size(); i++) {
            if (meetings.get(i).first > currentEndTime) {
                numberOfMeetingsHeld++;
                currentEndTime = meetings.get(i).second;
                meetingOrder.add(meetings.get(i).third);
            }
        }

        System.out.println("Number of meetings that can happen - " + numberOfMeetingsHeld);
        System.out.println("Meeting order - " + meetingOrder);
    }

    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 5, 7, 9, 9};

        solve(start, end);
    }
}


class Triplet {
    int first;
    int second;
    int third;

    Triplet(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

/*

This is followup question from previous question.

Given list of intervals, which may overlap.
Remove the minimum number of intervals such that the remaining intervals do not overlap.


Example -
Input-
{
    { 1, 2 }
    { 2, 3 }
    { 3, 4 }
    { 1, 3 }
}

1   2
    2   3
        3   4
1       3

If we remove interval (1,3), other intervals will not overlap.

If end time of interval_1 is same as start time of interval_2, they do not overlap.
In previous question, If end time of meeting_1 is same as start time of meeting_2, they were overlapping.

This question is similar to previous question.
In previous question, we figured out max number of meetings that can take place.
If we get the max meetings, we can get the min meetings that cannot take place.

Meetings(from previous question) are like Intervals(in this question)

Difference from previous question -
Previous question   -   currentMeetingStartTime > previousMeetingEndTime
This question       -   currentIntervalStartTime >= previousIntervalEndTime

 */
class NonOverlappingIntervals {
    private static void solve(int[] start, int[] end) {

        List<Triplet> intervals = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            Triplet triplet = new Triplet(start[i], end[i], i);
            intervals.add(triplet);
        }

        // Triplet - startTime, endTime, indexOfMeeting.
        // sort on end time
        Collections.sort(intervals, (t1, t2) -> t1.second - t2.second);

        int currentEndTime = intervals.get(0).second;
        int numberOfIntervalsConsidered = 1;
        List<Integer> intervalsOrder = new ArrayList<>();
        intervalsOrder.add(intervals.get(0).third);

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).first >= currentEndTime) {
                numberOfIntervalsConsidered++;
                currentEndTime = intervals.get(i).second;
                intervalsOrder.add(intervals.get(i).third);
            }
        }

        System.out.println("Number of meetings that can happen - " + numberOfIntervalsConsidered);
        System.out.println("Meeting order - " + intervalsOrder);
        System.out.println("Minimum number of intervals that need to be removed - " + (intervals.size() - numberOfIntervalsConsidered));
    }

    public static void main(String[] args) {
        int[] start1 = {1, 2, 3, 1};
        int[] end1 = {2, 3, 4, 3};
        solve(start1, end1);
    }
}