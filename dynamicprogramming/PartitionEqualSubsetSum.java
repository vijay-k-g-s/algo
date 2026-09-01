package dynamicprogramming;

// Problem (LC 416): Given an integer array, determine if it can be partitioned into
//          two subsets with equal sum.
// Example: nums = [1, 5, 11, 5] → true  ({1,5,5} and {11})
//          nums = [1, 2, 3, 5]  → false
// Approach: Reduce to subset-sum: find a subset with sum = total/2.
//   If total is odd → impossible.
//   DP boolean array: dp[j] = true if subset with sum j is achievable.
//   For each num, iterate j from target..num:
//     dp[j] |= dp[j - num]  (0/1 knapsack — iterate backward to avoid reuse)
// Time: O(n * sum), Space: O(sum)
//
// ─────────────────────────────────────────────────────────────────────────────

public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int n : nums) total += n;
        if (total % 2 != 0) return false;

        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum sol = new PartitionEqualSubsetSum();
        System.out.println(sol.canPartition(new int[]{1, 5, 11, 5})); // true
        System.out.println(sol.canPartition(new int[]{1, 2, 3, 5}));  // false
        System.out.println(sol.canPartition(new int[]{1, 1}));        // true
    }
}
