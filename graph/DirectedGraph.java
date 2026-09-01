package graph;

// Utility: Directed Graph — adjacency list representation.
//   addEdge(u, v) adds only u→v (directed; no reverse edge).
//   getConnectedNodes(u) returns all outgoing neighbors of u.
//   Node IDs are arbitrary integers; the map auto-initializes on first edge.
// Used by: TopologicalSort, CourseSchedule, CycleDetectionDirected, AlienDictionary.
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

class DirectedGraph {

    Map<Integer, List<Integer>> adjList;

    DirectedGraph(int v) {
        adjList = new HashMap<>();
    }

    void addEdge(int u, int v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(v); // one direction only
    }

    List<Integer> getConnectedNodes(int u) {
        return adjList.getOrDefault(u, new ArrayList<>());
    }
}
