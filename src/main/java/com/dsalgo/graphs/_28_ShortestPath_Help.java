package com.dsalgo.graphs;

public class _28_ShortestPath_Help {


    /*

    Undirected graph :
        - unit distance         :   use BFS without Dijkstra. We can use Dijkstra's as well.
        - non-unit distance     :   Dijkstra's

    Directed graph :
        - cyclic                :   Dijkstra's algo, Floyd Warshlaw algo
        - acyclic               :   topo sort


Dijkstra : from a single source, find shortest paths to all nodes. No negative weight edges

Floyd-Warshall : shortest path from every node as a source.

Bellman-Ford : same as Dijkstra, but works for negative weights.

Topological Sort : print the nodes with no incoming edges first.

MST : connect all the nodes with minimum costs (n nodes // n-1 edges).

Prim's Algo : build the MST by starting from any node and expanding the tree one edge at a time.

Kruskal Algo : build the MST by sorting all edges and adding them one by one, ensuring no cycles are formed.


Shortest path -

IF Undirected Graph with unit distance - use normal Q
First time you encounter and visit a node, the path taken is the shortest path
It means, while polling from the Queue, we can exit if the polled node is the target node.
Example - _31_ShortestDistanceInBinaryMaze

IF Undirected Graph with non-unit distance - use priority Q
First time you encounter and visit a node, the path taken is the shortest path
It means, while polling from the Queue, we can exit if the polled node is the target node.
Example - _32_PathWithMinimumEffort

IF Undirected Graph with non-unit distance - and still we use normal Q rather than a PQ
First time you encounter and visit a node, the path taken is NOT the shortest path
It means, while polling from the Queue, we CANNOT exit if the polled node is the target node.
We need to let the iterations complete
Example - _33_CheapestFlightsWithinKStops

     */

}
