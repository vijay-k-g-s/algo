package dynamicprogramming;

// Problem (LC 518): Given coins of different denominations and an amount, return
//          the number of distinct combinations that sum to amount.
// Example: amount = 5, coins = [1, 2, 5] → 4
//          (5; 2+2+1; 2+1+1+1; 1+1+1+1+1)
//          amount = 3, coins = [2] → 0
//          amount = 10, coins = [10] → 1
// Approach: 0/1 Knapsack-style DP (unbounded — coins can be reused).
//   dp[i] = number of ways to make amount i.
//   dp[0] = 1 (one way to make 0: use nothing).
//   For each coin, iterate FORWARD (allows reuse): dp[i] += dp[i - coin].
//   Outer loop on coins to avoid counting permutations as different combinations.
// Time: O(n * amount), Space: O(amount)
//
// ─────────────────────────────────────────────────────────────────────────────

public class CoinChangeII {

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChangeII sol = new CoinChangeII();
        System.out.println(sol.change(5, new int[]{1, 2, 5}));  // 4
        System.out.println(sol.change(3, new int[]{2}));         // 0
        System.out.println(sol.change(10, new int[]{10}));       // 1
    }
}
