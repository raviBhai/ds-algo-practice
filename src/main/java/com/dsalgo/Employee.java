package com.dsalgo;

import java.util.HashMap;
import java.util.Map;

public class Employee {

    String name;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}

class Client {
    public static void main(String[] args) {



        Map<String, String> map2 = new HashMap<>();
        map2.put("one", "ravi");
        map2.put("two", "lavi");
        map2.put("three", "mishti");

        for (Map.Entry<String, String> entry : map2.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

}
