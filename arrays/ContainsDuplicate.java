package arrays;

// Problem (LC 217): Given an integer array, return true if any value appears
//          at least twice. Return false if every element is distinct.
// Example: nums = [1, 2, 3, 1] → true
//          nums = [1, 2, 3, 4] → false
//          nums = [1, 1, 1, 3, 3, 4, 3, 2, 4, 2] → true
// Approach: HashSet — insert each number; if it's already present, duplicate found.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int n : nums) {
            if (!seen.add(n)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicate sol = new ContainsDuplicate();
        System.out.println(sol.containsDuplicate(new int[]{1, 2, 3, 1}));          // true
        System.out.println(sol.containsDuplicate(new int[]{1, 2, 3, 4}));          // false
        System.out.println(sol.containsDuplicate(new int[]{1, 1, 1, 3, 3, 4}));   // true
    }
}
