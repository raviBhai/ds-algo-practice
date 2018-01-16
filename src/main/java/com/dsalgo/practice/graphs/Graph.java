package com.dsalgo.practice.graphs;

import com.dsalgo.practice.stacksqueues.ArrayQueue;
import com.dsalgo.practice.stacksqueues.ArrayStack;
import com.dsalgo.practice.stacksqueues.Queue;
import com.dsalgo.practice.stacksqueues.Stack;

public class Graph {
    private Vertex[] vertexList;
    private int[][] adjacencyMatrix;
    private int numberOfVertices;
    private int maxVertices;

    public Graph(int max) {
        numberOfVertices = 0;
        maxVertices = max;
        vertexList = new Vertex[max];
        adjacencyMatrix = new int[max][max];

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }

    public void addVertex(char c) {
        Vertex vertex = new Vertex(c);
        vertexList[numberOfVertices++] = vertex;
    }

    public void addEdge(int row, int column) {
        adjacencyMatrix[row][column] = 1;
        adjacencyMatrix[column][row] = 1;
    }

    public int getAdjUnVisitedVertex(int v) {
        for (int i = 0; i < numberOfVertices; i++) {
            if (adjacencyMatrix[v][i] == 1 && vertexList[i].wasVisited == false) {
                return i;
            }
        }
        return -1;
    }

    public void dfs() {
        Stack<Integer> stack = new ArrayStack<Integer>(maxVertices);
        stack.push(0);
        vertexList[0].wasVisited = true;
        vertexList[0].display();

        while (!stack.isEmpty()) {
            int adjUnVisitedVertex = getAdjUnVisitedVertex(stack.peek());
            if (adjUnVisitedVertex == -1) {
                stack.pop();
            } else {
                vertexList[adjUnVisitedVertex].wasVisited = true;
                stack.push(adjUnVisitedVertex);
                vertexList[adjUnVisitedVertex].display();
            }
        }
        resetVisitedFlags();
    }

    public void dfs_recursion(int vertex) {
        vertexList[vertex].display();
        vertexList[vertex].wasVisited = true;
        int adjVertex;

        while ((adjVertex = getAdjUnVisitedVertex(vertex)) != -1) {
            dfs_recursion(adjVertex);
        }
    }

    public void bfs() {
        Queue<Integer> queue = new ArrayQueue<Integer>(maxVertices);
        queue.insert(0);
        vertexList[0].wasVisited = true;
        vertexList[0].display();

        int v;
        while (!queue.isEmpty()) {
            Integer currentVertex = queue.remove();
            while ((v = getAdjUnVisitedVertex(currentVertex)) != -1) {
                queue.insert(v);
                vertexList[v].wasVisited = true;
                vertexList[v].display();
            }
        }
        resetVisitedFlags();
    }

    public void mst() {
        Stack<Integer> stack = new ArrayStack<Integer>(maxVertices);
        stack.push(0);
        vertexList[0].wasVisited = true;

        while (!stack.isEmpty()) {
            Integer currentVertex = stack.peek();
            int adjUnVisitedVertex = getAdjUnVisitedVertex(currentVertex);
            if (adjUnVisitedVertex == -1) {
                stack.pop();
            } else {
                vertexList[adjUnVisitedVertex].wasVisited = true;
                stack.push(adjUnVisitedVertex);
                vertexList[currentVertex].display();
                vertexList[adjUnVisitedVertex].display();
                System.out.print(" ");
            }
        }
        resetVisitedFlags();
    }

    public boolean routeExistsBetweenNodesUsingDfs(int start, int end) {
        if (start == end) {
            return true;
        }
        Stack<Integer> stack = new ArrayStack<Integer>(maxVertices);
        stack.push(start);
        vertexList[start].wasVisited = true;
        boolean isRouteExists = false;
        while (!stack.isEmpty()) {
            int adjVertex = getAdjUnVisitedVertex(stack.peek());
            if (adjVertex == -1) {
                stack.pop();
            } else {
                if (end == adjVertex) {
                    isRouteExists = true;
                    break;
                }
                vertexList[adjVertex].wasVisited = true;
                stack.push(adjVertex);
            }
        }
        resetVisitedFlags();
        return isRouteExists;
    }

    public boolean routeExistsBetweenNodesUsingBfs(int start, int end) {
        if (start == end) {
            return true;
        }
        Queue<Integer> queue = new ArrayQueue<Integer>(maxVertices);
        queue.insert(start);
        vertexList[start].wasVisited = true;
        boolean isRouteExists = false;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            int adjVertex;
            while ((adjVertex = getAdjUnVisitedVertex(current)) != -1) {
                if (adjVertex == end) {
                    isRouteExists = true;
                    break;
                }
                queue.insert(adjVertex);
                vertexList[adjVertex].wasVisited = true;
            }
        }
        resetVisitedFlags();
        return isRouteExists;
    }

    public void resetVisitedFlags() {
        for (int i = 0; i < numberOfVertices; i++) {
            vertexList[i].wasVisited = false;
        }
    }
}
class GraphCleint {
    public static void main(String[] args) {
        Graph graph = new Graph(9);
        graph.addVertex('A');    // 0
        graph.addVertex('B');    // 1
        graph.addVertex('C');    // 2
        graph.addVertex('D');    // 3
        graph.addVertex('E');    // 4
        graph.addVertex('F');    // 5
        graph.addVertex('G');    // 6
        graph.addVertex('H');    // 7
        graph.addVertex('I');    // 8

        graph.addEdge(0, 1);    //AB
        graph.addEdge(0, 2);    //AC
        graph.addEdge(0, 3);    //AD
        graph.addEdge(0, 4);    //AE
        graph.addEdge(1, 5);    //BF
        graph.addEdge(5, 7);    //FH
        graph.addEdge(3, 6);    //DG
        graph.addEdge(6, 8);    //GI

        graph.dfs();            //ABFHCDGIE
        System.out.println();

        graph.dfs_recursion(0);   //ABFHCDGIE
        graph.resetVisitedFlags();
        System.out.println();

        graph.dfs();
        System.out.println();

        System.out.println(graph.routeExistsBetweenNodesUsingDfs(0, 6));
        System.out.println(graph.routeExistsBetweenNodesUsingBfs(0, 6));
    }
}
