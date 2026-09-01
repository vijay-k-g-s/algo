package backtracking;

// Problem (LC 47): Given an array that may contain duplicates, return all unique
//          permutations in any order.
// Example: nums = [1, 1, 2]
//          Output: [[1,1,2],[1,2,1],[2,1,1]]
//          nums = [1, 2, 3]
//          Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//          nums = [1, 1, 1]
//          Output: [[1,1,1]]
// Approach: Sort first to group identical values together, then use a boolean[]
//   used array (same as Permutations.java). Add one extra pruning rule to skip
//   duplicates: if nums[i] == nums[i-1] AND !used[i-1], this branch would
//   generate the exact same permutation already produced by the i-1 branch,
//   so skip it.
// Time: O(n * n!), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations2 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums); // group duplicates together
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            // Skip duplicate: same value as previous AND previous is not used at this level
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            current.add(nums[i]);
            backtrack(nums, used, current, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Permutations2 sol = new Permutations2();

        System.out.println("[1,1,2] → " + sol.permuteUnique(new int[]{1, 1, 2}));
        System.out.println("[1,2,3] → " + sol.permuteUnique(new int[]{1, 2, 3}));
        System.out.println("[1,1,1] → " + sol.permuteUnique(new int[]{1, 1, 1}));
    }
}
