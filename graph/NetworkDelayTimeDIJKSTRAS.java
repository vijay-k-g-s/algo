package graph;

import java.util.*;

// LeetCode 743. Network Delay Time
//
// Given a directed weighted graph of n nodes and times[i] = [u, v, w]
// (edge from u to v with weight w), find the minimum time for all nodes
// to receive a signal sent from source k.
// Return -1 if any node is unreachable.
//
// Approach: Dijkstra's Algorithm (single source shortest path)
//   1. Build weighted adjacency list from times[]
//   2. Min-heap (priority queue) ordered by distance
//   3. Greedily pick the closest unvisited node, relax its neighbors
//   4. Answer = max of all shortest distances (slowest node to receive signal)
//
// Complexity:
//   Time:  O((V + E) log V) — each node/edge processed once via heap
//   Space: O(V + E)

public class NetworkDelayTimeDIJKSTRAS {

    private Map<Integer, List<int[]>> buildGraph(int[][] times, int n) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int i = 1; i <= n; i++) adj.put(i, new ArrayList<>());
        for (int[] t : times) {
            adj.get(t[0]).add(new int[]{t[1], t[2]}); // [neighbor, weight]
        }
        System.out.println("Graph: " + adj);
        return adj;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adj = buildGraph(times, n);

        // Min-heap: [distance, node]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Map<Integer, Integer> dist = new HashMap<>();

        minHeap.offer(new int[]{0, k});                     // start from source k with dist 0

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int d = curr[0], node = curr[1];

            if (dist.containsKey(node)) continue;           // already finalized
            dist.put(node, d);

            for (int[] neighbor : adj.get(node)) {
                int next = neighbor[0], weight = neighbor[1];
                if (!dist.containsKey(next)) {
                    minHeap.offer(new int[]{d + weight, next});
                }
            }
        }

        if (dist.size() != n) return -1;                    // some nodes unreachable

        return Collections.max(dist.values());              // slowest node = total delay
    }

    public static void main(String[] args) {
        NetworkDelayTimeDIJKSTRAS solution = new NetworkDelayTimeDIJKSTRAS();

        System.out.println(solution.networkDelayTime(
            new int[][]{{2,1,1},{2,3,1},{3,4,1}}, 4, 2));  // Expected: 2

        System.out.println(solution.networkDelayTime(
            new int[][]{{1,2,1}}, 2, 1));                   // Expected: 1

        System.out.println(solution.networkDelayTime(
            new int[][]{{1,2,1}}, 2, 2));                   // Expected: -1 (node 1 unreachable)
    }
}
