package twopointers;

// Problem (LC 167): Given a 1-indexed sorted array of integers, find two numbers
//          that add up to the target. Return their 1-based indices as [index1, index2].
//          Exactly one solution exists; may not use the same element twice.
// Example: numbers = [2, 7, 11, 15], target = 9  → [1, 2]
//          numbers = [2, 3, 4],      target = 6  → [1, 3]
//          numbers = [-1, 0],        target = -1 → [1, 2]
// Approach: Two pointers starting at both ends.
//   If sum == target → return [left+1, right+1].
//   If sum <  target → move left pointer right (need larger value).
//   If sum >  target → move right pointer left (need smaller value).
//   Sorted order guarantees convergence.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;

public class TwoSumII {

    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) return new int[]{left + 1, right + 1};
            else if (sum < target) left++;
            else right--;
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSumII sol = new TwoSumII();
        System.out.println(Arrays.toString(sol.twoSum(new int[]{2, 7, 11, 15}, 9)));  // [1, 2]
        System.out.println(Arrays.toString(sol.twoSum(new int[]{2, 3, 4}, 6)));       // [1, 3]
        System.out.println(Arrays.toString(sol.twoSum(new int[]{-1, 0}, -1)));        // [1, 2]
    }
}
