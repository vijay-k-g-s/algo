package graph;

// Cycle Detection in an Undirected Graph using DFS.
//   In an undirected graph, a cycle exists when DFS reaches an already-visited
//   neighbor that is NOT the direct parent of the current node (a back edge).
// Example: Graph 1: 0-1-2-3-4 (no cycle) → false
//          Graph 2: 0-1-2-0   (cycle)     → true
// Approach: DFS with a `parent` parameter.
//   For each neighbor of the current node:
//     - If unvisited: recurse. If that returns true, a cycle was found.
//     - If visited AND not the parent: back edge detected → cycle exists.
//   Run from every unvisited node to handle disconnected graphs.
// Time: O(V + E), Space: O(V) recursion stack
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class CycleDetectionDFSUndirected {

    boolean hasCycleFromNode(Graph g, int node, int parent, Set<Integer> visited) {

        visited.add(node);

        for (int neighbor : g.getConnectedNodes(node)) {
            if (!visited.contains(neighbor)) {
                if (hasCycleFromNode(g, neighbor, node, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    boolean hasCycle(Graph g) {

        Set<Integer> visited = new HashSet<>();

        for (int node : g.adjList.keySet()) {
            if (!visited.contains(node)) {
                if (hasCycleFromNode(g, node, -1, visited)) {
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

        CycleDetectionDFSUndirected detector = new CycleDetectionDFSUndirected();
        System.out.println("Graph 1 has cycle: " + detector.hasCycle(g1));
        System.out.println("Graph 2 has cycle: " + detector.hasCycle(g2));
    }
}
