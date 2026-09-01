package dynamicprogramming;

// Problem (LC 213): Same as House Robber but houses are in a circle — first and
//          last houses are adjacent. Return max money without robbing two adjacent houses.
// Example: nums = [2, 3, 2] → 3  (can't rob both 2's; rob middle = 3)
//          nums = [1, 2, 3] → 3  (rob house 1 and 3: 1+3=4... wait: rob house 3 only = 3)
//          Actually [1,2,3]: rob(0,1) = rob([1,2]) = 2, rob(1,2) = rob([2,3]) = 3 → max = 3
// Approach: Run House Robber twice:
//   1. On nums[0..n-2] (exclude last house)
//   2. On nums[1..n-1] (exclude first house)
//   Return max of both. Edge case: single house → return nums[0].
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class HouseRobberII {

    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(robRange(nums, 0, nums.length - 2),
                        robRange(nums, 1, nums.length - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0;
        for (int i = start; i <= end; i++) {
            int curr = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void main(String[] args) {
        HouseRobberII sol = new HouseRobberII();
        System.out.println(sol.rob(new int[]{2, 3, 2}));    // 3
        System.out.println(sol.rob(new int[]{1, 2, 3}));    // 3
        System.out.println(sol.rob(new int[]{1, 2, 3, 1})); // 4
    }
}
