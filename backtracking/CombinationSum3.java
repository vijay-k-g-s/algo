package backtracking;

// Problem (LC 216): Find all combinations of exactly k numbers from 1–9 that
//          sum to n. Each digit may be used at most once. Return all valid combos.
// Example: k = 3, n = 7  → [[1, 2, 4]]
//          k = 3, n = 9  → [[1, 2, 6], [1, 3, 5], [2, 3, 4]]
//          k = 4, n = 1  → []  (impossible: smallest 4-digit sum = 1+2+3+4 = 10)
// Approach: Inclusion / Exclusion over digits 1..9.
//   - INCLUDE digit: add to current, reduce k and remaining, recurse to digit+1.
//   - EXCLUDE digit: skip digit, recurse to digit+1 with same k and remaining.
//   - Base case: k == 0 && remaining == 0 → valid combination.
//   - Prune: digit > 9, k == 0 but remaining != 0, or remaining < 0.
// Time: O(C(9, k)), Space: O(k)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }

    // Inclusion / Exclusion over digits 1..9
    // INCLUDE digit → move to digit+1, k-1 remaining slots, reduce remaining sum
    // EXCLUDE digit → move to digit+1, same k and remaining sum
    private void backtrack(int digit, int k, int remaining, List<Integer> current, List<List<Integer>> result) {
        if (k == 0 && remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (digit > 9 || k == 0 || remaining < 0) {
            return;
        }

        // INCLUDE digit
        current.add(digit);
        backtrack(digit + 1, k - 1, remaining - digit, current, result);
        current.remove(current.size() - 1);

        // EXCLUDE digit
        backtrack(digit + 1, k, remaining, current, result);
    }

    public static void main(String[] args) {
        CombinationSum3 sol = new CombinationSum3();

        System.out.println("k=3, n=7 → " + sol.combinationSum3(3, 7));   // [[1,2,4]]
        System.out.println("k=3, n=9 → " + sol.combinationSum3(3, 9));   // [[1,2,6],[1,3,5],[2,3,4]]
        System.out.println("k=4, n=1 → " + sol.combinationSum3(4, 1));   // []
    }
}
