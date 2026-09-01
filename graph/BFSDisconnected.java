package graph;

// BFS on a Disconnected (Multi-Component) Graph.
//   A single BFS from one start node only visits its connected component.
//   To visit ALL nodes in a graph that may have multiple disconnected components,
//   iterate over every node and start a fresh BFS for each unvisited one.
// Example: 7 nodes — components: {0,1,2,3}, {4,5}, {6}
//          Output:
//            Component: 0 1 2 3
//            Component: 4 5
//            Component: 6
// Approach: For each unvisited node, call bfsFromNode(). The shared `visited`
//   set ensures each node is processed exactly once across all BFS calls.
// Time: O(V + E), Space: O(V)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class BFSDisconnected {

    void bfsFromNode(Graph g, int start, Set<Integer> visited) {

        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : g.getConnectedNodes(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    void bfs(Graph g) {

        Set<Integer> visited = new HashSet<>();

        for (int node : g.adjList.keySet()) {
            if (!visited.contains(node)) {
                System.out.print("Component: ");
                bfsFromNode(g, node, visited);
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

        BFSDisconnected bfs = new BFSDisconnected();
        bfs.bfs(g);
    }
}
