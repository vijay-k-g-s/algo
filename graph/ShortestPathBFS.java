package graph;

import java.util.*;

// Shortest Path Between Two Nodes in an Undirected Graph (BFS)
//
// BFS guarantees the shortest path in an unweighted graph because it explores
// nodes level by level — the first time the target is reached, the path taken
// is the shortest one.
//
// Approach:
//   - Track each node's parent so the path can be reconstructed.
//   - Use a Map<Integer, Integer> parent where parent.get(node) = the node we
//     came from. The source maps to -1 as a sentinel.
//   - Once the target is dequeued (or found in neighbors), walk back through
//     parent pointers to reconstruct the path, then reverse it.
//   - Returns an empty list if no path exists (disconnected graph).
//
// Time:  O(V + E)  — standard BFS
// Space: O(V)      — visited set + parent map + queue

public class ShortestPathBFS {

    // Returns the shortest path from src to dst as a list of node IDs,
    // or an empty list if no path exists.
    public List<Integer> shortestPath(Graph g, int src, int dst) {
        if (src == dst) return List.of(src);

        Map<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        parent.put(src, -1);   // -1 = no parent (start node)
        queue.add(src);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : g.getConnectedNodes(node)) {
                if (!parent.containsKey(neighbor)) {
                    parent.put(neighbor, node);

                    if (neighbor == dst) {
                        return buildPath(parent, dst);
                    }

                    queue.add(neighbor);
                }
            }
        }

        return Collections.emptyList(); // no path found
    }

    private List<Integer> buildPath(Map<Integer, Integer> parent, int dst) {
        List<Integer> path = new ArrayList<>();
        int node = dst;
        while (node != -1) {
            path.add(node);
            node = parent.get(node);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        ShortestPathBFS solver = new ShortestPathBFS();

        // Graph:
        //   0 -- 1 -- 3
        //   |         |
        //   2 ------- 4 -- 5
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(2, 4);
        g.addEdge(4, 5);

        System.out.println(solver.shortestPath(g, 0, 5));  // [0, 2, 4, 5]
        System.out.println(solver.shortestPath(g, 0, 3));  // [0, 1, 3]
        System.out.println(solver.shortestPath(g, 3, 2));  // [3, 1, 0, 2] or [3, 4, 2]
        System.out.println(solver.shortestPath(g, 1, 1));  // [1]

        // Disconnected graph — no path between 0 and 6
        Graph g2 = new Graph(7);
        g2.addEdge(0, 1);
        g2.addEdge(6, 5);
        System.out.println(solver.shortestPath(g2, 0, 6)); // []
    }
}
