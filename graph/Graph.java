package graph;

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
