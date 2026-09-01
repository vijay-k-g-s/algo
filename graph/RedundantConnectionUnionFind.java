package graph;

// LeetCode 684. Redundant Connection
// Uses Union-Find (DSU) to detect the first edge that creates a cycle.

//I use Union-Find to track connected components.
// Initially, every node is its own parent.
// For every edge, I check whether both endpoints already belong to the same component using find().
// If they do, adding this edge creates a cycle, so I return it.
// Otherwise, I union their components.
// Path compression and union by rank keep the operations efficient.

// https://www.youtube.com/watch?v=ayW5B2W9hfo

public class RedundantConnectionUnionFind {

    private int[] parent;
    private int[] rank;

    private void init(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    // Returns false if x and y are already in the same component (cycle detected)
    private boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return false;
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[py] = px;
            rank[px]++;
        }
        return true;
    }

    public int[] findRedundantConnection(int[][] edges) {
        init(edges.length);
        for (int[] edge : edges) {
            if (!union(edge[0], edge[1])) {
                return edge; // this edge connects two already-connected nodes → redundant
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        RedundantConnectionUnionFind solution = new RedundantConnectionUnionFind();

        int[][] edges1 = {{1, 2}, {1, 3}, {2, 3}};
        int[] result1 = solution.findRedundantConnection(edges1);
        System.out.println("[" + result1[0] + ", " + result1[1] + "]"); // [2, 3]

        int[][] edges2 = {{1, 2}, {1, 3}, {3, 4}, {2, 4}};
        int[] result2 = solution.findRedundantConnection(edges2);
        System.out.println("[" + result2[0] + ", " + result2[1] + "]"); // [2, 4]
    }
}
