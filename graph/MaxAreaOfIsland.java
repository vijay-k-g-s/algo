package graph;

import java.util.HashSet;
import java.util.Set;

// LeetCode 695. Max Area of Island
// Approach: Convert grid to Graph, then DFS each connected component
//           tracking its size. Return the largest.
//   - Each land cell ('1') becomes a node: id = r * cols + c
//   - Edges between adjacent land cells
//   - DFS returns the size of each island, track the max

public class MaxAreaOfIsland {

    private static final int[][] DIRS = {{1, 0}, {0, 1}}; // right + down only — avoids duplicate edges

    private Graph buildGraph(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Graph g = new Graph(rows * cols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    int node = r * cols + c;
                    g.adjList.putIfAbsent(node, new java.util.ArrayList<>());

                    for (int[] dir : DIRS) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                            g.addEdge(node, nr * cols + nc);
                        }
                    }
                }
            }
        }

        return g;
    }

    private int dfs(Graph g, int node, Set<Integer> visited) {
        visited.add(node);
        int area = 1;
        for (int neighbor : g.getConnectedNodes(node)) {
            if (!visited.contains(neighbor)) {
                area += dfs(g, neighbor, visited);
            }
        }
        return area;
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        Graph g = buildGraph(grid);
        Set<Integer> visited = new HashSet<>();
        int maxArea = 0;

        for (int node : g.adjList.keySet()) {
            if (!visited.contains(node)) {
                int area = dfs(g, node, visited);
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        MaxAreaOfIsland solution = new MaxAreaOfIsland();

        int[][] grid1 = {
            {0, 1, 1, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 1, 0}
        };
        System.out.println(solution.maxAreaOfIsland(grid1)); // Expected: 4

        int[][] grid2 = {
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 0, 1, 1}
        };
        System.out.println(solution.maxAreaOfIsland(grid2)); // Expected: 4

        int[][] grid3 = {{0, 0, 0, 0, 0}};
        System.out.println(solution.maxAreaOfIsland(grid3)); // Expected: 0
    }
}
