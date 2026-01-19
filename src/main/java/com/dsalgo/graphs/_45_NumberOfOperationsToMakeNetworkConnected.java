package com.dsalgo.graphs;

/*

Given a graph with multiple components.

We have to find the minimum number of edges required to connect the components such that at the end we have only one component.
However, whenever we connect 2 separate components with an edge, we need to remove an existing edge from the graph.

Solution :
If the graph has 4 components called A, B, C, D, it will require minium 3 edges to connect all 4.
Using _44_NumberOfProvinces_DisjointSet, we can find the number of components.
Minimum edges required to connect the components = numberOfComponents - 1

However, we also need to figure out if we can remove 3 edges from the graph such that graph still remains connected.
We will remove the edge from the nodes only when we know that even after the edge removal, the nodes will still remain connected.

We can use Disjoin Set for this.
We can iterate over the edges and while iterating, for each edge, get the from and to node.
We can then check if the from and to nodes have the same ultimate parent, if yes, they are already connected.
It means, this edge is an extra edge.
Count such extra edges.

IF the count of the extra edges >= numberOfComponents - 1,
If yes, return numberOfComponents - 1, else it is not possible, hence return -1

 */
public class _45_NumberOfOperationsToMakeNetworkConnected {

    private static int solve(int n, int[][] edges) {
        DisjointSet ds = new DisjointSet(n);

        int extraEdges = 0;
        for (int[] edge : edges) {
            if (ds.findParent(edge[0]) == ds.findParent(edge[1])) {
                extraEdges++;
            } else {
                ds.unionBySize(edge[0], edge[1]);
            }
        }

        int numberOfComponents = 0;
        int[] parent = ds.parent;
        for (int i = 0; i < parent.length - 1; i++) {
            if (parent[i] == i) {
                numberOfComponents++;
            }
        }

        if (extraEdges >= numberOfComponents - 1) {
            return numberOfComponents - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1},
                {0, 2},
                {0, 3},
                {1, 2},
                {2, 3},
                {4, 5},
                {5, 6},
                {7, 8}

        };

        int n = 9;
        System.out.println(solve(n, edges));
    }
}
