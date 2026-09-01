package graph;

// LeetCode 1971. Find if Path Exists in Graph
//
// Approach 1 — Union-Find (DSU):
//   - Initialize every node as its own parent.
//   - Union both endpoints of each edge.
//   - A valid path exists iff find(source) == find(destination).
//   Time:  O((V + E) α(V))   Space: O(V)
//
// Approach 2 — BFS:
//   - Build an adjacency list, then do a standard BFS from source.
//   - Return true as soon as destination is dequeued.
//   Time:  O(V + E)           Space: O(V + E)

import java.util.*;

public class FindIfPathExists {

    private int[] parent;
    private int[] rank;

    private void init(int n) {
        parent = new int[n];
        rank   = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    private void union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return;
        if      (rank[px] < rank[py]) parent[px] = py;
        else if (rank[px] > rank[py]) parent[py] = px;
        else { parent[py] = px; rank[px]++; }
    }

    // --- Approach 1: Union-Find ---

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        init(n);
        for (int[] edge : edges) union(edge[0], edge[1]);
        return find(source) == find(destination);
    }

    // --- Approach 2: BFS using Graph ---

    public boolean validPathBFS(int n, int[][] edges, int source, int destination) {
        Graph g = new Graph(n);
        for (int i = 0; i < n; i++) g.adjList.putIfAbsent(i, new ArrayList<>());
        for (int[] edge : edges) g.addEdge(edge[0], edge[1]);

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == destination) return true;
            for (int neighbor : g.getConnectedNodes(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FindIfPathExists solution = new FindIfPathExists();

        int[][] edges1 = {{0,1},{1,2},{2,0}};
        int[][] edges2 = {{0,1},{0,2},{3,5},{5,4},{4,3}};
        int[][] edges3 = {};

        System.out.println("--- Union-Find ---");
        System.out.println(solution.validPath(3, edges1, 0, 2)); // true
        System.out.println(solution.validPath(6, edges2, 0, 5)); // false
        System.out.println(solution.validPath(1, edges3, 0, 0)); // true

        System.out.println("--- BFS ---");
        System.out.println(solution.validPathBFS(3, edges1, 0, 2)); // true
        System.out.println(solution.validPathBFS(6, edges2, 0, 5)); // false
        System.out.println(solution.validPathBFS(1, edges3, 0, 0)); // true
    }
}
