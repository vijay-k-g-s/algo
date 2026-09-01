package dynamicprogramming;

// Problem (LC 312): Given an array of n balloons with values, burst all balloons
//          to maximize coins. Bursting balloon i earns nums[i-1] * nums[i] * nums[i+1].
//          After bursting, adjacent balloons become neighbors. Out-of-bounds = 1.
// Example: nums = [3, 1, 5, 8] → 167
//          (burst 1→3*1*5=15, burst 5→3*5*8=120, burst 3→3*8=24... optimal = 167)
//          nums = [1, 5] → 10
// Approach: Interval DP (work backwards — think of last balloon to burst in range).
//   dp[left][right] = max coins from bursting all balloons between left and right (exclusive).
//   For each possible "last balloon" k in (left, right):
//     dp[left][right] = max(dp[left][k] + nums[left]*nums[k]*nums[right] + dp[k][right])
//   Pad nums with 1 on both sides.
// Time: O(n³), Space: O(n²)
//
// ─────────────────────────────────────────────────────────────────────────────

public class BurstBalloons {

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] padded = new int[n + 2];
        padded[0] = padded[n + 1] = 1;
        for (int i = 0; i < n; i++) padded[i + 1] = nums[i];

        int size = padded.length;
        int[][] dp = new int[size][size];

        for (int length = 2; length < size; length++) {
            for (int left = 0; left < size - length; left++) {
                int right = left + length;
                for (int k = left + 1; k < right; k++) {
                    dp[left][right] = Math.max(dp[left][right],
                        dp[left][k] + padded[left] * padded[k] * padded[right] + dp[k][right]);
                }
            }
        }
        return dp[0][size - 1];
    }

    public static void main(String[] args) {
        BurstBalloons sol = new BurstBalloons();
        System.out.println(sol.maxCoins(new int[]{3, 1, 5, 8})); // 167
        System.out.println(sol.maxCoins(new int[]{1, 5}));        // 10
        System.out.println(sol.maxCoins(new int[]{0}));           // 0
    }
}
