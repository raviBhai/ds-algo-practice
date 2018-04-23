package com.dsalgo.interviewbit.arrays;

import java.util.ArrayList;

public class MergeIntervals {
    public static void main(String[] args) {

        MergeIntervals mi = new MergeIntervals();
        ArrayList<Interval> intervals = new ArrayList<Interval>() {{
            add(new Interval(1,2));
            add(new Interval(3,4));
            add(new Interval(6,7));
            add(new Interval(8,10));
            add(new Interval(15,18));
        }};
        System.out.println(intervals);
        ArrayList<Interval> result = mi.insert(intervals, new Interval(5, 9));
        System.out.println(result);
    }

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {

        ArrayList<Interval> overLappingIntervals = new ArrayList<>();
        ArrayList<Interval> beforeOverlap = new ArrayList<>();
        ArrayList<Interval> afterOverlap = new ArrayList<>();

        //Get overlapping intervals
        for (Interval interval : intervals) {
            if (isOverlap(newInterval, interval)) {
                overLappingIntervals.add(interval);
            } else {
                if (isBefore(interval, newInterval)) {
                    beforeOverlap.add(interval);
                } else if (isAfter(interval, newInterval)) {
                    afterOverlap.add(interval);
                }
            }
        }

        Interval consolidatedInterval = null;
        if (!overLappingIntervals.isEmpty()) {
            consolidatedInterval = new Interval(Math.min(overLappingIntervals.get(0).start, newInterval.start),
                    Math.max(overLappingIntervals.get(overLappingIntervals.size() - 1).end, newInterval.end));
        } else {
            consolidatedInterval = newInterval;
        }

        ArrayList<Interval> result = new ArrayList<>();
        result.addAll(beforeOverlap);
        result.add(consolidatedInterval);
        result.addAll(afterOverlap);

        return result;
    }

    private boolean isOverlap(Interval first, Interval second) {
        return isBetween(second.start, first.start, first.end)
                || isBetween(second.end, first.start, first.end)
                || isBetween(first.start, second.start, second.end)
                || isBetween(first.end, second.start, second.end);
    }

    public boolean isBetween(int a, int start, int end) {
        if (start <= a && a <= end) {
            return true;
        }
        return false;
    }

    private boolean isBefore(Interval a, Interval b) {
        return a.end < b.start;
    }

    private boolean isAfter(Interval a, Interval b) {
        return b.end < a.start;
    }
}

class Interval {
      int end;
      int start;
      Interval() {
          start = 0; end = 0;
      }
      Interval(int s, int e) {
          start = s; end = e;
      }

    @Override
    public String toString() {
        return "(" + start + "," + end + ")";
    }
}
