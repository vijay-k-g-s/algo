package dynamicprogramming;

// Problem (LC 746): Given an array cost[] where cost[i] is the cost to step on
//          stair i, you can start at index 0 or 1. From each stair, you can climb
//          1 or 2 steps. Return the minimum cost to reach the top (beyond last index).
// Example: cost = [10, 15, 20] → 15  (start at index 1, pay 15, jump 2 → top)
//          cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1] → 6
// Approach: DP — dp[i] = min cost to reach step i.
//   dp[i] = cost[i] + min(dp[i-1], dp[i-2])
//   Answer = min(dp[n-1], dp[n-2])  (can jump 1 or 2 steps to the top).
//   Space-optimize to two variables.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int prev2 = cost[0], prev1 = cost[1];
        for (int i = 2; i < cost.length; i++) {
            int curr = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        return Math.min(prev1, prev2);
    }

    public static void main(String[] args) {
        MinCostClimbingStairs sol = new MinCostClimbingStairs();
        System.out.println(sol.minCostClimbingStairs(new int[]{10, 15, 20}));                          // 15
        System.out.println(sol.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1})); // 6
    }
}
