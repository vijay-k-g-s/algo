package graph;

import java.util.*;

// LeetCode 269. Alien Dictionary
//
// Given words sorted in alien lexicographic order, find the character ordering.
//
// Step 1 — Build Graph:
//   Compare each adjacent pair of words character by character.
//   First mismatch gives a directed edge: word1[i] → word2[i] (word1[i] comes before word2[i]).
//   Edge case: if word2 is a prefix of word1 (e.g. ["abc","ab"]) → invalid, return "".
//
// Step 2 — Topological Sort via DFS with 3-state cycle detection:
//   State 0 (WHITE) = unvisited
//   State 1 (GRAY)  = currently in DFS stack → if revisited, cycle detected
//   State 2 (BLACK) = fully processed
//
//   Post-order push to stack → reverse = topological order
//
// Complexity:
//   Time:  O(V + E) where V = unique chars, E = ordering constraints
//   Space: O(V + E)

public class AlienDictionary {

    private static final int WHITE = 0, GRAY = 1, BLACK = 2;

    private Map<Character, List<Character>> buildGraph(String[] words) {
        Map<Character, List<Character>> adj = new LinkedHashMap<>();

        // Add all unique characters as nodes
        for (String word : words) {
            for (char c : word.toCharArray()) {
                adj.putIfAbsent(c, new ArrayList<>());
            }
        }

        // Compare adjacent word pairs to extract ordering edges
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int minLen = Math.min(w1.length(), w2.length());

            // Invalid: w2 is prefix of w1 (e.g. ["abc", "ab"])
            if (w1.length() > w2.length() && w1.startsWith(w2)) return null;

            for (int j = 0; j < minLen; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    adj.get(w1.charAt(j)).add(w2.charAt(j)); // w1[j] comes before w2[j]
                    break;                                    // only first mismatch matters
                }
            }
        }

        System.out.println("Graph: " + adj);
        return adj;
    }

    // Returns false if cycle detected
    private boolean dfs(char node, Map<Character, List<Character>> adj,
                        Map<Character, Integer> state, Stack<Character> stack) {
        state.put(node, GRAY);                              // mark as visiting

        for (char neighbor : adj.get(node)) {
            if (state.get(neighbor) == GRAY) return false; // cycle detected
            if (state.get(neighbor) == WHITE) {
                if (!dfs(neighbor, adj, state, stack)) return false;
            }
        }

        state.put(node, BLACK);                             // mark as done
        stack.push(node);                                   // post-order
        return true;
    }

    public String alienOrder(String[] words) {
        Map<Character, List<Character>> adj = buildGraph(words);
        if (adj == null) return "";                         // invalid input

        Map<Character, Integer> state = new HashMap<>();
        for (char c : adj.keySet()) state.put(c, WHITE);

        Stack<Character> stack = new Stack<>();
        for (char c : adj.keySet()) {
            if (state.get(c) == WHITE) {
                if (!dfs(c, adj, state, stack)) return ""; // cycle → invalid
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());   // reverse post-order = topo order
        return sb.toString();
    }

    public static void main(String[] args) {
        AlienDictionary solution = new AlienDictionary();

        System.out.println(solution.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        // Expected: "wertf"

        System.out.println(solution.alienOrder(new String[]{"z", "x"}));
        // Expected: "zx"

        System.out.println(solution.alienOrder(new String[]{"z", "x", "z"}));
        // Expected: "" (cycle: z→x→z)

        System.out.println(solution.alienOrder(new String[]{"abc", "ab"}));
        // Expected: "" (invalid: longer word before its prefix)
    }
}
