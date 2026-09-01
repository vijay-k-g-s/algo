package arrays;

// Problem (LC 49): Given an array of strings, group the anagrams together.
//          Return the groups in any order.
// Example: strs = ["eat","tea","tan","ate","nat","bat"]
//          Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
//          strs = [""] → [[""]]
//          strs = ["a"] → [["a"]]
// Approach: For each string, sort its characters to get a canonical key.
//   All anagrams share the same sorted key. Group by key using a HashMap.
// Time: O(n * k log k) where k = max string length. Space: O(n * k)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams sol = new GroupAnagrams();
        System.out.println(sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        // [["bat"], ["nat","tan"], ["ate","eat","tea"]]
        System.out.println(sol.groupAnagrams(new String[]{""}));   // [[""]]
        System.out.println(sol.groupAnagrams(new String[]{"a"}));  // [["a"]]
    }
}
