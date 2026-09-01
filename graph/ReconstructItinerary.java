package graph;

// Problem (LC 332): Given a list of airline tickets [from, to], reconstruct the
//          itinerary in order. The itinerary must begin with "JFK". If multiple
//          valid itineraries exist, return the lexicographically smallest one.
//          All tickets must be used exactly once.
// Example: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
//          Output: ["JFK","MUC","LHR","SFO","SJC"]
//          tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//          Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
// Approach: Hierholzer's algorithm for Eulerian path.
//   Build adjacency list with sorted destinations (PriorityQueue for lex order).
//   DFS: while current airport has unvisited destinations, visit them.
//   Append to result in post-order (when no more destinations) → reverse at end.
// Time: O(E log E), Space: O(E)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class ReconstructItinerary {

    private Map<String, PriorityQueue<String>> graph;
    private List<String> result;

    public List<String> findItinerary(List<List<String>> tickets) {
        graph = new HashMap<>();
        result = new LinkedList<>();

        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).offer(ticket.get(1));
        }

        dfs("JFK");
        return result;
    }

    private void dfs(String airport) {
        PriorityQueue<String> destinations = graph.getOrDefault(airport, new PriorityQueue<>());
        while (!destinations.isEmpty()) {
            dfs(destinations.poll());
        }
        ((LinkedList<String>) result).addFirst(airport); // post-order → prepend
    }

    public static void main(String[] args) {
        ReconstructItinerary sol = new ReconstructItinerary();
        List<List<String>> t1 = Arrays.asList(
            Arrays.asList("MUC","LHR"), Arrays.asList("JFK","MUC"),
            Arrays.asList("SFO","SJC"), Arrays.asList("LHR","SFO"));
        System.out.println(sol.findItinerary(t1));
        // [JFK, MUC, LHR, SFO, SJC]
    }
}
