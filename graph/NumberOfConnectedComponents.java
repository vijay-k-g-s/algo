package graph;

import java.util.HashSet;
import java.util.Set;

// LeetCode 323. Number of Connected Components in an Undirected Graph
// Approach: Build Graph from edges, then count connected components via DFS.
//   - n nodes (0 to n-1), each added to adjList upfront
//   - Edges from input added as undirected
//   - Each unvisited node triggers a DFS → one component found
//
// Complexity:
//   Time:  O(V + E)
//   Space: O(V + E)

public class NumberOfConnectedComponents {

    private Graph buildGraph(int n, int[][] edges) {
        Graph g = new Graph(n);
        for (int i = 0; i < n; i++) {
            g.adjList.putIfAbsent(i, new java.util.ArrayList<>());
        }
        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }
        System.out.println(g.adjList);
        return g;
    }

    private void dfs(Graph g, int node, Set<Integer> visited) {
        visited.add(node);
        for (int neighbor : g.getConnectedNodes(node)) {
            if (!visited.contains(neighbor)) {
                dfs(g, neighbor, visited);
            }
        }
    }

    public int countComponents(int n, int[][] edges) {
        Graph g = buildGraph(n, edges);
        Set<Integer> visited = new HashSet<>();
        int components = 0;

        for (int node : g.adjList.keySet()) {
            if (!visited.contains(node)) {
                dfs(g, node, visited);
                components++;
            }
        }

        return components;
    }

    public static void main(String[] args) {
        NumberOfConnectedComponents solution = new NumberOfConnectedComponents();

        System.out.println(solution.countComponents(5, new int[][]{{0,1},{1,2},{3,4}}));       // Expected: 2
        System.out.println(solution.countComponents(5, new int[][]{{0,1},{1,2},{2,3},{3,4}})); // Expected: 1
        System.out.println(solution.countComponents(4, new int[][]{}));                        // Expected: 4
    }
}
