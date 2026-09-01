package graph;

import java.util.*;

public class TopologicalSortDFS {

    void dfs(DirectedGraph g, int node, Set<Integer> visited, Stack<Integer> stack) {

        visited.add(node);

        for (int neighbor : g.getConnectedNodes(node)) {
            if (!visited.contains(neighbor)) {
                dfs(g, neighbor, visited, stack);
            }
        }

        stack.push(node);
    }

    List<Integer> topologicalSort(DirectedGraph g) {

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for (int node : g.adjList.keySet()) {
            if (!visited.contains(node)) {
                dfs(g, node, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
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

        TopologicalSortDFS ts = new TopologicalSortDFS();
        System.out.println("Topological Sort: " + ts.topologicalSort(g));
    }
}
