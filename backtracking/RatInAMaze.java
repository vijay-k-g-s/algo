package backtracking;

// Problem (GFG classic): Given an n×n binary matrix where 1 = open cell and
//          0 = blocked cell, a rat starts at (0,0) and must reach (n-1, n-1).
//          Return all distinct paths as direction strings using D/U/R/L.
//          The rat cannot revisit a cell in the same path.
// Example: maze = [[1,0,0,0],   (only right and down are open enough)
//                  [1,1,0,1],
//                  [1,1,0,0],
//                  [0,1,1,1]]
//          Output: ["DDRDRR", "DRDDRR"]
//
//          maze = [[1,1],[1,1]]
//          Output: ["DR","RD"]
//
//          maze = [[1,0],[0,1]]
//          Output: []  (no valid path)
// Approach: 4-directional DFS (D, U, R, L) from (0,0).
//   Maintain a visited[][] to prevent cycles in the current path.
//   Append direction char, recurse, then remove and un-visit (backtrack).
//   Record path string when (n-1, n-1) is reached.
// Time: O(4^(n²)), Space: O(n²)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class RatInAMaze {

    private static final int[] DR = {1, -1, 0, 0};   // row deltas
    private static final int[] DC = {0, 0, 1, -1};   // col deltas
    private static final char[] DIR = {'D', 'U', 'R', 'L'};

    public List<String> findPaths(int[][] maze) {
        int n = maze.length;
        List<String> result = new ArrayList<>();
        if (maze[0][0] == 0) return result;
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        backtrack(maze, 0, 0, n, new StringBuilder(), visited, result);
        return result;
    }

    private void backtrack(int[][] maze, int row, int col, int n,
                           StringBuilder path, boolean[][] visited, List<String> result) {
        if (row == n - 1 && col == n - 1) {
            result.add(path.toString());
            return;
        }
        for (int d = 0; d < 4; d++) {
            int newRow = row + DR[d];
            int newCol = col + DC[d];
            if (isValid(maze, newRow, newCol, n, visited)) {
                visited[newRow][newCol] = true;
                path.append(DIR[d]);
                backtrack(maze, newRow, newCol, n, path, visited, result);
                path.deleteCharAt(path.length() - 1);
                visited[newRow][newCol] = false;
            }
        }
    }

    private boolean isValid(int[][] maze, int row, int col, int n, boolean[][] visited) {
        return row >= 0 && row < n && col >= 0 && col < n
                && maze[row][col] == 1 && !visited[row][col];
    }

    public static void main(String[] args) {
        RatInAMaze sol = new RatInAMaze();

        int[][] maze1 = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}
        };
        System.out.println("Maze 1 paths: " + sol.findPaths(maze1));

        int[][] maze2 = {
            {1, 1},
            {1, 1}
        };
        System.out.println("Maze 2 paths: " + sol.findPaths(maze2));

        int[][] maze3 = {
            {1, 0},
            {0, 1}
        };
        System.out.println("Maze 3 paths: " + sol.findPaths(maze3));
    }
}
