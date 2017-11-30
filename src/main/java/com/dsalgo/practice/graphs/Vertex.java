package com.dsalgo.practice.graphs;

public class Vertex {
    public char label;
    public boolean wasVisited;

    public Vertex() {

    }

    public Vertex(char c) {
        this.label = c;
    }

    public void display() {
        System.out.print(label);
    }
}
