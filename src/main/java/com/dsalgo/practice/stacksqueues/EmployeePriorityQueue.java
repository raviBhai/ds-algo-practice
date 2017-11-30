package com.dsalgo.practice.stacksqueues;

class Employee implements Comparable<Employee> {
    int id;
    String name;

    public Employee() {}

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int compareTo(Employee o) {
        if (this.id > o.id) {
            return 1;
        } else if (this.id < o.id) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "id - " + id + " name - " + name;
    }
}

class Client {
    public static void main(String[] args) {
        Employee ravi = new Employee(3, "ravi");
        Employee lavi = new Employee(1, "lavi");
        Employee arpit = new Employee(2, "arpit");

        PriorityQueue<Employee> employeePriorityQueue = new PriorityQueue<Employee>(5);
        employeePriorityQueue.insert(ravi);
        employeePriorityQueue.insert(lavi);
        employeePriorityQueue.insert(arpit);

        System.out.println(employeePriorityQueue.size());
        System.out.println(employeePriorityQueue.isEmpty());
        System.out.println(employeePriorityQueue.isFull());

        System.out.println(employeePriorityQueue.peek());
        System.out.println(employeePriorityQueue.remove());

        System.out.println(employeePriorityQueue.size());

        employeePriorityQueue.insert(lavi);
        System.out.println(employeePriorityQueue.remove());
        System.out.println(employeePriorityQueue.remove());
        System.out.println(employeePriorityQueue.remove());

    }
}
