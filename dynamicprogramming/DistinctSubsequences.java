package dynamicprogramming;

// Problem (LC 115): Given strings s and t, return the number of distinct
//          subsequences of s that equal t.
// Example: s = "rabbbit", t = "rabbit" → 3
//          (pick the 3rd, 4th, or 5th 'b' to omit)
//          s = "babgbag", t = "bag" → 5
// Approach: 2D DP.
//   dp[i][j] = number of ways to form t[0..j-1] using s[0..i-1].
//   dp[i][0] = 1 for all i (empty t is a subsequence of any prefix of s).
//   If s[i-1] == t[j-1]: dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
//     (use s[i-1] to match, or skip s[i-1])
//   Else: dp[i][j] = dp[i-1][j]  (must skip s[i-1])
// Time: O(m*n), Space: O(m*n)
//
// ─────────────────────────────────────────────────────────────────────────────

public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return (int) dp[m][n];
    }

    public static void main(String[] args) {
        DistinctSubsequences sol = new DistinctSubsequences();
        System.out.println(sol.numDistinct("rabbbit", "rabbit")); // 3
        System.out.println(sol.numDistinct("babgbag", "bag"));    // 5
        System.out.println(sol.numDistinct("a", "b"));            // 0
    }
}
