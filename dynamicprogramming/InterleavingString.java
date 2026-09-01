package dynamicprogramming;

// Problem (LC 97): Given strings s1, s2, and s3, determine if s3 is formed by
//          interleaving s1 and s2 (maintaining their relative order).
// Example: s1="aab", s2="axy", s3="aaxaby" → true
//          s1="aab", s2="axy", s3="aayxab" → false
//          s1="", s2="", s3="" → true
// Approach: 2D DP.
//   dp[i][j] = true if s3[0..i+j-1] can be formed by interleaving s1[0..i-1] and s2[0..j-1].
//   Transition:
//     dp[i][j] = (dp[i-1][j] && s1[i-1] == s3[i+j-1])   // take from s1
//              || (dp[i][j-1] && s2[j-1] == s3[i+j-1])  // take from s2
// Time: O(m*n), Space: O(m*n) — can optimize to O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 1; i <= m; i++)
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        for (int j = 1; j <= n; j++)
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                         || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        InterleavingString sol = new InterleavingString();
        System.out.println(sol.isInterleave("aab", "axy", "aaxaby")); // true
        System.out.println(sol.isInterleave("aab", "axy", "aayxab")); // false
        System.out.println(sol.isInterleave("", "", ""));             // true
    }
}
