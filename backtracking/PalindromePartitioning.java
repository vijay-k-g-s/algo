package backtracking;

// Problem (LC 131): Given a string s, partition it such that every substring
//          in the partition is a palindrome. Return all possible palindrome partitions.
// Example: s = "aab"
//          Output: [["a","a","b"],["aa","b"]]
//          s = "a" → [["a"]]
// Approach: Backtracking — at each step, try every possible prefix of the
//   remaining string. If the prefix is a palindrome, add it to the current
//   partition and recurse on the rest. Backtrack by removing it.
//   Pre-compute a 2D palindrome DP table for O(1) palindrome checks.
// Time: O(n * 2^n), Space: O(n²) for the DP table
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                isPalin[i][j] = s.charAt(i) == s.charAt(j)
                    && (j - i <= 2 || isPalin[i + 1][j - 1]);
            }
        }
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), isPalin, result);
        return result;
    }

    private void backtrack(String s, int start, List<String> current,
                           boolean[][] isPalin, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (isPalin[start][end]) {
                current.add(s.substring(start, end + 1));
                backtrack(s, end + 1, current, isPalin, result);
                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PalindromePartitioning sol = new PalindromePartitioning();
        System.out.println(sol.partition("aab")); // [[a,a,b],[aa,b]]
        System.out.println(sol.partition("a"));   // [[a]]
    }
}
