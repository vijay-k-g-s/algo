package bitmanipulation;

// Problem (LC 136): Given a non-empty array where every element appears twice
//          except for one, find that single element. Must be O(n) time, O(1) space.
// Example: nums = [2, 2, 1] → 1
//          nums = [4, 1, 2, 1, 2] → 4
//          nums = [1] → 1
// Approach: XOR.
//   a XOR a = 0 (same number cancels)
//   a XOR 0 = a
//   XOR all numbers together — pairs cancel, single number remains.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class SingleNumber {

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int n : nums) result ^= n;
        return result;
    }

    public static void main(String[] args) {
        SingleNumber sol = new SingleNumber();
        System.out.println(sol.singleNumber(new int[]{2, 2, 1}));          // 1
        System.out.println(sol.singleNumber(new int[]{4, 1, 2, 1, 2}));   // 4
        System.out.println(sol.singleNumber(new int[]{1}));                // 1
    }
}
