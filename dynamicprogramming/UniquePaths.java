package dynamicprogramming;

// Problem (LC 62): A robot is on an m×n grid at top-left corner. It can only
//          move right or down. How many unique paths reach the bottom-right corner?
// Example: m=3, n=7 → 28
//          m=3, n=2 → 3  (R↓↓, ↓R↓, ↓↓R)
// Approach: Bottom-up DP.
//   dp[i][j] = number of ways to reach cell (i,j).
//   Top row and left column = 1 (only one way to reach each).
//   dp[i][j] = dp[i-1][j] + dp[i][j-1].
//   Optimize to 1D: dp[j] += dp[j-1].
// Time: O(m*n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        UniquePaths sol = new UniquePaths();
        System.out.println(sol.uniquePaths(3, 7)); // 28
        System.out.println(sol.uniquePaths(3, 2)); // 3
        System.out.println(sol.uniquePaths(1, 1)); // 1
    }
}
