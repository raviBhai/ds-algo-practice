package com.dsalgo.graphs;

import java.util.Arrays;

public class _42_DisjointSet {

}

class DisjointSet {
    int[] parent;
    int[] rank;
    int[] size;

    public DisjointSet(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    // get ultimate parent of a node
    public int findParent(int node) {
        if (node == parent[node]) {
            return node;
        }
        int ulp_node = findParent(parent[node]);
        parent[node] = ulp_node;    // path compression
        return ulp_node;
    }

    // brings 2 nodes under the same ultimate parent, if they are not already
    public void unionByRank(int u, int v) {
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        if (ulp_u == ulp_v) {   // same component, hence no operation to be performed
            return;
        }

        if (rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v;
        } else if (rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u;
        } else {    // same rank, so update the rank as well along with the parent
            parent[ulp_u] = ulp_v;
            rank[ulp_v]++;
        }
    }

    // brings 2 nodes under the same ultimate parent, if they are not already
    public void unionBySize(int u, int v) {
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        if (ulp_u == ulp_v) {   // same component, hence no operation to be performed
            return;
        }

        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        } else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }

    public static void main(String[] args) {
      //  byRank();
        bySize();
    }

    private static void byRank() {

        System.out.println("By Rank : ");

        DisjointSet ds = new DisjointSet(7);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);

        // until now, we have created 2 components:
        // 1----2-----3
        // 4----5----6----7
        // we will check if 3 and 7 belong to the same component

        if (ds.findParent(3) == ds.findParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not same");
        }

        // above will print "Not same"

        // now we will connect both the components, and will check again for 3 and 7
        ds.unionByRank(3, 7);

        if (ds.findParent(3) == ds.findParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not same");
        }
        // above will print "Same"
    }

    private static void bySize() {

        System.out.println("By Size : ");

        DisjointSet ds = new DisjointSet(7);
        ds.unionBySize(1, 2);
        ds.unionBySize(2, 3);
        ds.unionBySize(4, 5);
        ds.unionBySize(6, 7);
        ds.unionBySize(5, 6);

        // until now, we have created 2 components:
        // 1----2-----3
        // 4----5----6----7
        // we will check if 3 and 7 belong to the same component

        if (ds.findParent(3) == ds.findParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not same");
        }

        // above will print "Not same"

        // now we will connect both the components, and will check again for 3 and 7
        ds.unionBySize(3, 7);

        if (ds.findParent(3) == ds.findParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not same");
        }
        // above will print "Same"
    }
}