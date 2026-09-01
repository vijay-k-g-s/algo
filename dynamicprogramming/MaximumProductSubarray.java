package dynamicprogramming;

// Problem (LC 152): Given an integer array, find the contiguous subarray that has
//          the largest product and return that product.
// Example: nums = [2, 3, -2, 4] → 6  (subarray [2,3])
//          nums = [-2, 0, -1]   → 0
//          nums = [-2, 3, -4]   → 24  (full array)
// Approach: Track both max and min product ending at current position.
//   A negative number can turn a large negative into a large positive.
//   currMax = max(num, currMax*num, currMin*num)
//   currMin = min(num, currMax*num, currMin*num)
//   Update global max at each step.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int globalMax = nums[0], currMax = nums[0], currMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            int tempMax = Math.max(n, Math.max(currMax * n, currMin * n));
            currMin = Math.min(n, Math.min(currMax * n, currMin * n));
            currMax = tempMax;
            globalMax = Math.max(globalMax, currMax);
        }
        return globalMax;
    }

    public static void main(String[] args) {
        MaximumProductSubarray sol = new MaximumProductSubarray();
        System.out.println(sol.maxProduct(new int[]{2, 3, -2, 4}));  // 6
        System.out.println(sol.maxProduct(new int[]{-2, 0, -1}));    // 0
        System.out.println(sol.maxProduct(new int[]{-2, 3, -4}));    // 24
    }
}
