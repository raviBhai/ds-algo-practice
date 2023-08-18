package com.dsalgo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * The Main class implements an application that reads lines from the standard input
 * and prints them to the standard output.
 */
public class Main {
    /**
     * Iterate through each line of input.
     */
    private static final String LINK = "->";
    private static final String COMMA = ",";
    private static final Map<String, SinglyLinkedList> nodeMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            solve(line);
        }
    }

    private static void solve(String line) throws Exception {
        if (line.contains(LINK)) {
            String[] nodes = line.split(LINK);
            String node1 = nodes[0];
            String node2 = nodes[1];
            if (!nodeMap.containsKey(node1)) {
                SinglyLinkedList node = new SinglyLinkedList(node1);
                nodeMap.put(node1, node);
            }
            if (!nodeMap.containsKey(node2)) {
                SinglyLinkedList node = new SinglyLinkedList(node2);
                nodeMap.put(node2, node);
            }
            nodeMap.get(node1).next = nodeMap.get(node2);
        } else {
            String[] startNodes = line.split(COMMA);

            List<SinglyLinkedList> toBeTested = new ArrayList<>();
            for (String startNode : startNodes) {
                toBeTested.add(nodeMap.get(startNode));
            }
            if (DoLinkedListsIntersect(toBeTested)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }

        }
    }

    private static boolean DoLinkedListsIntersect(List<SinglyLinkedList> toBeTested) throws Exception {
        Set<String> nodeIndexes = new HashSet<>();
        for (SinglyLinkedList node : toBeTested) {
            if (isLoop(node)) {
                throw new Exception("Loop present");
            } else {
                nodeIndexes.add(node.value);
            }
        }

        Set<String> uniqueNodes = new HashSet<>();

        Deque<SinglyLinkedList> dq = new LinkedList<>();
        for (SinglyLinkedList node : toBeTested) {
            dq.addLast(node);
        }

        while (!dq.isEmpty()) {
            SinglyLinkedList removedNode = dq.removeFirst();
            if (!uniqueNodes.contains(removedNode.value)) {
                uniqueNodes.add(removedNode.value);
                if (removedNode.next != null) {
                    dq.add(removedNode.next);
                }
            } else {
                return true;
            }
        }

        double i = 1;
        double j = 1;
        double k = Math.sqrt(i+j);

       /* Set<String> lastNodes = new HashSet<>();
        for (SinglyLinkedList node : toBeTested) {
            node = node.next;
            SinglyLinkedList lastNode = null;
            while (node != null) {
                if (nodeIndexes.contains(node.value)) {
                    return true;
                }
                lastNode = node;
                node = node.next;
            }
            if (lastNode != null ) {
                if (lastNodes.contains(lastNode.value)) {
                    return true;
                } else {
                    lastNodes.add(lastNode.value);
                }
            }
        }
*/
        return false;
    }

    private static boolean isLoop(SinglyLinkedList node) {
        SinglyLinkedList slow = node, fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}

class SinglyLinkedList {
    String value;
    SinglyLinkedList next;

    SinglyLinkedList(String value) {
        this.value = value;
    }
}