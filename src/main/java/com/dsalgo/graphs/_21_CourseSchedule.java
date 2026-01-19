package com.dsalgo.graphs;

import java.util.*;

public class _21_CourseSchedule {


}

/*

Given input as tasks and their orders. Also, given input as the number of distinct tasks
0,1 represents that to complete 1, first complete 0
Given such pairs of tasks and their dependencies.
Return true if it is possible to perform all the tasks.

Solution -
As ordering is given, we can think of topological sort.
In the given problem, it will be not possible to perform all the tasks if there is a cycle.
Hence, we can use topo sort to check if there is a cycle

 */
class _1_CourseScheduleOne {
    private static boolean topo(int[][] tasks, int N) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] pair : tasks) {
            adjacencyList.get(pair[0]).add(pair[1]);
        }

        int[] indegree = new int[adjacencyList.size()];
        for (List<Integer> neighbours : adjacencyList) {
            for (int neighbour : neighbours) {
                indegree[neighbour]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        //List<Integer> result = new ArrayList<>();
        int topoCount = 0;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            // rather than storing the path for the topo sort, we will maintain the count
            //result.add(node);
            topoCount++;

            List<Integer> neighbours = adjacencyList.get(node);
            for (int neighbour : neighbours) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }
        return topoCount == N;
    }

    public static void main(String[] args) {
        int[][] tasks1 = {
                {0,1},
                {1,2},
                {2,0}
        };
        System.out.println(topo(tasks1, 3)); // false

        int[][] tasks2 = {
                {0,1},
                {1,2},
                {2,3}
        };
        System.out.println(topo(tasks2, 4)); // true
    }
}
/*
Following up from the above question, return the order in which the tasks can be performed.
If the tasks cannot be performed, return empty list.
The tasks cannot be performed if there is a cycle.
 */
class _2_CourseScheduleTwo {
    private static List<Integer> topo(int[][] tasks, int N) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] pair : tasks) {
            adjacencyList.get(pair[0]).add(pair[1]);
        }

        int[] indegree = new int[adjacencyList.size()];
        for (List<Integer> neighbours : adjacencyList) {
            for (int neighbour : neighbours) {
                indegree[neighbour]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            result.add(node);

            List<Integer> neighbours = adjacencyList.get(node);
            for (int neighbour : neighbours) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }

        if (result.size() == N) {
            return result;
        }
        return Collections.emptyList();
    }
    public static void main(String[] args) {
        int[][] tasks1 = {
                {0,1},
                {1,2},
                {2,0}
        };
        System.out.println(topo(tasks1, 3)); // [], empty array as there is a cycle

        int[][] tasks2 = {
                {0,1},
                {1,2},
                {2,3}
        };
        System.out.println(topo(tasks2, 4)); // [0,1,2,3]
    }
}

/*
Tasks represents courses. In semester 1, you can do the tasks that do not require prerequisites.
Then, in the subsequent semester, you can do other tasks.
Find the number of minimum number of semesters required to complete all the courses
 */
class _3_CourseScheduleThree {
    private static Integer topo(int[][] tasks, int N) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] pair : tasks) {
            adjacencyList.get(pair[0]).add(pair[1]);
        }

        int[] indegree = new int[adjacencyList.size()];
        for (List<Integer> neighbours : adjacencyList) {
            for (int neighbour : neighbours) {
                indegree[neighbour]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int topoCount = 0;
        int semesters = 0;

        while (!queue.isEmpty()) {

            semesters++;

            // for each semester, process all the nodes currently in the queue (the ones available this semester) and then add new ones (next semester’s tasks) after processing the current level.
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int node = queue.poll();
                topoCount++;

                for (int neighbour : adjacencyList.get(node)) {
                    indegree[neighbour]--;
                    if (indegree[neighbour] == 0) {
                        queue.add(neighbour);
                    }
                }
            }

        }

        if (topoCount != N) {
            return -1;
        }
        return semesters;
    }
    public static void main(String[] args) {
        int[][] tasks1 = {
                {0,1},
                {1,2},
                {2,0}
        };
        System.out.println(topo(tasks1, 3)); // [], empty array as there is a cycle

        int[][] tasks2 = {
                {0,1},
                {1,2},
                {2,3}
        };
        System.out.println(topo(tasks2, 4)); // [0,1,2,3]
    }
}