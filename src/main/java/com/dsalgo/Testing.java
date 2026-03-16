package com.dsalgo;

import java.util.*;
import java.util.stream.Collectors;

public class Testing {

    public static void main(String[] args) {
       /* Map<String, String> map = Collections.emptyMap();
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
        }*/

        Map<String, String> map = new HashMap<>();
        map.put("a", "AAA");
        map.put("b", "BBB");

        List<cell> list = new ArrayList<>();
        list.add(new cell("c", "CCC"));
        list.add(new cell("d", "DDD"));

        List<String> ccgIds = list.stream().map(ccg -> map.get(ccg.s1)).collect(Collectors.toList());
        System.out.println(ccgIds);


    }


}


class cell {
    String s1;
    String s2;

    public cell(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }
}