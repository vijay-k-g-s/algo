package graph;

import java.util.HashSet;
import java.util.Set;

// LeetCode 200. Number of Islands
// Approach: Convert grid to Graph, then count connected components via DFS.
//   - Each land cell ('1') becomes a node: id = r * cols + c
//   - Edges are added between horizontally/vertically adjacent land cells
//   - Each unvisited node triggers a DFS → one island found

public class NumberOfIslands {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private Graph buildGraph(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Graph g = new Graph(rows * cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    int node = r * cols + c;
                    g.adjList.putIfAbsent(node, new java.util.ArrayList<>());

                    for (int[] dir : DIRS) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '1') {
                            g.addEdge(node, nr * cols + nc);
                        }
                    }
                }
            }
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

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        Graph g = buildGraph(grid);
        Set<Integer> visited = new HashSet<>();
        int islands = 0;

        for (int node : g.adjList.keySet()) {
            if (!visited.contains(node)) {
                dfs(g, node, visited);
                islands++;
            }
        }

        return islands;
    }

    public static void main(String[] args) {
        NumberOfIslands solution = new NumberOfIslands();

        char[][] grid1 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        System.out.println(solution.numIslands(grid1)); // Expected: 1

        char[][] grid2 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println(solution.numIslands(grid2)); // Expected: 3
    }
}
