package math;

// Problem (LC 66): Given a large integer represented as an array of digits,
//          increment the integer by one and return the resulting array.
// Example: digits = [1,2,3] → [1,2,4]
//          digits = [4,3,2,1] → [4,3,2,2]
//          digits = [9] → [1,0]  (carry propagates)
//          digits = [9,9,9] → [1,0,0,0]
// Approach: Traverse from last digit to first.
//   If digit < 9: increment and return.
//   If digit == 9: set to 0 and carry over to next.
//   If all digits were 9: prepend 1 (e.g. 999 → 1000).
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        // All digits were 9
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {
        PlusOne sol = new PlusOne();
        System.out.println(Arrays.toString(sol.plusOne(new int[]{1, 2, 3}))); // [1,2,4]
        System.out.println(Arrays.toString(sol.plusOne(new int[]{9})));       // [1,0]
        System.out.println(Arrays.toString(sol.plusOne(new int[]{9, 9, 9}))); // [1,0,0,0]
    }
}
