package graph;

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
