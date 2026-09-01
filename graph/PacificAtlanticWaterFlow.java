package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// LeetCode 417. Pacific Atlantic Water Flow
//
// Pacific ocean  → top row + left col
// Atlantic ocean → bottom row + right col
//
// Naive approach: From every cell check if it can reach both oceans → O((m*n)^2). Too slow.
//
// Approach: Reverse DFS from ocean borders (go uphill instead of downhill)
//   1. DFS from all Pacific border cells  — find every cell that can drain to Pacific.
//   2. DFS from all Atlantic border cells — find every cell that can drain to Atlantic.
//   3. Answer = intersection of both sets.
//
//   Reverse flow condition: move to neighbor only if neighbor height >= current height.
//
// Complexity:
//   Time:  O(m x n) — each cell visited at most twice
//   Space: O(m x n) — visited sets + recursion stack

public class PacificAtlanticWaterFlow {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void dfs(int[][] heights, int r, int c, int rows, int cols, Set<Integer> reachable) {
        int node = r * cols + c;
        reachable.add(node);

        for (int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            int neighbor = nr * cols + nc;
            if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
            if (reachable.contains(neighbor)) continue;
            if (heights[nr][nc] < heights[r][c]) continue;  // can't flow uphill
            dfs(heights, nr, nc, rows, cols, reachable);
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        Set<Integer> pacific  = new HashSet<>();
        Set<Integer> atlantic = new HashSet<>();

        // Seed Pacific: top row + left col
        for (int c = 0; c < cols; c++) dfs(heights, 0,        c, rows, cols, pacific);
        for (int r = 0; r < rows; r++) dfs(heights, r,        0, rows, cols, pacific);

        // Seed Atlantic: bottom row + right col
        for (int c = 0; c < cols; c++) dfs(heights, rows - 1, c, rows, cols, atlantic);
        for (int r = 0; r < rows; r++) dfs(heights, r,  cols - 1, rows, cols, atlantic);

        // Intersection — cells reachable from both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int node : pacific) {
            if (atlantic.contains(node)) {
                List<Integer> cell = new ArrayList<>();
                cell.add(node / cols);
                cell.add(node % cols);
                result.add(cell);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        PacificAtlanticWaterFlow solution = new PacificAtlanticWaterFlow();

        int[][] heights1 = {
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}
        };
        System.out.println(solution.pacificAtlantic(heights1));
        // Expected: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]

        int[][] heights2 = {{1}};
        System.out.println(solution.pacificAtlantic(heights2));
        // Expected: [[0,0]]
    }
}
