package greedy;

// Problem (LC 763): Given a string s, partition it into as many parts as possible
//          so that each letter appears in at most one part. Return a list of partition sizes.
// Example: s = "ababcbacadefegdehijhklij"
//          Output: [9, 7, 8]  ("ababcbaca", "defegde", "hijhklij")
//          s = "eccbbbbdec" → [10]
// Approach: Greedy.
//   1. Record the last occurrence index of each character.
//   2. Scan left to right. For each character, extend the current partition's
//      end to max(current end, lastOccurrence[char]).
//   3. When i reaches the current end → finalize partition, start new one.
// Time: O(n), Space: O(1) — 26-element array
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {

    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) last[s.charAt(i) - 'a'] = i;

        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PartitionLabels sol = new PartitionLabels();
        System.out.println(sol.partitionLabels("ababcbacadefegdehijhklij")); // [9,7,8]
        System.out.println(sol.partitionLabels("eccbbbbdec"));               // [10]
    }
}
