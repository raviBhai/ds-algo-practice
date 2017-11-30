package com.dsalgo.practice.graphs;

import java.util.Arrays;

public class DirectedGraph {
    public Vertex[] vertexList;
    public int[][] adjMatrix;
    public int numberOfVertices;
    public int maxVertices;
    public char[] sortedList;

    public DirectedGraph(int max) {
        numberOfVertices = 0;
        maxVertices = max;
        vertexList = new Vertex[max];
        sortedList = new char[max];
        adjMatrix = new int[max][max];

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }

    public void addVertex(char c) {
        Vertex vertex = new Vertex(c);
        vertexList[numberOfVertices++] = vertex;
    }

    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
    }

    public void topo() {
        int originalVertices = numberOfVertices;
        while (numberOfVertices > 0) {
            int vertex = noSuccessor();
            if (vertex == -1) {
                System.out.println("Invalid graph as there is a cycle in it");
                break;
            }
            sortedList[numberOfVertices - 1] = vertexList[vertex].label;
            deleteVertex(vertex);
        }
        for (int i = 0; i < originalVertices; i++) {
            System.out.print(sortedList[i]);
        }
    }

    public int noSuccessor() {
        boolean isEdge;
        for (int row = 0; row < numberOfVertices; row++) {
            isEdge = false;
            for (int column = 0; column < numberOfVertices; column++) {
                if (adjMatrix[row][column] > 0) {
                    isEdge = true;
                    break;
                }
            }
            if (!isEdge) {
                return row;
            }
        }
        return -1;
    }

    public void deleteVertex(int delVertex) {
        for (int i = delVertex; i < numberOfVertices - 1; i++) {
            vertexList[i] = vertexList[i + 1];
        }

        for (int row = delVertex; row < numberOfVertices - 1; row++) {
            moveRowUp(row, numberOfVertices);
        }

        for (int column = delVertex; column < numberOfVertices - 1; column++) {
            moveColumnLeft(column, numberOfVertices - 1);
        }
        numberOfVertices--;
    }

    public void moveRowUp(int row, int length) {
        for (int column = 0; column < length; column++) {
            adjMatrix[row][column] = adjMatrix[row + 1][column];
        }
    }

    public void moveColumnLeft(int column, int length) {
        for (int row = 0; row < length; row++) {
            adjMatrix[row][column] = adjMatrix[row][column + 1];
        }
    }
}

class TopoApp
{
    public static void main(String[] args)
    {
        DirectedGraph theGraph = new DirectedGraph(20);
        theGraph.addVertex('A');    // 0
        theGraph.addVertex('B');    // 1
        theGraph.addVertex('C');    // 2
        theGraph.addVertex('D');    // 3
        theGraph.addVertex('E');    // 4
        theGraph.addVertex('F');    // 5
        theGraph.addVertex('G');    // 6
        theGraph.addVertex('H');    // 7

        theGraph.addEdge(0, 3);     // AD
        theGraph.addEdge(0, 4);     // AE
        theGraph.addEdge(1, 4);     // BE
        theGraph.addEdge(2, 5);     // CF
        theGraph.addEdge(3, 6);     // DG
        theGraph.addEdge(4, 6);     // EG
        theGraph.addEdge(5, 7);     // FH
        theGraph.addEdge(6, 7);     // GH

        theGraph.topo();            // do the sort
    }  // end main()
}
