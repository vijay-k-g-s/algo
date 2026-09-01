package dynamicprogramming;

// Problem (LC 329): Given an m×n matrix, return the length of the longest
//          strictly increasing path. From each cell, move in 4 directions.
//          Cannot move diagonally or outside the boundary.
// Example: matrix = [[9,9,4],[6,6,8],[2,1,1]] → 4  (path: 1→2→6→9)
//          matrix = [[3,4,5],[3,2,6],[2,2,1]]  → 4  (path: 3→4→5→6)
// Approach: DFS with memoization.
//   dp[r][c] = length of longest increasing path starting at (r,c).
//   For each cell, DFS to 4 neighbors with strictly greater value.
//   Memoize to avoid recomputation (each cell computed once).
// Time: O(m*n), Space: O(m*n)
//
// ─────────────────────────────────────────────────────────────────────────────

public class LongestIncreasingPathInMatrix {

    private static final int[] DR = {0, 0, 1, -1};
    private static final int[] DC = {1, -1, 0, 0};
    private int[][] memo;

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        memo = new int[m][n];
        int max = 0;
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                max = Math.max(max, dfs(matrix, r, c, m, n));
        return max;
    }

    private int dfs(int[][] matrix, int r, int c, int m, int n) {
        if (memo[r][c] != 0) return memo[r][c];
        int best = 1;
        for (int d = 0; d < 4; d++) {
            int nr = r + DR[d], nc = c + DC[d];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && matrix[nr][nc] > matrix[r][c]) {
                best = Math.max(best, 1 + dfs(matrix, nr, nc, m, n));
            }
        }
        return memo[r][c] = best;
    }

    public static void main(String[] args) {
        LongestIncreasingPathInMatrix sol = new LongestIncreasingPathInMatrix();
        System.out.println(sol.longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}})); // 4
        System.out.println(sol.longestIncreasingPath(new int[][]{{3,4,5},{3,2,6},{2,2,1}})); // 4
        System.out.println(sol.longestIncreasingPath(new int[][]{{1}}));                     // 1
    }
}
