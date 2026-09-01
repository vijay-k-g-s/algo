package graph;

// LeetCode 261. Graph Valid Tree
// A valid tree must have exactly n-1 edges and no cycle (which also guarantees full connectivity).
public class GraphValidTreeUnionFind {

    private int[] parent;
    private int[] rank;

    private void init(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
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

    public boolean validTree(int n, int[][] edges) {
        // A tree with n nodes must have exactly n-1 edges
        if (edges.length != n - 1) return false;

        init(n);

        // If any edge creates a cycle, it's not a tree
        for (int[] edge : edges) {
            if (!union(edge[0], edge[1])) return false;
        }

        return true; // n-1 edges + no cycle → fully connected tree
    }

    public static void main(String[] args) {
        GraphValidTreeUnionFind solution = new GraphValidTreeUnionFind();

        System.out.println(solution.validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}));       // true
        System.out.println(solution.validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}})); // false
        System.out.println(solution.validTree(1, new int[][]{}));                                        // true  (single node, no edges)
        System.out.println(solution.validTree(3, new int[][]{{0, 1}}));                                  // false (disconnected)
    }
}
