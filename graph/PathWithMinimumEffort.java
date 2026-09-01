package graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// LeetCode 1631. Path With Minimum Effort
//
// Find a path from (0,0) to (rows-1,cols-1) that minimizes the MAXIMUM
// absolute difference in heights between consecutive cells on the path.
//
// Approach: Modified Dijkstra
//   - Instead of minimizing total cost, minimize the max edge weight on the path.
//   - State in heap: [effort, row, col]
//     effort = max absolute height difference seen so far on this path.
//   - Transition: effort to neighbor = max(current effort, |heights[r][c] - heights[nr][nc]|)
//   - dist[r][c] = minimum effort to reach (r,c) — only update if new effort is smaller.
//
// Why Dijkstra works here:
//   Effort is monotonically non-decreasing along any path (max only grows),
//   so greedily picking the minimum-effort frontier node is always optimal.
//
// Complexity:
//   Time:  O(m * n * log(m * n))
//   Space: O(m * n)

public class PathWithMinimumEffort {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int minimumEffortPath(int[][] heights) {
        int cols = heights[0].length;
        int rows = heights.length;

        int[][] dist = new int[rows][cols];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;

        // Min-heap: [effort, row, col]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{0, 0, 0});

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int effort = curr[0], r = curr[1], c = curr[2];

            if (effort > dist[r][c]) continue;             // stale entry, skip

            if (r == rows - 1 && c == cols - 1) return effort; // reached destination

            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;

                int nextEffort = Math.max(effort, Math.abs(heights[r][c] - heights[nr][nc]));

                if (nextEffort < dist[nr][nc]) {
                    dist[nr][nc] = nextEffort;
                    minHeap.offer(new int[]{nextEffort, nr, nc});
                }
            }
        }

        return dist[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        PathWithMinimumEffort solution = new PathWithMinimumEffort();

        System.out.println(solution.minimumEffortPath(new int[][]{
            {1, 2, 2},
            {3, 8, 2},
            {5, 3, 5}
        })); // Expected: 2  (path: 1→3→5→3→5, max diff=2)

        System.out.println(solution.minimumEffortPath(new int[][]{
            {1, 2, 3},
            {3, 8, 4},
            {5, 3, 5}
        })); // Expected: 1  (path: 1→2→3→4→5, max diff=1)

        System.out.println(solution.minimumEffortPath(new int[][]{
            {1, 10, 6, 7, 9, 10, 4, 9}
        })); // Expected: 9
    }
}
