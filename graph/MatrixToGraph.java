package graph;

// Utility: Convert an Adjacency Matrix to an undirected Graph (adjacency list).
//   matrix[i][j] = 1 means node i and node j are connected.
//   The matrix is symmetric (undirected), so only the upper triangle is processed.
//   Node IDs are 0-indexed row/column numbers.
// Example: matrix = [[1,1,0],    →  0 → [1]
//                    [1,1,0],    →  1 → [0]
//                    [0,0,1]]    →  2 → []
// Approach: Iterate i from 0..n-1, j from i+1..n-1 (upper triangle only).
//   If matrix[i][j] == 1, call g.addEdge(i, j) which adds both directions.
// Used by: NumberOfProvinces (isConnected matrix input).
// Time: O(n²), Space: O(n + E)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;

/* matrix = {
    {1, 1, 0},
    {1, 1, 0},
    {0, 0, 1}
  }

  Read it as a table: row = "from node", column = "to node"

       node0  node1  node2
  node0 [ 1     1     0 ]   → node0 connects to node1
  node1 [ 1     1     0 ]   → node1 connects to node0
  node2 [ 0     0     1 ]   → node2 connects to nothing

  matrix[0][1] == 1 means node0 — node1 are connected.
  The 3 nodes are 0, 1, 2. The values tell you edges.

  ---
* */

public class MatrixToGraph {

    Graph toGraph(int[][] matrix) {

        int n = matrix.length;
        Graph g = new Graph(n);
// Convert 2D to Undirected Graph
        for (int i = 0; i < n; i++) {
            g.adjList.putIfAbsent(i,new ArrayList<>());
            for (int j = i+1; j < n; j++) {
                if (matrix[i][j] == 1) {
                        g.addEdge(i, j);
                }
            }
        }

        return g;
    }

    public static void main(String[] args) {

//        int[][] matrix = {
//            {0, 1, 1, 0, 0},
//            {1, 0, 0, 1, 0},
//            {1, 0, 0, 0, 1},
//            {0, 1, 0, 0, 0},
//            {0, 0, 1, 0, 0}
//        };

       int[][] matrix = {{1,1,0},{1,1,0},{0,0,1}};

        MatrixToGraph converter = new MatrixToGraph();
        Graph g = converter.toGraph(matrix);

        System.out.println("Adjacency List:");
        for (int node : g.adjList.keySet()) {
            System.out.println(node + " -> " + g.getConnectedNodes(node));
        }
    }
}
