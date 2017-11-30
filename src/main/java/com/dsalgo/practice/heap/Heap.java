package com.dsalgo.practice.heap;

public class Heap {
    Node[] heapArray;
    int maxSize;
    int currentSize;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    public boolean insertMaxHeap(int data) {
        if (currentSize == maxSize) {
            return false;
        }
        Node newNode = new Node(data);
        heapArray[currentSize] = newNode;
        trickleUpMaxHeap(currentSize++);
        return true;
    }

    public boolean insertMinHeap(int data) {
        if (currentSize == maxSize) {
            return false;
        }
        Node newNode = new Node(data);
        heapArray[currentSize] = newNode;
        trickleUpMinHeap(currentSize++);
        return true;
    }

    public Node removeMaxHeap() {
        if (currentSize == 0) {
            return null;
        }
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDownMaxHeap(0);
        return root;
    }

    public Node removeMinHeap() {
        if (currentSize == 0) {
            return null;
        }
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDownMinHeap(0);
        return root;
    }

    private void trickleDownMaxHeap(int index) {
        Node top = heapArray[index];
        int largerChild;

        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize && heapArray[rightChild].data > heapArray[leftChild].data) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (top.data >= heapArray[largerChild].data) {
                break;
            } else {
                heapArray[index] = heapArray[largerChild];
                index = largerChild;
            }
        }
        heapArray[index] = top;
    }

    private void trickleDownMinHeap(int index) {
        Node top = heapArray[index];
        int smallerChild;

        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize && heapArray[rightChild].data < heapArray[leftChild].data) {
                smallerChild = rightChild;
            } else {
                smallerChild = leftChild;
            }

            if (top.data <= heapArray[smallerChild].data) {
                break;
            } else {
                heapArray[index] = heapArray[smallerChild];
                index = smallerChild;
            }
        }
        heapArray[index] = top;
    }

    private void trickleUpMaxHeap(int index) {
        int parent = (index - 1) / 2;
        Node bottom = heapArray[index];

        while (index > 0 && heapArray[parent].data < bottom.data) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    private void trickleUpMinHeap(int index) {
        int parent = (index - 1) / 2;
        Node bottom = heapArray[index];

        while (index > 0 && heapArray[parent].data > bottom.data) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }
}

class Node {
    public int data;

    public Node(int data) {
        this.data = data;
    }
}
