package graph;

import java.util.HashSet;
import java.util.Set;

// LeetCode 547. Number of Provinces
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
