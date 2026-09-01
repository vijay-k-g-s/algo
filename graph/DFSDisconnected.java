package graph;

import java.util.*;

public class DFSDisconnected {

    void dfsHelper(Graph g, int node, Set<Integer> visited) {

        visited.add(node);
        System.out.print(node + " ");

        for (int neighbor : g.getConnectedNodes(node)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(g, neighbor, visited);
            }
        }
    }

    void dfs(Graph g) {

        Set<Integer> visited = new HashSet<>();

        for (int node : g.adjList.keySet()) {
            if (!visited.contains(node)) {
                System.out.print("Component: ");
                dfsHelper(g, node, visited);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        Graph g = new Graph(7);

        // Component 1
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);

        // Component 2
        g.addEdge(4, 5);

        // Component 3 (isolated node)
        g.adjList.putIfAbsent(6, new ArrayList<>());

        DFSDisconnected dfs = new DFSDisconnected();
        dfs.dfs(g);
    }
}
