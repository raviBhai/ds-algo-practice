package com.dsalgo.onlinetests;

import java.util.Iterator;
import java.util.LinkedList;

public class WaterlineData {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("val 1");
        list.add("val 2");
        list.add("random");
        Iterator<String> itr = list.descendingIterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
