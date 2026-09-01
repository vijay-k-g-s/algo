package graph;

import java.util.ArrayDeque;
import java.util.Queue;

// LeetCode 994. Rotting Oranges / NeetCode "Rotting Fruit"
//
// Algorithm: Multi-source BFS
//   1. Seed the queue with all rotten fruits (2), count all fresh fruits (1)
//   2. Early exit — if fresh == 0 from the start, return 0
//   3. BFS level by level — each full level = 1 minute.
//      For each rotten cell, rot adjacent fresh neighbors and decrement fresh
//   4. After BFS, if fresh > 0 some fruits were unreachable → return -1,
//      otherwise return minutes - 1 (the last BFS level incremented minutes
//      before finding no new neighbors)
//
// Key difference from Islands and Treasure:
//   Here we process the queue level by level (tracking size per iteration)
//   so we can count elapsed minutes, rather than writing distances into the grid.
//
// Complexity:
//   Time:  O(m x n) — each cell visited at most once
//   Space: O(m x n) — queue in worst case

/* “This is a multi-source BFS problem.
First, I add all initially rotten fruits to the queue and count the fresh fruits.
Starting with all rotten fruits at the same time ensures the rotting spreads simultaneously.
\Then I process the queue level by level, where each BFS level represents one minute.
For every rotten fruit, I check its four neighbors. If a neighbor is fresh,
I mark it rotten, decrease the fresh count, and add it to the queue.
At the end, if fresh fruits remain, they were unreachable, so I return -1; otherwise, I return the elapsed time.
The time complexity is O(rows × cols) and the space complexity is O(rows × cols).” */

public class RottingFruitMultiSourceBFS {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        int fresh = 0;

        // Count fresh fruits and seed queue with all rotten fruits
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) queue.offer(new int[]{r, c});
                else if (grid[r][c] == 1) fresh++;
            }
        }

        // No fresh fruits to rot — already done
        if (fresh == 0) return 0;

        int minutes = 0;

        // BFS level by level — each level is one minute
        while (!queue.isEmpty()) {
            int size = queue.size();
            minutes++;

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int r = cell[0], c = cell[1];

                for (int[] dir : DIRS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                    if (grid[nr][nc] != 1) continue; // skip: empty (0) or already rotten (2)

                    // convert 1 to 2
                    grid[nr][nc] = 2; // fresh fruit becomes rotten
                    fresh--;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        // If fresh fruits remain they are unreachable (blocked by empty cells)
        return fresh == 0 ? minutes - 1 : -1;
    }

    public static void main(String[] args) {
        RottingFruitMultiSourceBFS solution = new RottingFruitMultiSourceBFS();

        int[][] grid1 = {
            {1, 1, 0},
            {0, 1, 1},
            {0, 1, 2}
        };
        System.out.println(solution.orangesRotting(grid1)); // Expected: 4

        int[][] grid2 = {{0}};
        System.out.println(solution.orangesRotting(grid2)); // Expected: 0

        int[][] grid3 = {{1, 0, 1}};
        System.out.println(solution.orangesRotting(grid3)); // Expected: -1 (fresh fruit unreachable)
    }
}
