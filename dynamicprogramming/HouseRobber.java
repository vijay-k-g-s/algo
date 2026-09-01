package dynamicprogramming;

// Problem (LC 198): You are a robber planning to rob houses along a street.
//          Adjacent houses have security systems — robbing two adjacent houses triggers
//          an alert. Return the maximum money you can rob without alerting police.
// Example: nums = [1, 2, 3, 1] → 4  (rob house 1 and 3: 1+3=4)
//          nums = [2, 7, 9, 3, 1] → 12  (rob house 1, 3, 5: 2+9+1=12)
// Approach: DP with two variables.
//   For each house, decide: rob it (prev2 + nums[i]) or skip it (prev1).
//   dp[i] = max(dp[i-1], dp[i-2] + nums[i])
//   Track only the last two values to save space.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class HouseRobber {

    public int rob(int[] nums) {
        int prev2 = 0, prev1 = 0;
        for (int n : nums) {
            int curr = Math.max(prev1, prev2 + n);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void main(String[] args) {
        HouseRobber sol = new HouseRobber();
        System.out.println(sol.rob(new int[]{1, 2, 3, 1}));    // 4
        System.out.println(sol.rob(new int[]{2, 7, 9, 3, 1})); // 12
        System.out.println(sol.rob(new int[]{0}));              // 0
    }
}
