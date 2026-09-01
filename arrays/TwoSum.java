package arrays;

// Problem (LC 1): Given an integer array and a target, return the indices of
//          the two numbers that add up to the target. Exactly one solution exists.
// Example: nums = [2, 7, 11, 15], target = 9  → [0, 1]  (2 + 7 = 9)
//          nums = [3, 2, 4],      target = 6  → [1, 2]  (2 + 4 = 6)
//          nums = [3, 3],         target = 6  → [0, 1]
// Approach: HashMap of value → index.
//   For each num, check if (target - num) is already in the map.
//   If yes, return [map.get(complement), i].
//   Otherwise, store num → i and continue.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSum sol = new TwoSum();
        System.out.println(Arrays.toString(sol.twoSum(new int[]{2, 7, 11, 15}, 9))); // [0, 1]
        System.out.println(Arrays.toString(sol.twoSum(new int[]{3, 2, 4}, 6)));      // [1, 2]
        System.out.println(Arrays.toString(sol.twoSum(new int[]{3, 3}, 6)));         // [0, 1]
    }
}
