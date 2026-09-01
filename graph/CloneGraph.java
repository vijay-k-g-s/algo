package graph;

import java.util.*;

// LeetCode 133. Clone Graph
// Approach: BFS + HashMap
//
//   The map (original → clone) serves two purposes:
//     1. Tracks visited nodes (a node is "visited" once it has a clone entry).
//     2. Lets us look up the clone of any neighbor when wiring edges.
//
//   Two-step loop:
//     Step 1 — create clone: when a neighbor is seen for the first time,
//              create its clone and enqueue the original for later processing.
//     Step 2 — wire neighbors: for the current node being processed,
//              add each neighbor's clone to the current node's clone neighbor list.
//
// Complexity:
//   Time:  O(V + E) — each node and edge visited once
//   Space: O(V)     — HashMap + queue

public class CloneGraph {

    static class Node {
        int val;
        List<Node> neighbors;

        Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> cloneMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        cloneMap.put(node, new Node(node.val));
        queue.add(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            Node currClone = cloneMap.get(curr);
            for (Node neighbor : curr.neighbors) {
                if (!cloneMap.containsKey(neighbor)) {
                    cloneMap.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }
                Node neighborClone = cloneMap.get(neighbor);
                currClone.neighbors.add(neighborClone);
            }
        }

        return cloneMap.get(node);
    }

    public static void main(String[] args) {
        CloneGraph solution = new CloneGraph();

        // Build: 1 -- 2
        //        |    |
        //        4 -- 3
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.neighbors.add(n2); n1.neighbors.add(n4);
        n2.neighbors.add(n1); n2.neighbors.add(n3);
        n3.neighbors.add(n2); n3.neighbors.add(n4);
        n4.neighbors.add(n1); n4.neighbors.add(n3);

        Node cloned = solution.cloneGraph(n1);

        // Verify deep copy — different object references, same structure
        System.out.println("Original n1: " + System.identityHashCode(n1));
        System.out.println("Cloned  n1: " + System.identityHashCode(cloned));
        System.out.println("Same object? " + (n1 == cloned));               // false
        System.out.println("Same val?    " + (n1.val == cloned.val));        // true
        System.out.println("Neighbor count: " + cloned.neighbors.size());    // 2
    }
}
