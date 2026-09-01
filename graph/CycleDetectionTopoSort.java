package graph;

import java.util.*;

// Khan Algorithm uses BFS

/* "Kahn's Algorithm detects a cycle by repeatedly removing nodes that have an in-degree of 0,
meaning no incoming edges. First, I calculate the in-degree of every node and add all nodes with in-degree 0 to a queue.
Then I process each node, reducing the in-degree of its neighbors. Whenever a neighbor's in-degree becomes 0, I add it to the queue.
At the end, if I've processed all the nodes, the adjList is acyclic.
If some nodes remain unprocessed, they must be part of a cycle because their in-degree never became 0.
The time complexity is O(V + E) and the space complexity is O(V)." */


public class CycleDetectionTopoSort {

    boolean hasCycle(DirectedGraph g) {

        // Calculate in-degree of each node
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int node : g.adjList.keySet()) {
            inDegree.put(node, 0);
        }
        for (int node : g.adjList.keySet()) {
            for (int neighbor : g.getConnectedNodes(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        // Add all nodes with in-degree 0 to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }

        int visited = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;

            for (int neighbor : g.getConnectedNodes(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If not all nodes were visited, there is a cycle
        return visited != g.adjList.size();
    }

    public static void main(String[] args) {

        // No cycle: 0 -> 1 -> 2 -> 3
        DirectedGraph g1 = new DirectedGraph(4);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);

        // Cycle: 0 -> 1 -> 2 -> 0
        DirectedGraph g2 = new DirectedGraph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 0);

        CycleDetectionTopoSort detector = new CycleDetectionTopoSort();
        System.out.println("Graph 1 has cycle: " + detector.hasCycle(g1));
        System.out.println("Graph 2 has cycle: " + detector.hasCycle(g2));
    }
}
