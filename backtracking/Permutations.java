package backtracking;

// Problem (LC 46): Given an array of distinct integers, return all possible
//          permutations in any order.
// Example: nums = [1, 2, 3]
//          Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//          nums = [0, 1]
//          Output: [[0,1],[1,0]]
//          nums = [1]
//          Output: [[1]]
// Approach: At each recursive call, iterate over all indices.
//   Skip any index that is already used (tracked by boolean[] used).
//   Mark used[i] = true, add to current, recurse, then undo both (backtrack).
//   When current.size() == nums.length, one full permutation is complete.
// Time: O(n * n!), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    // At each call, try placing every unused element at the current position
    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            current.add(nums[i]);
            backtrack(nums, used, current, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Permutations sol = new Permutations();

        System.out.println("[1,2,3] → " + sol.permute(new int[]{1, 2, 3}));
        System.out.println("[0,1]   → " + sol.permute(new int[]{0, 1}));
        System.out.println("[1]     → " + sol.permute(new int[]{1}));
    }
}
