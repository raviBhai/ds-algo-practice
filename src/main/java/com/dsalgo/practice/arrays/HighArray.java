package com.dsalgo.practice.arrays;

public class HighArray {
    private long[] array;
    private int nElems;

    public HighArray(int size) {
        array = new long[size];
        nElems = 0;
    }

    //insert, find, delete, display

    public void insert(long data) {
        array[nElems] = data;
        nElems++;
    }

    public boolean find(long data) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (array[j] == data) {
                break;
            }
        }
        if (j == nElems) {
            return false;
        } else {
            return true;
        }
    }

    public boolean delete(long data) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (array[j] == data) {
                break;
            }
        }
        if (j == nElems) {
            return false;
        } else {
            for (int k = j; k < nElems; k++) {
                array[k] = array[k + 1];
            }
            nElems--;
            return true;
        }
    }

    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.println(array[i]);
        }
    }
}

class HighArrayClient {
    public static void main(String[] args) {
        HighArray highArray = new HighArray(5);

        //insert
        highArray.insert(10);
        highArray.insert(20);
        highArray.insert(30);
        highArray.insert(40);

        //display
        highArray.display();

        //find
        long searchKey = 20;
        if (highArray.find(searchKey)) {
            System.out.println("Found : " + searchKey);
        } else {
            System.out.println("Not Found : " + searchKey);
        }
        searchKey = 50;
        if (highArray.find(searchKey)) {
            System.out.println("Found : " + searchKey);
        } else {
            System.out.println("Not Found : " + searchKey);
        }

        //delete
        long deleteKey = 40;
        highArray.delete(deleteKey);
        System.out.println("Array after deleting : " + deleteKey);
        highArray.display();

/*
        deleteKey = 20;
        highArray.delete(deleteKey);
        System.out.println("Array after deleting : " + deleteKey);
        highArray.display();
*/
    }
}
