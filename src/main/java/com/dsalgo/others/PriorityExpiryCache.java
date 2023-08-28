package com.dsalgo.others;

import java.util.*;

class Node {
    int key;
    int value;
    int priority;
    int expiry;
    Node prev;
    Node next;

    Node(int key, int value, int p, int e) {
        this.key = key;
        this.value = value;
        this.priority = p;
        this.expiry = e;
    }
}

public class PriorityExpiryCache {

    Map<Integer, Node> map = new HashMap<>();
    Node head;
    Node tail;
    int maxSize = 5;

    TreeMap<Node, Node> expiryTM = new TreeMap<>((o1, o2) -> {
        return o1.expiry - o2.expiry;
    });

    TreeMap<Node, Node> priorityTM = new TreeMap<>((o1, o2) -> {
        return o1.priority - o2.priority;
    });

    public void set(int key, int value, int priority, int expiry) {
        if (map.containsKey(key)) {
            Node node = map.get(key);

            expiryTM.remove(node);
            priorityTM.remove(node);

            node.value = value;
            node.priority = priority;
            node.expiry = expiry;

            expiryTM.put(node, node);
            priorityTM.put(node, node);

            removeFromList(node);
            addToLast(node);
        } else {

            if (map.size() >= maxSize) {
                boolean isEvicted = evictItem((int) System.currentTimeMillis());
                if (!isEvicted) {
                    isEvicted = removeLeastPriority();
                    if (!isEvicted) {
                        map.remove(head.key);
                        removeFromList(head);
                        expiryTM.remove(head);
                        priorityTM.remove(head);
                    }
                }
            }

            Node node = new Node(key, value, priority, expiry);
            map.put(key, node);
            addToLast(node);
            expiryTM.put(node, node);
            priorityTM.put(node, node);
        }
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeFromList(node);
            addToLast(node);
            return node.value;
        }
        return -1;
    }

    private boolean removeLeastPriority() {
        int count = 0;
        int p1 = -1, p2 = -1;
        Node toBeRemoved = null;
        for (Node node : priorityTM.keySet()) {
            if (count == 0) {
                toBeRemoved = node;
                p1 = node.priority;
            }
            if (count == 1) {
                p2 = node.priority;
            }
            count++;

            if (count == 2) {
                break;
            }
        }
        if (p1 == p2) {
            return false;
        }

        priorityTM.remove(toBeRemoved);
        expiryTM.remove(toBeRemoved);
        map.remove(toBeRemoved.key);
        removeFromList(toBeRemoved);
        return true;
    }

    public boolean evictItem(int currentTime) {
        Node firstNode = null;
        for (Node node : expiryTM.keySet()) {
            firstNode = node;
            break;
        }
        if (firstNode != null && currentTime > firstNode.expiry) {
            expiryTM.remove(firstNode);
            priorityTM.remove(firstNode);
            map.remove(firstNode.key);
            removeFromList(firstNode);
            return true;
        }
        return false;
    }

    private void removeFromList(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

    }

    private void addToLast(Node node) {
        if (head == null && tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = tail.next;
        }

    }

}
