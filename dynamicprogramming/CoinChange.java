package dynamicprogramming;

// Problem (LC 322): Given coins of different denominations and an amount, return
//          the fewest number of coins needed to make up the amount.
//          Return -1 if the amount cannot be made.
// Example: coins = [1,2,5], amount = 11 → 3  (5+5+1)
//          coins = [2], amount = 3 → -1
//          coins = [1], amount = 0 → 0
// Approach: Bottom-up DP.
//   dp[i] = min coins to make amount i.
//   dp[0] = 0. For each amount from 1..amount:
//     dp[i] = min(dp[i], dp[i - coin] + 1) for each coin <= i.
//   Initialize all dp values to amount+1 (infinity).
// Time: O(amount * n), Space: O(amount)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange sol = new CoinChange();
        System.out.println(sol.coinChange(new int[]{1, 2, 5}, 11)); // 3
        System.out.println(sol.coinChange(new int[]{2}, 3));         // -1
        System.out.println(sol.coinChange(new int[]{1}, 0));         // 0
    }
}
