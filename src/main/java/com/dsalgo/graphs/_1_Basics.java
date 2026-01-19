package com.dsalgo.graphs;

public class _1_Basics {

    /**
     *
     * Undirected graphs -
     *
     *
     *
     * Directed graphs -
     *
     *
     * Cycle in a graph - starts from a node and ends at the SAME node.
     *
     * Below is undirected graph which has a cycle as we can start from 1 and end at 1
     *      1 ----- 2
     *      \      /
     *       \   /
     *        \/
     *        3
     *
     *      If the above graph had edges from 1 to 2, 1 to 3 and 2 to 3 shown as below, it does not have a cycle.
     *      Because, there is no path which starts and ends at the same node.
     *      The below graph is a Directed Acyclic Graph (DAG)
     *
     *      1 -----> 2
     *      |        |
     *      |        |
     *      |        ↓
     *      -------> 3
     *
     *
     * Degree of a node in an undirected graph - Number of edges linked to a node.
     * In the above undirected graph, degree of node 1 is 2.
     *
     * Degree of a node in a directed graph -
     * Degree of a node = number of edges connected to the node (incoming + outgoing)
     * Degree of a node is sum of indegree and outdegree
     * Indegree - number of edges going in to a node
     * Outdegree - number of edges going out from a node
     * In the above directed graph,
     *      indegree of node 1 is 0.
     *      indegree of node 2 is 1.
     *      indegree of node 3 is 2.
     *
     *      outdegree of node 1 is 2.
     *      outdegree of node 2 is 1.
     *      outdegree of node 3 is 0.
     *
     *
     * Total degree of the entire graph = sum of degrees of all nodes
     *                                  = 2 * numberOfEdgesInAGraph
     *
     */
}
