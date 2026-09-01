package graph;

// Topological Sort — Kahn's Algorithm (BFS).
//   A topological ordering of a Directed Acyclic Graph (DAG) is a linear ordering
//   of nodes such that for every directed edge u→v, u appears before v.
// Example: Edges: 5→2, 5→0, 4→0, 4→1, 2→3, 3→1
//          Valid order: [5, 4, 2, 0, 3, 1]  (or any valid linearization)
// Approach: Kahn's BFS — process nodes with no prerequisites first.
//   1. Compute in-degree for every node.
//   2. Enqueue all nodes with in-degree 0 (no dependencies).
//   3. Poll a node → add to result; for each neighbor decrement its in-degree.
//      If neighbor's in-degree reaches 0, enqueue it.
//   4. If result.size() < V, a cycle exists (used in CourseSchedule1 for detection).
// Time: O(V + E), Space: O(V + E)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class TopologicalSortBFS {

    List<Integer> topologicalSort(DirectedGraph g) {

        // Calculate in-degree of each node
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int node : g.adjList.keySet()) {
            inDegree.put(node, 0);
        }
        for (int node : g.adjList.keySet()) {
            for (int neighbor : g.getConnectedNodes(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        // Add all nodes with in-degree 0 to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor : g.getConnectedNodes(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
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

        TopologicalSortBFS ts = new TopologicalSortBFS();
        System.out.println("Topological Sort (BFS): " + ts.topologicalSort(g));
    }
}
