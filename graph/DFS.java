package graph;

import java.util.*;

/* "DFS explores one path as deep as possible before backtracking.
I use recursion, where the call stack naturally keeps track of the path.
For each node, I first mark it as visited, process it, and then recursively visit each unvisited neighbor.
The visited set prevents revisiting nodes and avoids infinite loops in cyclic graphs.
The time complexity is O(V + E) since every vertex and edge is visited once, and the space complexity is O(V) due to the recursion stack and visited set." */


public class DFS {

    void dfs(Graph g, int node, Set<Integer> visited) {

        visited.add(node);
        System.out.print(node + " ");

        for (int neighbor : g.getConnectedNodes(node)) {
            if (!visited.contains(neighbor)) {
                dfs(g, neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {

        Graph g = new Graph(5);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);

        DFS dfs = new DFS();

        Set<Integer> visited = new HashSet<>();
        dfs.dfs(g, 0, visited);
    }
}
