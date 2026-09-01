package graph;

// Problem (LC 547): There are n cities. isConnected[i][j] = 1 means city i and
//          city j are directly connected. A province is a group of directly or
//          indirectly connected cities. Return the total number of provinces.
// Example: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//          Output: 2  (cities 0 & 1 form one province; city 2 is its own)
//          isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//          Output: 3  (no connections — each city is its own province)
// Approach: Convert the adjacency matrix to a Graph, then count connected
//   components via DFS. Each DFS call from an unvisited node discovers one
//   full province. Similar to NumberOfIslands but on an adjacency matrix.
// Time: O(n²), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.HashSet;
import java.util.Set;

public class NumberOfProvinces {

    private Graph buildGraph(int[][] isConnected) {
        int n = isConnected.length;
        Graph g = new Graph(n);
        for (int i = 0; i < n; i++) {
            g.adjList.putIfAbsent(i, new java.util.ArrayList<>());
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    g.addEdge(i, j);
                }
            }
        }
        System.out.println(g.adjList);
        return g;
    }

    private void dfs(Graph g, int city, Set<Integer> visited) {
        visited.add(city);
        for (int neighbor : g.getConnectedNodes(city)) {
            if (!visited.contains(neighbor)) {
                dfs(g, neighbor, visited);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        Graph g = buildGraph(isConnected);
        Set<Integer> visited = new HashSet<>();
        int provinces = 0;

        for (int city : g.adjList.keySet()) {
            if (!visited.contains(city)) {
                dfs(g, city, visited);
                provinces++;
            }
        }
        return provinces;
    }

    public static void main(String[] args) {
        NumberOfProvinces solution = new NumberOfProvinces();

        int[][] isConnected1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution.findCircleNum(isConnected1)); // 2

        int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(solution.findCircleNum(isConnected2)); // 3
    }
}
