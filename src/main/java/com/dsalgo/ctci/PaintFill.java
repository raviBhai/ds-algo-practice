package com.dsalgo.ctci;

import java.util.Arrays;

public class PaintFill {

    static String originalColor = "";

    public static void main(String[] args) {
        Color[][] matrix = {
                {new Color("R"), new Color("R"), new Color("R"), new Color("G")},
                {new Color("R"), new Color("G"), new Color("G"), new Color("")},
                {new Color("R"), new Color("G"), new Color(""), new Color("")},
                {new Color("R"), new Color(""), new Color(""), new Color("")},
                {new Color("R"), new Color(""), new Color(""), new Color("")}
        };

        /*Color[][] matrix = {
                {new Color("R"), new Color("R"), new Color("R") },
                {new Color("R"), new Color("R"), new Color("R") },
                {new Color("R"), new Color("R"), new Color("R") }
        };*/

        /*Color[][] matrix = {
                {new Color("R"), new Color("R") },
                {new Color("R"), new Color("") }
        };*/

        System.out.println("Before:");
        for (Color[] row : matrix) {
            System.out.println(Arrays.toString(row));
            System.out.println("");
        }

        PaintFill paint = new PaintFill();
        Pixel px = new Pixel(4, 3);
        originalColor = matrix[px.row][px.col].color;
        paint.fill(px, new Color("B"), matrix);

        System.out.println("After:");
        for (Color[] row : matrix) {
            System.out.println(Arrays.toString(row));
            System.out.println("");
        }
    }

    public void fill(Pixel px, Color color, Color[][] matrix) {
        Pixel up = getUp(px, matrix);
        Pixel down = getDown(px, matrix);
        Pixel left = getLeft(px, matrix);
        Pixel right = getRight(px, matrix);
        matrix[px.row][px.col] = color;
        if (up != null) {
            fill(up, color, matrix);
        }

        if (down != null) {
            fill(down, color, matrix);
        }

        if (left != null) {
            fill(left, color, matrix);
        }

        if (right != null) {
            fill(right, color, matrix);
        }
    }

    private Pixel getUp(Pixel px, Color[][] matrix) {
        String pxColor = originalColor;
        if (px.row - 1 >= 0) {
            String upColor = matrix[px.row - 1][px.col].color;
            if (pxColor.equalsIgnoreCase(upColor)) {
                return new Pixel(px.row - 1, px.col);
            }
        }
        return null;
    }

    private Pixel getDown(Pixel px, Color[][] matrix) {
        String pxColor = originalColor;
        if (px.row + 1 <= matrix.length - 1) {
            String downColor = matrix[px.row + 1][px.col].color;
            if (pxColor.equalsIgnoreCase(downColor)) {
                return new Pixel(px.row + 1, px.col);
            }
        }
        return null;
    }

    private Pixel getLeft(Pixel px, Color[][] matrix) {
        String pxColor = originalColor;
        if (px.col - 1 >= 0) {
            String leftColor = matrix[px.row][px.col - 1].color;
            if (pxColor.equalsIgnoreCase(leftColor)) {
                return new Pixel(px.row, px.col - 1);
            }
        }
        return null;
    }

    private Pixel getRight(Pixel px, Color[][] matrix) {
        String pxColor = originalColor;
        if (px.col + 1 <= matrix[0].length - 1) {
            String rightColor = matrix[px.row][px.col + 1].color;
            if (pxColor.equalsIgnoreCase(rightColor)) {
                return new Pixel(px.row, px.col + 1);
            }
        }
        return null;
    }
}
class Color {
    String color;
    public Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
class Pixel {
    int row;
    int col;
    public Pixel(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
