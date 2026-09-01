package dynamicprogramming;

// Problem (LC 1143): Given two strings text1 and text2, return the length of
//          their longest common subsequence. Return 0 if none exists.
// Example: text1 = "abcde", text2 = "ace" → 3  (subsequence "ace")
//          text1 = "abc",   text2 = "abc" → 3
//          text1 = "abc",   text2 = "def" → 0
// Approach: Bottom-up 2D DP.
//   dp[i][j] = LCS length of text1[0..i-1] and text2[0..j-1].
//   If text1[i-1] == text2[j-1]: dp[i][j] = dp[i-1][j-1] + 1
//   Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
// Time: O(m*n), Space: O(m*n)
//
// ─────────────────────────────────────────────────────────────────────────────

public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence sol = new LongestCommonSubsequence();
        System.out.println(sol.longestCommonSubsequence("abcde", "ace")); // 3
        System.out.println(sol.longestCommonSubsequence("abc", "abc"));   // 3
        System.out.println(sol.longestCommonSubsequence("abc", "def"));   // 0
    }
}
