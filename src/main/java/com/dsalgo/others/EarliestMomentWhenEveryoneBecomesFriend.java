package com.dsalgo.others;

import java.util.*;

public class EarliestMomentWhenEveryoneBecomesFriend {


    public int earliestAcq(int[][] logs, int n) {
        boolean[] visited = new boolean[n];
        List<Set<Integer>> groups = new ArrayList<>();

        Arrays.sort(logs, (a, b) -> Integer.compare(a[0], b[0]));

        for (int  i = 0; i < logs.length; i++) {
            int[] log = logs[i];
            if (!visited[log[1]] && !visited[log[2]]) {
                Set<Integer> newGroup = new HashSet<>();
                newGroup.add(log[1]);
                newGroup.add(log[2]);
                groups.add(newGroup);
            } else {
                if (visited[log[1]] && !visited[log[2]]) {
                    Set<Integer> group = getGroupOf(log[1], groups);

                    group.add(log[2]);
                } else if (!visited[log[1]] && visited[log[2]]) {
                    Set<Integer> group = getGroupOf(log[2], groups);
                    group.add(log[1]);
                } else {
                    Set<Integer> group1 = getGroupOf(log[1], groups);
                    Set<Integer> group2 = getGroupOf(log[2], groups);
                    if (group1 != group2) {
                        for (Integer entry : group2) {
                            group1.add(entry);
                        }
                        groups.remove(group1);
                    }
                }
            }
            visited[log[1]] = true;
            visited[log[2]] = true;

            if (allVisited(visited) && groups.size() == 1) {
                return log[0];
            }
        }
        return -1;
    }

    private Set<Integer> getGroupOf(int person, List<Set<Integer>> groups) {
        for (Set<Integer> group : groups) {
            if (group.contains(person)) {
                return group;
            }
        }
        return null;
    }

    private boolean allVisited (boolean[] visited) {
        for (boolean entry : visited) {
            if (!entry) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        EarliestMomentWhenEveryoneBecomesFriend obj = new EarliestMomentWhenEveryoneBecomesFriend();
        int[][] logs = {
                {5,3,2}, {3,0,1},{2,2,0},{10,2,4},{0,3,1},{8,3,4}
        };
        obj.earliestAcq(logs, 5);

    }
}
