package bitmanipulation;

// Problem (LC 268): Given an array nums containing n distinct numbers in range [0,n],
//          return the one missing number.
// Example: nums = [3, 0, 1] → 2
//          nums = [0, 1]    → 2
//          nums = [9,6,4,2,3,5,7,0,1] → 8
// Approach: XOR.
//   XOR all indices 0..n with all values in nums.
//   Pairs cancel; unpaired index = missing number.
//   Alternative: expected sum = n*(n+1)/2. Missing = expected - actual sum.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class MissingNumber {

    public int missingNumber(int[] nums) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        MissingNumber sol = new MissingNumber();
        System.out.println(sol.missingNumber(new int[]{3, 0, 1}));           // 2
        System.out.println(sol.missingNumber(new int[]{0, 1}));              // 2
        System.out.println(sol.missingNumber(new int[]{9,6,4,2,3,5,7,0,1})); // 8
    }
}
