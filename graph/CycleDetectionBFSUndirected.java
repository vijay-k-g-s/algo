package graph;

import java.util.*;

/* To detect a cycle in an undirected adjList using BFS, I store both the current node and its parent in the queue. When I visit an unvisited neighbor, I mark it visited and add it with the current node as its parent. If I encounter an already visited neighbor that is not the current node’s parent, then there is another path to that node, which means a cycle exists. I repeat this from every unvisited node because the adjList may be disconnected. The time complexity is O(V + E) and the space complexity is O(V). */

public class CycleDetectionBFSUndirected {

    boolean hasCycleFromNode(Graph g, int start, Set<Integer> visited) {

        Queue<int[]> queue = new LinkedList<>(); // [node, parent]

        visited.add(start);
        queue.add(new int[]{start, -1});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int parent = curr[1];

            for (int neighbor : g.getConnectedNodes(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new int[]{neighbor, node});
                } else if (neighbor != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean hasCycle(Graph g) {

        Set<Integer> visited = new HashSet<>();

        for (int node : g.adjList.keySet()) {
            if (!visited.contains(node)) {
                if (hasCycleFromNode(g, node, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);

        Graph g2 = new Graph(4);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 0); // cycle

        CycleDetectionBFSUndirected detector = new CycleDetectionBFSUndirected();
        System.out.println("Graph 1 has cycle: " + detector.hasCycle(g1));
        System.out.println("Graph 2 has cycle: " + detector.hasCycle(g2));
    }
}
