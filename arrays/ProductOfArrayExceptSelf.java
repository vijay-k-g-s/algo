package arrays;

// Problem (LC 238): Given an integer array, return an array where output[i]
//          equals the product of all elements except nums[i].
//          Must run in O(n) without using the division operator.
// Example: nums = [1, 2, 3, 4] → [24, 12, 8, 6]
//          nums = [-1, 1, 0, -3, 3] → [0, 0, 9, 0, 0]
// Approach: Two-pass prefix/suffix products.
//   Pass 1 (left→right): result[i] = product of all elements to the LEFT of i.
//   Pass 2 (right→left): multiply result[i] by the running suffix product
//     (product of all elements to the RIGHT of i).
//   No extra array needed for suffix — track it with a running variable.
// Time: O(n), Space: O(1) excluding the output array
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Pass 1: result[i] = product of all elements left of i
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Pass 2: multiply by suffix product (running right-to-left)
        int suffix = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix *= nums[i + 1];
            result[i] *= suffix;
        }

        return result;
    }

    public static void main(String[] args) {
        ProductOfArrayExceptSelf sol = new ProductOfArrayExceptSelf();
        System.out.println(Arrays.toString(sol.productExceptSelf(new int[]{1, 2, 3, 4})));       // [24, 12, 8, 6]
        System.out.println(Arrays.toString(sol.productExceptSelf(new int[]{-1, 1, 0, -3, 3}))); // [0, 0, 9, 0, 0]
    }
}
