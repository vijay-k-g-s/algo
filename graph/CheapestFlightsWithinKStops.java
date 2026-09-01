package graph;

import java.util.*;

// LeetCode 787. Cheapest Flights Within K Stops
//
// Two approaches:
//
// Approach 1: Bellman-Ford (K+1 iterations)
//   Relax all edges K+1 times (K stops = K+1 edges).
//   Use a temp[] snapshot each round to prevent chaining multiple edges in one pass.
//   Time: O(K * E)  Space: O(V)
//
// Approach 2: Dijkstra variation
//   Min-heap state: {cost, node, stops}.
//   Unlike standard Dijkstra, we cannot use a plain visited[] set because a node
//   reached with fewer stops (even at higher cost) may still yield a cheaper total path.
//   Instead, prune when stops > k. Since the heap is sorted by cost, the first time
//   dst is popped it is guaranteed to be the cheapest valid path.
//   Time: O(E * log(V * K))  Space: O(V * K)
//
// When to use which:
//   Bellman-Ford is simpler and preferred here because the stop constraint maps
//   directly onto iteration count. Dijkstra is useful when K is large and E is small.

public class CheapestFlightsWithinKStops {

    // Approach 1: Bellman-Ford
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(prices, n);
            for (int[] flight : flights) {
                int u = flight[0], v = flight[1], cost = flight[2];
                if (prices[u] == Integer.MAX_VALUE) continue;
                if (prices[u] + cost < temp[v]) temp[v] = prices[u] + cost;
            }
            prices = temp;
        }

        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

    // Approach 2: Dijkstra variation — min-heap on {cost, node, stops}
    public int findCheapestPriceDijkstra(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] flight : flights) {
            adj.computeIfAbsent(flight[0], x -> new ArrayList<>())
               .add(new int[]{flight[1], flight[2]});
        }

        // min-heap sorted by cost
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src, 0}); // {cost, node, stops}

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cost = curr[0], node = curr[1], stops = curr[2];

            if (node == dst) return cost;   // first pop = cheapest (min-heap guarantee)
            if (stops > k) continue;        // exceeded stop budget, prune

            for (int[] neighbor : adj.getOrDefault(node, Collections.emptyList())) {
                pq.offer(new int[]{cost + neighbor[1], neighbor[0], stops + 1});
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops solution = new CheapestFlightsWithinKStops();

        int[][] flights1 = {{0, 1, 100}, {1, 2, 100}, {2, 3, 100}, {0, 3, 500}};
        int[][] flights2 = {{0, 1, 1}, {1, 2, 1}};

        System.out.println("--- Bellman-Ford ---");
        System.out.println(solution.findCheapestPrice(4, flights1, 0, 3, 1)); // 500
        System.out.println(solution.findCheapestPrice(4, flights1, 0, 3, 2)); // 300
        System.out.println(solution.findCheapestPrice(3, flights2, 0, 2, 0)); // -1

        System.out.println("--- Dijkstra ---");
        System.out.println(solution.findCheapestPriceDijkstra(4, flights1, 0, 3, 1)); // 500
        System.out.println(solution.findCheapestPriceDijkstra(4, flights1, 0, 3, 2)); // 300
        System.out.println(solution.findCheapestPriceDijkstra(3, flights2, 0, 2, 0)); // -1
    }
}
