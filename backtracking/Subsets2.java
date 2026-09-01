package backtracking;

// Problem (LC 90): Given an integer array that may contain duplicates, return all
//          possible unique subsets (the power set). No duplicate subsets in result.
// Example: nums = [1, 2, 2]
//          Output: [[], [1], [1,2], [1,2,2], [2], [2,2]]
//          nums = [1, 2, 2, 3]
//          Output: [[], [1], [1,2], [1,2,2], [1,2,2,3], [1,2,3], [1,3], [2], [2,2], [2,2,3], [2,3], [3]]
// Approach: Sort first to group duplicates together, then Inclusion / Exclusion.
//   - INCLUDE nums[i]: add to current, recurse to i+1.
//   - EXCLUDE nums[i]: skip ahead past all identical values at this recursive
//     level so that the "exclude" branch doesn't revisit the same value and
//     produce duplicate subsets.
//   - Base case: index == nums.length → snapshot current into result.
// Time: O(n * 2^n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); // group duplicates together
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    // Inclusion / Exclusion strategy:
    // INCLUDE nums[index] → move to index+1
    // EXCLUDE nums[index] → skip all duplicates of this value, then move on
    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // INCLUDE nums[index]
        current.add(nums[index]);
        backtrack(nums, index + 1, current, result);
        current.remove(current.size() - 1);

        // EXCLUDE nums[index] — skip duplicates to avoid duplicate subsets
        int next = index + 1;
        while (next < nums.length && nums[next] == nums[index]) {
            next++;
        }
        backtrack(nums, next, current, result);
    }

    public static void main(String[] args) {
        Subsets2 sol = new Subsets2();

        System.out.println("[1,2,2] → " + sol.subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println("[0]     → " + sol.subsetsWithDup(new int[]{0}));
        System.out.println("[1,2,2,3] → " + sol.subsetsWithDup(new int[]{1, 2, 2, 3}));
    }
}
