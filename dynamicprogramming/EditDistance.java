package dynamicprogramming;

// Problem (LC 72): Given two strings word1 and word2, return the minimum number
//          of operations (insert, delete, replace) to convert word1 to word2.
// Example: word1 = "horse", word2 = "ros" → 3
//          (horse→rorse(replace h→r), rorse→rose(delete r), rose→ros(delete e))
//          word1 = "intention", word2 = "execution" → 5
// Approach: 2D DP.
//   dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1].
//   If chars match: dp[i][j] = dp[i-1][j-1]
//   Else: dp[i][j] = 1 + min(dp[i-1][j-1] (replace),
//                              dp[i-1][j]   (delete from word1),
//                              dp[i][j-1]   (insert into word1))
// Time: O(m*n), Space: O(m*n)
//
// ─────────────────────────────────────────────────────────────────────────────

public class EditDistance {

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                   Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        EditDistance sol = new EditDistance();
        System.out.println(sol.minDistance("horse", "ros"));       // 3
        System.out.println(sol.minDistance("intention", "execution")); // 5
        System.out.println(sol.minDistance("", "abc"));            // 3
    }
}
