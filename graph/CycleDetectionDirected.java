package graph;

// Cycle Detection in a Directed Graph using DFS (3-color / recStack approach).
//   In a directed graph, a cycle exists when DFS finds a back edge — i.e., a
//   neighbor that is currently on the active recursion stack (GRAY state).
// Example: Graph 1: 0→1→2→3 (no cycle)  → false
//          Graph 2: 0→1→2→0 (cycle)      → true
// Approach: DFS with two sets — `visited` and `recStack`.
//   visited:  all nodes ever explored (WHITE → BLACK).
//   recStack: nodes on the current DFS path (GRAY = in progress).
//   If a neighbor is in recStack, a back edge is found → cycle.
//   Remove node from recStack when backtracking.
//   Run from every unvisited node to handle disconnected graphs.
// Time: O(V + E), Space: O(V)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class CycleDetectionDirected {

    /* To detect a cycle in a directed adjList,
    I use DFS along with two sets: visited and recStack. visited keeps track of all nodes we've already explored,
    while recStack keeps track of the nodes in the current DFS path.
    As I visit a node, I add it to both sets. If I reach an unvisited neighbor, I continue the DFS.
    If I encounter a node that's already in the recursion stack,
    it means I've found a back edge to an ancestor in the current path, which indicates a cycle. Once I'm done exploring a node,
    I remove it from the recursion stack before backtracking.
    The time complexity is O(V + E) and the space complexity is O(V). */

    boolean hasCycleFromNode(DirectedGraph g, int node, Set<Integer> visited, Set<Integer> recStack) {

        visited.add(node);
        recStack.add(node);

        for (int neighbor : g.getConnectedNodes(node)) {
            if (!visited.contains(neighbor)) {
                if (hasCycleFromNode(g, neighbor, visited, recStack)) {
                    return true;
                }
            } else if (recStack.contains(neighbor)) {
                return true;
            }
        }

        recStack.remove(node);
        return false;
    }

    boolean hasCycle(DirectedGraph g) {

        Set<Integer> visited = new HashSet<>();
        Set<Integer> recStack = new HashSet<>();

        for (int node : g.adjList.keySet()) {
            if (!visited.contains(node)) {
                if (hasCycleFromNode(g, node, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        // No cycle: 0 -> 1 -> 2 -> 3
        DirectedGraph g1 = new DirectedGraph(4);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);

        // Cycle: 0 -> 1 -> 2 -> 0
        DirectedGraph g2 = new DirectedGraph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 0);

        CycleDetectionDirected detector = new CycleDetectionDirected();
        System.out.println("Graph 1 has cycle: " + detector.hasCycle(g1));
        System.out.println("Graph 2 has cycle: " + detector.hasCycle(g2));
    }
}
