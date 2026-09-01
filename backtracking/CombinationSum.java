package backtracking;

// Problem (LC 39): Given an array of distinct integers and a target, return all
//          unique combinations where the chosen numbers sum to target.
//          The same number may be chosen unlimited times.
// Example: candidates = [2, 3, 6, 7], target = 7
//          Output: [[2, 2, 3], [7]]
//          candidates = [2, 3, 5], target = 8
//          Output: [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
// Approach: Inclusion / Exclusion backtracking.
//   - INCLUDE candidates[i]: subtract from remaining and stay at index i (reuse allowed).
//   - EXCLUDE candidates[i]: move to index i+1 without changing remaining.
//   - Base case: remaining == 0 → valid combination found.
//   - Prune: remaining < 0 or index out of bounds.
// Time: O(n^(T/M)) where T = target, M = min candidate value. Space: O(T/M)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    // Inclusion / Exclusion strategy:
    // At each index, decide to INCLUDE the candidate (stay at same index, reuse allowed)
    // or EXCLUDE it (move to next index).
    private void backtrack(int[] candidates, int remaining, int index, List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (index >= candidates.length || remaining < 0) {
            return;
        }

        // INCLUDE candidates[index] — stay at same index to allow reuse
        current.add(candidates[index]);
        backtrack(candidates, remaining - candidates[index], index, current, result);
        current.remove(current.size() - 1);

        // EXCLUDE candidates[index] — move to next index
        backtrack(candidates, remaining, index + 1, current, result);
    }


//    void practice(int[] candidates, int remaining, int index, List<Integer> current, List<List<Integer>> result){
//
//        if(remaining == 0){
//            result.add();
//        }
//    }

    public static void main(String[] args) {
        CombinationSum sol = new CombinationSum();

        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = sol.combinationSum(candidates, target);
        System.out.println("Input: candidates=[2,3,6,7], target=7");
        System.out.println("Combinations: " + result);

        candidates = new int[]{2, 3, 5};
        target = 8;
        result = sol.combinationSum(candidates, target);
        System.out.println("\nInput: candidates=[2,3,5], target=8");
        System.out.println("Combinations: " + result);
    }
}
