package graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// LeetCode 778. Swim in Rising Water
//
// Given an n x n grid where grid[r][c] = elevation, at time t you can
// swim to any cell with elevation <= t. Swimming between cells is instant.
// Find the minimum time t to swim from (0,0) to (n-1,n-1).
//
// Key insight: The answer is the minimum possible value of the MAXIMUM
// elevation seen along any path from (0,0) to (n-1,n-1).
//
// Approach: Modified Dijkstra (same structure as PathWithMinimumEffort)
//   - State in heap: [time, row, col]
//     time = max elevation encountered so far on this path
//   - Transition: time to neighbor = max(current time, grid[nr][nc])
//   - dist[r][c] = minimum time (max elevation) to reach (r,c)
//
// Difference from PathWithMinimumEffort:
//   PathWithMinimumEffort → cost = max |height diff| between adjacent cells
//   SwimInRisingWater     → cost = max elevation of cells visited
//
// Complexity:
//   Time:  O(n^2 * log n)
//   Space: O(n^2)

public class SwimInRisingWater {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int swimInWater(int[][] grid) {
        int n = grid.length;

        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = grid[0][0];

        // Min-heap: [time, row, col]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{grid[0][0], 0, 0});

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int time = curr[0], r = curr[1], c = curr[2];

            if (time > dist[r][c]) continue;               // stale entry, skip

            if (r == n - 1 && c == n - 1) return time;    // reached destination

            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                int nextTime = Math.max(time, grid[nr][nc]); // wait until water rises to neighbor

                if (nextTime < dist[nr][nc]) {
                    dist[nr][nc] = nextTime;
                    minHeap.offer(new int[]{nextTime, nr, nc});
                }
            }
        }

        return dist[n - 1][n - 1];
    }

    public static void main(String[] args) {
        SwimInRisingWater solution = new SwimInRisingWater();

        System.out.println(solution.swimInWater(new int[][]{
            {0, 2},
            {1, 3}
        })); // Expected: 3  (path 0→1→3, max elevation=3)

        System.out.println(solution.swimInWater(new int[][]{
            {0, 1, 2, 3, 4},
            {24, 23, 22, 21, 5},
            {12, 13, 14, 15, 16},
            {11, 17, 18, 19, 20},
            {10,  9,  8,  7,  6}
        })); // Expected: 16
    }
}
