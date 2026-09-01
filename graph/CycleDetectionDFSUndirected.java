package graph;

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
