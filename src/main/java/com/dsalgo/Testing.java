package com.dsalgo;

import java.util.*;

public class Testing {

    public static void main(String[] args) {
        Map<String, String> map = Collections.emptyMap();
       // map.put("a", "null");

        if (map == null) {
            System.out.println("null");
        }

        if (map.containsKey("a")) {
            System.out.println("contains a");
        } else {
            System.out.println("not contains a");
        }

        if (!map.containsKey("a")) {
            System.out.println("no a");
        }

    }


}
