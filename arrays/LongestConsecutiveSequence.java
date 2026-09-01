package arrays;

// Problem (LC 128): Given an unsorted array of integers, return the length of
//          the longest consecutive elements sequence. Must run in O(n).
// Example: nums = [100, 4, 200, 1, 3, 2] → 4  (sequence: 1, 2, 3, 4)
//          nums = [0, 3, 7, 2, 5, 8, 4, 6, 0, 1] → 9  (sequence: 0-8)
// Approach: Add all numbers to a HashSet for O(1) lookup.
//   For each number n, only start counting if (n-1) is NOT in the set
//   (i.e., n is the beginning of a sequence).
//   Then expand: count how many consecutive numbers (n, n+1, n+2, ...) exist.
//   Track max length seen.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);

        int maxLen = 0;
        for (int n : set) {
            if (set.contains(n - 1)) continue; // not the start of a sequence
            int len = 1;
            while (set.contains(n + len)) len++;
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence sol = new LongestConsecutiveSequence();
        System.out.println(sol.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));        // 4
        System.out.println(sol.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1})); // 9
        System.out.println(sol.longestConsecutive(new int[]{}));                             // 0
    }
}
