package dynamicprogramming;

// Problem (LC 10): Given string s and pattern p, implement regex matching with:
//   '.' — matches any single character.
//   '*' — matches zero or more of the preceding element.
//   The matching must cover the ENTIRE string s.
// Example: s="aa", p="a"   → false
//          s="aa", p="a*"  → true  ('a*' = zero or more 'a')
//          s="ab", p=".*"  → true  ('.*' = any chars)
//          s="aab", p="c*a*b" → true (c*=0 c's, a*=2 a's, b=b)
// Approach: 2D DP.
//   dp[i][j] = true if s[0..i-1] matches p[0..j-1].
//   dp[0][0] = true. dp[0][j] = dp[0][j-2] if p[j-1]=='*' (zero occurrences).
//   If p[j-1]=='*': dp[i][j] = dp[i][j-2]               (zero occurrences of p[j-2])
//                            || (dp[i-1][j] && matches(s[i-1], p[j-2]))  (one+ occurrences)
//   Else: dp[i][j] = dp[i-1][j-1] && matches(s[i-1], p[j-1])
// Time: O(m*n), Space: O(m*n)
//
// ─────────────────────────────────────────────────────────────────────────────

public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // Handle patterns like a*, a*b*, a*b*c* that can match empty string
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] // zero occurrences
                        || (dp[i - 1][j] && matches(s.charAt(i - 1), p.charAt(j - 2))); // one+
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && matches(s.charAt(i - 1), p.charAt(j - 1));
                }
            }
        }
        return dp[m][n];
    }

    private boolean matches(char sc, char pc) {
        return pc == '.' || pc == sc;
    }

    public static void main(String[] args) {
        RegularExpressionMatching sol = new RegularExpressionMatching();
        System.out.println(sol.isMatch("aa", "a"));    // false
        System.out.println(sol.isMatch("aa", "a*"));   // true
        System.out.println(sol.isMatch("ab", ".*"));   // true
        System.out.println(sol.isMatch("aab", "c*a*b")); // true
    }
}
