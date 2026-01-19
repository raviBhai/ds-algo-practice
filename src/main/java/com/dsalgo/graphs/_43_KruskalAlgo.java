package com.dsalgo.graphs;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Given a weighted undirected graph, find its MST.

Algo :
List all the edges and sort them by weight.
Pick the smallest edge, check if edge.from and edge.to nodes belong to the same component.
If not, add this edge to the MST
Add the weight
Use Disjoint set

 */
public class _43_KruskalAlgo {

    private static int solve(List<List<Pair<Integer, Integer>>> adjacencyList) {

        // distance, from, to
        List<Triplet<Integer, Integer, Integer>> edges = new ArrayList<>();


        // O (N + E)
        for (int i = 0; i < adjacencyList.size(); i++) {
            List<Pair<Integer, Integer>> neighbours = adjacencyList.get(i);
            for (Pair<Integer, Integer> neighbour : neighbours) {
                edges.add(new Triplet<>(neighbour.second, i, neighbour.first));
            }
        }

        // O ( E log E)
        Collections.sort(edges, new Comparator<Triplet<Integer, Integer, Integer>>() {
            @Override
            public int compare(Triplet<Integer, Integer, Integer> a, Triplet<Integer, Integer, Integer> b) {
                return a.first - b.first;
            }
        });

        DisjointSet ds = new DisjointSet(adjacencyList.size());
        int sum = 0;

        // O( E * 4 * alpha )
        for (Triplet<Integer, Integer, Integer> edge : edges) {
            if (ds.findParent(edge.second) != ds.findParent(edge.third)) {
                sum += edge.first;
                ds.unionBySize(edge.second, edge.third);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        List<List<Pair<Integer, Integer>>> adjacencyList = new ArrayList<>();
        int n = 6;
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        adjacencyList.get(1).add(new Pair<>(4, 1));
        adjacencyList.get(1).add(new Pair<>(5, 4));

        adjacencyList.get(2).add(new Pair<>(1, 2));
        adjacencyList.get(2).add(new Pair<>(4, 3));
        adjacencyList.get(2).add(new Pair<>(3, 3));
        adjacencyList.get(2).add(new Pair<>(6, 7));

        adjacencyList.get(3).add(new Pair<>(4, 5));
        adjacencyList.get(3).add(new Pair<>(2, 3));
        adjacencyList.get(3).add(new Pair<>(6, 8));

        adjacencyList.get(4).add(new Pair<>(5, 9));
        adjacencyList.get(4).add(new Pair<>(1, 1));
        adjacencyList.get(4).add(new Pair<>(2, 3));
        adjacencyList.get(4).add(new Pair<>(3, 5));

        adjacencyList.get(5).add(new Pair<>(1, 4));
        adjacencyList.get(5).add(new Pair<>(4, 9));

        adjacencyList.get(6).add(new Pair<>(2, 7));
        adjacencyList.get(6).add(new Pair<>(3, 8));

        System.out.println(solve(adjacencyList));
    }
}


