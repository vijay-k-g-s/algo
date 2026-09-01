package graph;

import java.util.*;

/* "BFS traverses a adjList level by level using a queue.
I first mark the starting node as visited and push it into the queue.
Then, while the queue is not empty, I remove the front node, process it, and add all its unvisited neighbors to the queue after marking them visited.
 Marking nodes visited prevents revisiting the same node and avoids cycles.
 The overall time complexity is O(V + E) and the space complexity is O(V)." */


public class BFS {

    void bfs(Graph g, int start) {

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : g.getConnectedNodes(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Graph g = new Graph(5);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);

        BFS bfs = new BFS();
        System.out.print("BFS from node 0: ");
        bfs.bfs(g, 1);
    }
}
