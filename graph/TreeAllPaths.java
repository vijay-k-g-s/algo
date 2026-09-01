package graph;

// Tree as a Graph — All Paths
//
// Represents a rooted tree as an undirected Graph and provides two traversals:
//
// Method 1 — rootToLeaves:
//   DFS from root, carrying the current path. A node is a leaf when all its
//   neighbors are its parent (i.e., no children). Print the path at each leaf.
//   Time: O(V * H)  — V nodes, path copy proportional to height H
//   Space: O(H)     — recursion stack + path list
//
// Method 2 — leafToAllLeaves:
//   First collect all leaves via DFS. Then, for each target leaf, run a DFS
//   from the source leaf tracking the path; backtrack if a dead end is reached.
//   Since it is a tree there is exactly one path between any two nodes.
//   Time: O(L * V)  — L leaves, each DFS is O(V)
//   Space: O(V)     — recursion stack + path list

import java.util.*;

public class TreeAllPaths {

    // --- Method 1: root to all leaves ---

    public void rootToLeaves(Graph g, int root) {
        dfsRoot(g, root, -1, new ArrayList<>());
    }

    private void dfsRoot(Graph g, int node, int parent, List<Integer> path) {
        path.add(node);

        boolean isLeaf = true;
        for (int neighbor : g.getConnectedNodes(node)) {
            if (neighbor != parent) {
                isLeaf = false;
                dfsRoot(g, neighbor, node, path);
            }
        }

        if (isLeaf) System.out.println(path);

        path.remove(path.size() - 1); // backtrack
    }

    // --- Method 2: one leaf to all other leaves ---

    public void leafToAllLeaves(Graph g, int root, int sourceLeaf) {
        List<Integer> leaves = new ArrayList<>();
        collectLeaves(g, root, -1, leaves);

        for (int targetLeaf : leaves) {
            if (targetLeaf == sourceLeaf) continue;
            List<Integer> path = new ArrayList<>();
            dfsLeafToLeaf(g, sourceLeaf, -1, targetLeaf, path);
            System.out.println(sourceLeaf + " -> " + targetLeaf + " : " + path);
        }
    }

    private void collectLeaves(Graph g, int node, int parent, List<Integer> leaves) {
        boolean isLeaf = true;
        for (int neighbor : g.getConnectedNodes(node)) {
            if (neighbor != parent) {
                isLeaf = false;
                collectLeaves(g, neighbor, node, leaves);
            }
        }
        if (isLeaf) leaves.add(node);
    }

    // Returns true when target is found; path holds the full route on success
    private boolean dfsLeafToLeaf(Graph g, int node, int parent, int target, List<Integer> path) {
        path.add(node);
        if (node == target) return true;

        for (int neighbor : g.getConnectedNodes(node)) {
            if (neighbor != parent) {
                if (dfsLeafToLeaf(g, neighbor, node, target, path)) return true;
            }
        }

        path.remove(path.size() - 1); // backtrack
        return false;
    }

    public static void main(String[] args) {
        // Tree:
        //        0
        //       / \
        //      1   2
        //     / \   \
        //    3   4   5
        //             \
        //              6
        Graph g = new Graph(7);
        for (int i = 0; i < 7; i++) g.adjList.putIfAbsent(i, new ArrayList<>());
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(5, 6);

        TreeAllPaths solution = new TreeAllPaths();

        System.out.println("--- All paths: root(0) to leaves ---");
        solution.rootToLeaves(g, 0);
        // [0, 1, 3]
        // [0, 1, 4]
        // [0, 2, 5, 6]

        System.out.println("\n--- Paths from leaf 3 to all other leaves ---");
        solution.leafToAllLeaves(g, 0, 3);
        // 3 -> 4 : [3, 1, 4]
        // 3 -> 6 : [3, 1, 0, 2, 5, 6]
    }
}
