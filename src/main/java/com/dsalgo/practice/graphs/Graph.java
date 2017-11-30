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
    }
}
