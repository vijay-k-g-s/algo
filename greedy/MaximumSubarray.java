package greedy;

// Problem (LC 53): Given an integer array, find the subarray with the largest sum
//          and return its sum. A subarray is a contiguous part of an array.
// Example: nums = [-2,1,-3,4,-1,2,1,-5,4] → 6  (subarray [4,-1,2,1])
//          nums = [1] → 1
//          nums = [5,4,-1,7,8] → 23  (entire array)
// Approach: Kadane's Algorithm (Greedy DP).
//   curr = max sum ending at current position.
//   If curr < 0, start fresh from the current element (drop the prefix).
//   Update global max at each step.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int curr = nums[0], maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            maxSum = Math.max(maxSum, curr);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaximumSubarray sol = new MaximumSubarray();
        System.out.println(sol.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})); // 6
        System.out.println(sol.maxSubArray(new int[]{1}));                      // 1
        System.out.println(sol.maxSubArray(new int[]{5,4,-1,7,8}));            // 23
    }
}
