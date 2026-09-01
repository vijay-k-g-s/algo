package graph;

// Topological Sort — DFS Post-Order (using a Stack).
//   A topological ordering of a DAG ensures that for every edge u→v, u comes
//   before v. DFS post-order naturally produces reverse topological order:
//   a node is pushed to the stack only AFTER all its descendants are processed.
// Example: Edges: 5→2, 5→0, 4→0, 4→1, 2→3, 3→1
//          Valid order: [5, 4, 2, 3, 1, 0]  (or any valid linearization)
// Approach: DFS on every unvisited node.
//   After fully exploring a node's subtree (post-order), push it onto a stack.
//   Final result = stack popped in LIFO order = topological order.
//   A node pushed later in DFS means all its dependencies were already pushed first.
// Time: O(V + E), Space: O(V) stack + recursion
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class TopologicalSortDFS {

    void dfs(DirectedGraph g, int node, Set<Integer> visited, Stack<Integer> stack) {

        visited.add(node);

        for (int neighbor : g.getConnectedNodes(node)) {
            if (!visited.contains(neighbor)) {
                dfs(g, neighbor, visited, stack);
            }
        }

        stack.push(node);
    }

    List<Integer> topologicalSort(DirectedGraph g) {

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for (int node : g.adjList.keySet()) {
            if (!visited.contains(node)) {
                dfs(g, node, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {

        DirectedGraph g = new DirectedGraph(6);

        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        TopologicalSortDFS ts = new TopologicalSortDFS();
        System.out.println("Topological Sort: " + ts.topologicalSort(g));
    }
}
