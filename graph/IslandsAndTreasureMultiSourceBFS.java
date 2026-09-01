package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

// LeetCode 1926 / NeetCode "Islands and Treasure"
// Multi-source BFS from all treasure chests (0s) simultaneously.
// Each land cell (INF) gets the shortest BFS distance to any chest.
// Water cells (-1) are never visited.
public class IslandsAndTreasureMultiSourceBFS {

    private static final int INF = 2147483647;
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void islandsAndTreasure(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();

        // Seed the queue with every treasure chest position
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) queue.offer(new int[]{r, c});
            }
        }

        // BFS outward — each level adds 1 to the distance
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                if (grid[nr][nc] != INF) continue; // skip: water (-1), treasure (0), or already-visited land (distance already set)

                grid[nr][nc] = grid[r][c] + 1;
                queue.offer(new int[]{nr, nc});
            }
        }
    }

    // ── helpers ──────────────────────────────────────────────────────────────

    private static void print(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        IslandsAndTreasureMultiSourceBFS solution = new IslandsAndTreasureMultiSourceBFS();

        int[][] grid = {
            {INF, -1,  0,  INF},
            {INF, INF, INF, -1},
            {INF, -1,  INF, -1},
            {0,   -1,  INF, INF}
        };

        System.out.println("Before:");
        print(grid);

        solution.islandsAndTreasure(grid);

        System.out.println("After:");
        print(grid);

        // Expected:
        // [3, -1, 0, 1]
        // [2, 2, 1, -1]
        // [1, -1, 2, -1]
        // [0, -1, 3, 4]
    }
}
