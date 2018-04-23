package com.dsalgo.interviewbit.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class SearchForARange {
    public static void main(String[] args) {
        SearchForARange sfr = new SearchForARange();

        ArrayList<Integer> list = new ArrayList<Integer>() {{
            //add(18);
            /*add(18);
            add(18);
            add(18);
            add(18);*/
            add(5);
            add(7);
            add(7);
            add(8);
            add(8);
            add(10);
        }};

        System.out.println(sfr.searchRange(list, 8));
    }

    public ArrayList<Integer> searchRange(final List<Integer> a, int b) {

        if (a.isEmpty()) {
            return notFoundResult();
        }

        int lRange = lowerRange(a, b);
        if (lRange == -1) {
            return notFoundResult();
        }

        int uRange = upperRange(a, b);
        ArrayList<Integer> range = new ArrayList<>();
        range.add(lRange);
        range.add(uRange);
        return range;
    }

    public int lowerRange(List<Integer> a, int b) {
        int lower = 0, upper = a.size() - 1, mid = 0;
        int lowerIndex = -1;
        while (true) {
            if (upper < 0 || lower > upper) {
                break;
            }
            mid = (lower + upper) / 2;
            if (a.get(mid) == b) {
                lowerIndex = mid;
                upper = mid - 1;
            } else if (lower > upper) {
                break;
            } else {
                if (a.get(mid) >= b) {
                    upper = mid - 1;
                } else {
                    lower = mid + 1;
                }
            }

        }
        return lowerIndex;
    }

    public int upperRange(List<Integer> a, int b) {
        int lower = 0, upper = a.size() - 1, mid = 0;
        int upperIndex = -1;
        while (true) {
            if (lower >= a.size() || lower > upper) {
                break;
            }
            mid = (lower + upper) / 2;
            if (a.get(mid) == b) {
                upperIndex = mid;
                lower = mid + 1;
            } else if (lower > upper) {
                break;
            } else {
                if (a.get(mid) >= b) {
                    upper = mid - 1;
                } else {
                    lower = mid + 1;
                }
            }

        }
        return upperIndex;
    }

    private ArrayList<Integer> notFoundResult() {
        ArrayList<Integer> range = new ArrayList<>();
        range.add(-1);
        range.add(-1);
        return range;
    }
}
