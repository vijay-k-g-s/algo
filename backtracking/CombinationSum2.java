package backtracking;

// Problem (LC 40): Given a collection of candidates (may contain duplicates) and
//          a target, return all unique combinations that sum to target.
//          Each element may only be used once in a combination.
// Example: candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
//          Output: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
//          candidates = [2, 5, 2, 1, 2], target = 5
//          Output: [[1, 2, 2], [5]]
// Approach: Sort first to group duplicates, then Inclusion / Exclusion.
//   - INCLUDE candidates[i]: move to i+1 (no reuse of same element).
//   - EXCLUDE candidates[i]: skip ahead past all identical values at this level
//     so duplicate combinations are never generated.
//   - Base case: remaining == 0 → valid combination found.
// Time: O(2^n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // needed to group duplicates together
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    // Inclusion / Exclusion strategy:
    // INCLUDE candidates[index] → move to index+1 (no reuse of same element)
    // EXCLUDE candidates[index] → skip all duplicates at this level to avoid duplicate combos
    private void backtrack(int[] candidates, int remaining, int index, List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (index >= candidates.length || remaining < 0) {
            return;
        }

        // INCLUDE candidates[index]
        current.add(candidates[index]);
        backtrack(candidates, remaining - candidates[index], index + 1, current, result);
        current.remove(current.size() - 1);

        // EXCLUDE candidates[index] — skip duplicates so we don't re-pick the same value at this level
        int next = index + 1;
        while (next < candidates.length && candidates[next] == candidates[index]) {
            next++;
        }
        backtrack(candidates, remaining, next, current, result);
    }

    public static void main(String[] args) {
        CombinationSum2 sol = new CombinationSum2();

        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println("Input: candidates=[10,1,2,7,6,1,5], target=8");
        System.out.println("Combinations: " + sol.combinationSum2(candidates, target));

        candidates = new int[]{2, 5, 2, 1, 2};
        target = 5;
        System.out.println("\nInput: candidates=[2,5,2,1,2], target=5");
        System.out.println("Combinations: " + sol.combinationSum2(candidates, target));
    }
}
