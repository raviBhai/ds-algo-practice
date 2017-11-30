package com.dsalgo.practice.arrays;

public class OrderedArray {
    private long[] array;
    private int nElems;

    public OrderedArray(int size) {
        this.array = new long[size];
        this.nElems = 0;
    }

    //size, insert, find, delete, display

    public int size() {
        return nElems;
    }

    public void insert(long data) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (array[j] > data) {
                break;
            }
        }
        for (int k = nElems; k > j; k--) {
            array[k] = array[k - 1];
        }
        array[j] = data;
        nElems++;
    }

    public int find(long data) {
        int lowerIndex = 0;
        int upperIndex = nElems - 1;
        int currentIndex;
        while (true) {
            currentIndex = (lowerIndex + upperIndex) / 2;
            if (array[currentIndex] == data) {
                return currentIndex;
            } else if (lowerIndex > currentIndex) {
                return nElems;
            } else {
                if (array[currentIndex] > data) {
                    upperIndex = currentIndex - 1;
                } else {
                    lowerIndex = currentIndex + 1;
                }
            }
        }
    }

    public boolean delete(long data) {
        int index = find(data);
        if (index == nElems) {
            return false;
        } else {
            for (int k = index; k < nElems; k++) {
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

class OrderedArrayClient {
    public static void main(String[] args) {
        OrderedArray orderedArray = new OrderedArray(10);
        orderedArray.insert(10);
        orderedArray.insert(20);
        orderedArray.insert(30);
        orderedArray.insert(40);
        orderedArray.insert(50);

        System.out.println("Array size is : " + orderedArray.size());
        orderedArray.display();

        long searchKey = 20;
        int searchKeyIndex = orderedArray.find(searchKey);
        System.out.println(searchKeyIndex == orderedArray.size() ? searchKey + " not found" : searchKey + " found at index " + searchKeyIndex);

        searchKey = 60;
        searchKeyIndex = orderedArray.find(searchKey);
        System.out.println(searchKeyIndex == orderedArray.size() ? searchKey + " not found" : searchKey + " found at index " + searchKeyIndex);

        System.out.println("Inserting...");
        orderedArray.insert(25);
        System.out.println("Array size is : " + orderedArray.size());
        orderedArray.display();

        orderedArray.delete(30);
        orderedArray.delete(55);
        System.out.println("Array size is : " + orderedArray.size());
        orderedArray.display();
    }
}
