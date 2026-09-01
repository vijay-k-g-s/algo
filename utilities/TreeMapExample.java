package utilities;

// Utility: Demonstrates TreeMap — a sorted map backed by a Red-Black Tree.
//   Keys are always maintained in natural (ascending) order.
//   Operations: put, get, keySet (returns keys in sorted order).
// Key difference from HashMap:
//   HashMap  → O(1) average, unordered.
//   TreeMap  → O(log n) guaranteed, always sorted by key.
// Use TreeMap when you need ordered iteration or range queries (firstKey, lastKey, etc.).
// Example: put("Bob",30), put("Alice",25), put("Charlie",22)
//          keySet() → [Alice, Bob, Charlie]  (alphabetical)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();

        // Adding entries
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 22);

        // Retrieving values
        System.out.println("Age of Bob: " + map.get("Bob")); 

        // // Iterating over the map in sorted order
        // for (String key : map.keySet()) {
        //     System.out.println(key + ": " + map.get(key));
        // }

    
    }
}