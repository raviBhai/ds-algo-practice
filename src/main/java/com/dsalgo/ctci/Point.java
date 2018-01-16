package com.dsalgo.ctci;

import java.util.Collection;
import java.util.HashSet;

public class Point {
    Integer row;
    Integer column;

    Point left;
    Point right;

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }

    public boolean presentIn(Collection<Point> points) {
        for (Point p : points) {
            if (p.row == this.row && p.column == this.column) {
                return true;
            }
        }
        return false;
    }
}
