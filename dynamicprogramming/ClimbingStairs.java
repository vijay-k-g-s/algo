package dynamicprogramming;

// Problem (LC 70): You are climbing a staircase with n steps. Each time you can
//          climb 1 or 2 steps. In how many distinct ways can you reach the top?
// Example: n = 2 → 2  (1+1, 2)
//          n = 3 → 3  (1+1+1, 1+2, 2+1)
// Approach: Dynamic programming (Fibonacci pattern).
//   dp[i] = dp[i-1] + dp[i-2]  (arrive from step below by 1, or two below by 2)
//   Base cases: dp[1]=1, dp[2]=2.
//   Optimize to O(1) space by keeping only last two values.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n <= 2) return n;
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void main(String[] args) {
        ClimbingStairs sol = new ClimbingStairs();
        System.out.println(sol.climbStairs(1)); // 1
        System.out.println(sol.climbStairs(2)); // 2
        System.out.println(sol.climbStairs(3)); // 3
        System.out.println(sol.climbStairs(5)); // 8
    }
}
