package com.dsalgo.graphs;

public class _41_DisjointSet_Help {
    /*

    What is Disjoint Set?
        A Disjoint Set (also called Union-Find) is a data structure that keeps track of a set of elements split into a number of non-overlapping (disjoint) subsets.

🔹 Why Use Disjoint Set?
    Disjoint Set is ideal for problems involving:
        Dynamic graphs – where edges are added or removed over time.
        Determining whether two nodes are in the same component.
        Efficient connectivity queries.
        Algorithms like:
            Kruskal’s Minimum Spanning Tree
            Cycle detection
            Network or group formation problems

    Disjoint set is a data structure.
    It is used when we want to connect nodes or components.

    This is usually used in Dynamic Graph

    Dynamic graph - they keep changing their configuration

    Disjoint set gives 2 functions -
        1. findParent()
        2. union()
            - union() is done using 2 approaches :
                - union by rank
                - union by size

        Disjoint set will help in figuring out whether 2 nodes belong to the same component in constant time.
        Without Disjoint set, we can figure this out using BFS/DFS but it take O(E + V) time complexity.

        Let's say we have nodes from 1 to 7 and initially they are not connected to each other.
        Consider the below 6 edges :
        1. (1,2)
        2. (2,3)
        3. (4,5)
        4. (6,7)
        5. (5,6)
        6. (3,7)

        If we add only first 4 edges, the graph will have 3 components like this :
        1 ---- 2 --- 3
        4 ---- 5
        6 ---- 7

        At this time, if we have to figure out whether 1 and 4 belong to the same component, it will take O(E + V) time.
        Later, we add edges 5 and 6, this will change the configuration of the graph.

        What is ultimate parent of a node : parent of parent of parent...till the root node is found

        Algorithm :
        Have 2 arrays - parent and rank
        parent :
            - denotes what is the parent of a given node
            - initialize it with the node itself
        rank :
            - what is the rank of a given node
            - initialize it with 0

         The DisjointSet data structure will have 2 functions -
            1. findParent
            2. union (By Rank)

         union :
         when union(u, v) is to be performed, carry out the below steps :
          - get ultimate parent of u and v, call them pu and pv
          - if ultimate parents are same, return
          - get ranks of pu and pv, call them ru and rv
          - compare ru and rv
          - attach smaller rank parent to larger rank parent as a child
          - if ranks are same, attach anyone to anyone
          - when ranks are same, and one is getting attached to another, then rank of the parent will increase by 1
          - however, when ranks are different, after attachment, the rank of the parent will not change, parent might have other children as well which determine the rank

          if ru < rv
            parent[ru] = rv // make rv as parent of ru
          if rv < ru
            parent[rv] = ru // make ru as parent of rv
         if ru == rv
            parent[ru] = rv // or else, you can do parent[rv] = ru
            rank[rv]++

        findParent :
            - this will return the ultimate parent
            - if parent[a] == a, then a is the ultimate parent
            - otherwise, recursively call the function for parent[a] until we get ultimate parent
            example: let's say we have the graph as 1 ---> 2 ---> 3 ---> 4 ---> 5
             - ultimate parent of 5 is 1, and so is for all the nodes.
             - when findParent(5) is called, it will recursively call findParent(4), and so on till findParent(1)
             - and finally, it will return 1, which is the ultimate parent of all the nodes.
             - during the same operation, update the ultimate parent of the other nodes (2,3,4) as well
             - this is called path compression
             - while doing path compression, do not update the rank, as it is possible that there are other children
             - as this is just a possibility, rank does not always reflect the correct value, but the algorithm works fine even without the exact value of the rank

        Now, if someone wants to find whether nodes a and b belong to the same component, we need to call the findParent function on a and b
        and check if they are the same. If yes, then they belong to the same component, otherwise not.

        union by size :
         - as rank was not exact, it is not very intuitive.
         - rather than rank, we can store the size for each node
         - declare an array named size, which denotes size of each node
         - we will follow similar algorithm

         declare another array size, and initialize all elements with 1, as the initial size of each element is 1

         when unionBySize(u, v) is to be performed, carry out the below steps :
          - get ultimate parent of u and v, call them pu and pv
          - if ultimate parents are same, return
          - get size of pu and pv, call them su and sv
          - compare su and sv
          - attach smaller size parent to larger size parent as a child
          - if size are same, attach anyone to anyone
          - in both the cases (whether size is same or different), update the size of parent as size[parent] = size[parent] + size[child]

        In any Disjoint set, we should use either of rank or size, we should never use both in the same set of operations

        Why attach smaller to the larger parent :
        - This is done to keep the tree as short as possible
        - if we attach larger to smaller, then the tree will become taller and taller
        - there will now be more nodes which take more time to reach the root

        Time complexity :
        - findParent - O(4 alpha) ~ O(1)
        - union - O(4 alpha) ~ O(1)

     */
}
