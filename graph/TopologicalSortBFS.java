package graph;

import java.util.*;

// Course Schedule 2
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
