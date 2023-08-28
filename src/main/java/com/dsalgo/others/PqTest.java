package com.dsalgo.others;


public class PqTest {

    public static void main(String[] args) {
        Cell c1 = new Cell(1, 2, 3);
        System.out.println(c1.hashCode());
        c1.data = 5;
        c1.a = 6;
        c1.b = 7;
        System.out.println(c1.hashCode());
    }
}

class Cell {
    int data;
    int a;
    int b;
    Cell (int data, int a, int b) {
        this.data = data;
        this.a = a;
        this.b = b;
    }
}
