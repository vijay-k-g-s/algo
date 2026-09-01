package graph;

import java.util.*;

// LeetCode 1584. Min Cost to Connect All Points
//
// Given points[i] = [xi, yi], return the minimum cost to connect all points.
// Cost between two points = Manhattan distance: |xi - xj| + |yi - yj|.
// This is a Minimum Spanning Tree (MST) problem on a complete graph.
//
// Two approaches shown:
//
// Approach 1: Prim's — compute edge costs on the fly (no graph built upfront)
//   - Space: O(V)  — only minCost[] and heap, no adjacency list
//   - Preferred for dense graphs
//
// Approach 2: Build weighted graph first, then run Prim's on adjacency list
//   - Space: O(V²) — stores all V*(V-1)/2 edges explicitly
//   - Same time complexity but higher memory; useful when graph is reused
//   - Graph class doesn't support weights, so use Map<Integer, List<int[]>>
//     where int[] = {neighbor, weight}, same pattern as NetworkDelayTimeDIJKSTRAS
//
// Complexity (both):
//   Time:  O(V² log V)
//   Space: O(V) vs O(V²)

public class MinCostConnectPointsPRIMS {

    // -----------------------------------------------------------------------
    // Approach 1: Prim's — edge costs computed on the fly (no graph built)
    // -----------------------------------------------------------------------
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[0] = 0;

        // Min-heap: [cost, node]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{0, 0});

        int totalCost = 0;

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int cost = curr[0], node = curr[1];

            if (visited[node]) continue;        // stale heap entry, skip
            visited[node] = true;
            totalCost += cost;

            for (int next = 0; next < n; next++) {
                if (!visited[next]) {
                    int dist = Math.abs(points[node][0] - points[next][0])
                             + Math.abs(points[node][1] - points[next][1]);
                    if (dist < minCost[next]) {
                        minCost[next] = dist;
                        minHeap.offer(new int[]{dist, next});
                    }
                }
            }
        }

        return totalCost;
    }



    public static void main(String[] args) {
        MinCostConnectPointsPRIMS solution = new MinCostConnectPointsPRIMS();

        int[][] t1 = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        int[][] t2 = {{3,12},{-2,5},{-4,1}};
        int[][] t3 = {{0,0},{1,1},{1,0},{-1,1}};

        System.out.println("--- Approach 1: on-the-fly ---");
        System.out.println(solution.minCostConnectPoints(t1));  // 20
        System.out.println(solution.minCostConnectPoints(t2));  // 18
        System.out.println(solution.minCostConnectPoints(t3));  // 4


    }
}
