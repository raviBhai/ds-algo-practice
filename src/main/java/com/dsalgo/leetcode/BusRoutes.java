package com.dsalgo.leetcode;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        if (isSourceAndTargetBySameBus(routes, source, target)) {
            return 1;
        }

        boolean[] visited = new boolean[routes.length];
        Queue<Cell> q = new LinkedList<>();
        int routeIndex = getRouteIndex(source, visited, routes);
        int[] route = routes[routeIndex];
        for (int stop : route) {
            if (stop != source) {
                Cell c = new Cell();
                c.stop = stop;
                c.buses = 1;
                q.add(c);
            }
        }
        visited[routeIndex] = true;

        int minBuses = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Cell c = q.remove();
            int routeIndex1 = getRouteIndex(c.stop, visited, routes);
           /* while ((routeIndex1 = getRouteIndex(c.stop, visited, routes)) != -1) {

            }*/
            if (routeIndex1 == -1) {
                continue;
            }
            int[] route1 = routes[routeIndex1];
            for (int stop : route1) {
                Cell c1 = new Cell();
                c1.stop = stop;
                c1.buses = c.buses + 1;
                if (c1.stop == target) {
                    minBuses = Math.min(minBuses, c1.buses);
                } else {
                    q.add(c1);
                }
            }
            visited[routeIndex1] = true;

        }

        return minBuses == Integer.MAX_VALUE ? -1 : minBuses;
    }

    private int getRouteIndex(int stop, boolean[] visited, int[][] routes) {
        for (int i = 0; i < routes.length; i++) {
            if (!visited[i]) {
                int[] route = routes[i];
                for (int j = 0; j < route.length; j++) {
                    if (route[j] == stop) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    private boolean isSourceAndTargetBySameBus(int[][] routes, int source, int target) {
        boolean isSourceFound=false, isTargetFound=false;
        for (int i = 0; i < routes.length; i++) {
            int[] route = routes[i];

            for (int j = 0; j < route.length; j++) {
                if (route[j] == source) {
                    isSourceFound = true;
                }
                if (route[j] == target) {
                    isTargetFound = true;
                }
            }

            if (isSourceFound && isTargetFound) {
                return true;
            }

            isSourceFound = false;
            isTargetFound = false;

        }
        return false;
    }

    public static void main(String[] args) {
        BusRoutes busRoutes = new BusRoutes();

        int[][] routes = {
                {10,13,22,28,32,35,43},
                {2,11,15,25,27},
                {6,13,18,25,42},
                {5,6,20,27,37,47},
                {7,11,19,23,35},
                {7,11,17,25,31,43,46,48},
                {1,4,10,16,25,26,46},
                {7,11},
                {3,9,19,20,21,24,32,45,46,49},
                {11,41}
    };

        System.out.println(busRoutes.numBusesToDestination(routes, 37, 43));
    }
}

class Cell {
    int stop;
    int buses;
}
