package twopointers;

// Problem (LC 15): Given an integer array, return all triplets [nums[i], nums[j], nums[k]]
//          such that i, j, k are distinct and nums[i] + nums[j] + nums[k] == 0.
//          The solution set must not contain duplicate triplets.
// Example: nums = [-1, 0, 1, 2, -1, -4] → [[-1,-1,2],[-1,0,1]]
//          nums = [0, 1, 1]              → []
//          nums = [0, 0, 0]              → [[0,0,0]]
// Approach: Sort + Two Pointers.
//   For each index i (fix nums[i]), use two pointers left=i+1, right=n-1.
//   Find pairs that sum to -nums[i].
//   Skip duplicate values of nums[i] (and of left/right after finding a triplet)
//   to avoid duplicate triplets.
// Time: O(n²), Space: O(1) excluding output
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicate first element
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++; right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum sol = new ThreeSum();
        System.out.println(sol.threeSum(new int[]{-1, 0, 1, 2, -1, -4})); // [[-1,-1,2],[-1,0,1]]
        System.out.println(sol.threeSum(new int[]{0, 1, 1}));              // []
        System.out.println(sol.threeSum(new int[]{0, 0, 0}));              // [[0,0,0]]
    }
}
