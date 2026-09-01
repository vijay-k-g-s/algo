package graph;

// Utility: Undirected Graph — adjacency list representation.
//   addEdge(u, v) adds both u→v and v→u (undirected).
//   getConnectedNodes(u) returns all neighbors of u.
//   Node IDs are arbitrary integers; the map auto-initializes on first edge.
// Used by: BFS, DFS, CycleDetection (undirected), NumberOfIslands, NumberOfProvinces, etc.
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

class Graph {

    Map<Integer, List<Integer>> adjList;

    Graph(int v) {
        adjList = new HashMap<>();
    }

    void addEdge(int u, int v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(v);
        adjList.get(v).add(u); // this makes it UNDIRECTED adjList
    }

    List<Integer> getConnectedNodes(int u) {
        return adjList.getOrDefault(u, new ArrayList<>());
    }
}
