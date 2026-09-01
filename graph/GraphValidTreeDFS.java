package graph;

import java.util.HashSet;
import java.util.Set;

// LeetCode 261. Graph Valid Tree — DFS approach
//
// A valid tree must satisfy TWO conditions:
//   1. No cycle
//   2. Fully connected (all n nodes reachable)
//
// Shortcut: edges.length == n-1 is necessary but not sufficient alone
//           (could be disconnected with no cycles). Both checks are needed.
//
// DFS cycle detection (undirected graph):
//   - Track visited nodes and the parent of the current node.
//   - If we reach an already-visited node that is NOT the parent → cycle found.
//
// Complexity:
//   Time:  O(V + E)
//   Space: O(V + E)

public class GraphValidTreeDFS {

    private boolean hasCycle(Graph g, int node, int parent, Set<Integer> visited) {
        visited.add(node);
        for (int neighbor : g.getConnectedNodes(node)) {
            if (!visited.contains(neighbor)) {
                if (hasCycle(g, neighbor, node, visited)) return true;
            } else if (neighbor != parent) {               // visited and not parent → cycle
                return true;
            }
        }
        return false;
    }

    private Graph buildGraph(int n, int[][] edges) {
        Graph g = new Graph(n);
        for (int i = 0; i < n; i++) {
            g.adjList.putIfAbsent(i, new java.util.ArrayList<>());
        }
        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }
        return g;
    }

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;           // quick check: tree needs exactly n-1 edges

        Graph g = buildGraph(n, edges);
        Set<Integer> visited = new HashSet<>();

        if (hasCycle(g, 0, -1, visited)) return false;    // cycle detected

        return visited.size() == n;                        // all nodes connected
    }

    public static void main(String[] args) {
        GraphValidTreeDFS solution = new GraphValidTreeDFS();

        System.out.println(solution.validTree(5, new int[][]{{0,1},{0,2},{0,3},{1,4}}));          // true
        System.out.println(solution.validTree(5, new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}}));    // false (cycle)
        System.out.println(solution.validTree(1, new int[][]{}));                                 // true  (single node)
        System.out.println(solution.validTree(3, new int[][]{{0,1}}));                            // false (disconnected)
    }
}
