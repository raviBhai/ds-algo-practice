package com.dsalgo.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParallelCourses {


    /*
    public int minimumSemesters(int n, int[][] relations) {
        int[][] adj = getAdjMatrix(n, relations);
        int count = 0;
        while (n > 0) {
            List<Integer> courses = noSuccessors(adj, n);
            if (courses.size() == 0) {
                return -1;
            }
            deleteVertexes(courses, adj, n);
            n = n - courses.size();
            count++;
        }
        return count;
    }

    private void deleteVertexes(List<Integer> courses, int[][] adj, int n) {
        for (int del : courses) {

            for (int i = del; i < n - 1; i++) {
                moveRowUp(i, n, adj);
            }

            for (int i = del; i < n - 1; i++) {
                moveColLeft(i, n-1, adj);
            }

        }
    }

    private void moveRowUp(int row, int colLength, int[][] adj) {
        for (int i = 0; i < colLength; i++) {
            adj[row][i] = adj[row+1][i];
        }
    }

    private void moveColLeft(int col, int rowLength, int[][] adj) {
        for (int i = 0; i < rowLength; i++) {
            adj[i][col] = adj[i][col+1];
        }
    }

    private List<Integer> noSuccessors(int[][] adj, int n) {
        List<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean isEdge = false;
            for (int j = 0; j < n; j++) {
                if (adj[i][j] == 1) {
                    isEdge = true;
                    break;
                }
            }
            if (!isEdge) {
                nodes.add(i);
            }
        }
        return nodes;
    }

    private int[][] getAdjMatrix(int n, int[][] relations) {
        int[][] adj = new int[n][n];
        for (int i = 0; i < relations.length; i++) {
            int row = relations[i][0] - 1;
            int col = relations[i][1] - 1;
            adj[row][col] = 1;
        }
        return adj;
    }
    */


    public int minimumSemesters(int n, int[][] relations) {

        Map<Integer, List<Integer>> dependentNodes = new HashMap<>();
        Map<Integer, Integer> dependencyCount = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            dependencyCount.put(i, 0);
        }

        int semesters = 0;

        for (int[] relation : relations) {
            int count = dependencyCount.get(relation[1]);
            dependencyCount.put(relation[1], count + 1);

            dependentNodes.putIfAbsent(relation[0], new ArrayList());
            dependentNodes.get(relation[0]).add(relation[1]);
        }
        boolean[] removed = new boolean[n + 1];

        while (n > 0) {
            List<Integer> nodesToBeRemoved = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : dependencyCount.entrySet()) {
                if (entry.getValue() == 0 && !removed[entry.getKey()]) {
                    nodesToBeRemoved.add(entry.getKey());
                }
            }

            if (nodesToBeRemoved.size() == 0) {
                return -1;
            }



            for (int node : nodesToBeRemoved) {

                List<Integer> dependents = dependentNodes.get(node);

                if (dependents != null) {
                    for (int dependent : dependents) {
                        int dpCount = dependencyCount.get(dependent);
                        dependencyCount.put(dependent, dpCount - 1);
                    }
                }

                removed[node] = true;
            }


            n = n - nodesToBeRemoved.size();
            semesters++;
        }

        return semesters;
    }

    public static void main(String[] args) {
        ParallelCourses pc = new ParallelCourses();
        int[][] relations = {
                    {1, 3},
                    {2, 3}
                };
        int n = 3;
        System.out.println(pc.minimumSemesters(n, relations));
    }
}
