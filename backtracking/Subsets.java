package backtracking;

// Problem (LC 78): Given an integer array of unique elements, return all possible
//          subsets (the power set). The solution set must not contain duplicate subsets.
//          Return the subsets in any order.
// Example: nums = [1, 2, 3]
//          Output: [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]  (8 = 2^3 subsets)
//          nums = [0]
//          Output: [[], [0]]
// Approach: Inclusion / Exclusion at each index.
//   - INCLUDE nums[i]: add to current, recurse to i+1, then remove.
//   - EXCLUDE nums[i]: recurse to i+1 without changing current.
//   - Base case: index == nums.length → snapshot current into result.
//   Every element independently contributes a binary include/exclude choice,
//   producing all 2^n subsets.
// Time: O(n * 2^n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    // Inclusion / Exclusion strategy:
    // At each index, decide to INCLUDE or EXCLUDE nums[index]
    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // INCLUDE nums[index]
        current.add(nums[index]);
        backtrack(nums, index + 1, current, result);
        current.remove(current.size() - 1);

        // EXCLUDE nums[index]
        backtrack(nums, index + 1, current, result);
    }

    public static void main(String[] args) {
        Subsets sol = new Subsets();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = sol.subsets(nums);
        System.out.println("Input: [1, 2, 3]");
        System.out.println("Subsets (" + result.size() + "):");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }
}
